package com.arcwolf.tfcoinage.util;

import com.arcwolf.tfcoinage.blockentities.TfCoinBlockEntities;
import com.arcwolf.tfcoinage.blocks.devices.CoinPileBlock;
import net.dries007.tfc.util.BlockItemPlacement;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.InteractionManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.arcwolf.tfcoinage.blocks.CoinBlocks.COIN_PILE;


public class TFCoinInteraction {


    public static void registerDefaultInteractions()
    {
//        final BlockItemPlacement CoinPilePlacement = new BlockItemPlacement((Supplier<? extends Item>) () -> Items.AIR, (Supplier<? extends Block>) COIN_PILE);
        final BlockItemPlacement CoinPilePlacement = new BlockItemPlacement(() -> Items.AIR, COIN_PILE);
        InteractionManager.register(Ingredient.of(TfCoinageTags.Items.PILEABLE_COINS), false, (stack, context) -> {
            final Player player = context.getPlayer();
            if (player != null && player.mayBuild() && player.isShiftKeyDown()) {
                final Level level = context.getLevel();
                final BlockPos posClicked = context.getClickedPos();
                final BlockState stateClicked = level.getBlockState(posClicked);

                if (Helpers.isBlock(stateClicked, COIN_PILE.get())) {
                    // We clicked on an ingot pile, so attempt to add to the pile
                    final int currentCoins = stateClicked.getValue(CoinPileBlock.COUNT);
                    if (currentCoins < 64) {
                        final ItemStack insertStack = stack.split(1);

                        Helpers.playPlaceSound(level, posClicked, stateClicked);
                        level.setBlock(posClicked, stateClicked.setValue(CoinPileBlock.COUNT, currentCoins + 1), Block.UPDATE_CLIENTS);
                        level.getBlockEntity(posClicked, TfCoinBlockEntities.COIN_PILE.get()).ifPresent(pile -> pile.addCoin(insertStack));
                        return InteractionResult.SUCCESS;
                    } else {
                        // Iterate upwards until we find a non-full ingot pile in the stack
                        BlockPos topPos = posClicked;
                        BlockState topState;
                        do {
                            topPos = topPos.above();
                            topState = level.getBlockState(topPos);
                        } while (Helpers.isBlock(topState, COIN_PILE.get()) && topState.getValue(CoinPileBlock.COUNT) == 64);

                        if (Helpers.isBlock(topState, COIN_PILE.get())) {
                            // We must be at a non-full ingot pile, so we want to place another ingot on this pile instead
                            final ItemStack insertStack = stack.split(1);
                            final int topCoins = topState.getValue(CoinPileBlock.COUNT);

                            Helpers.playPlaceSound(level, topPos, topState);
                            level.setBlock(topPos, topState.setValue(CoinPileBlock.COUNT, topCoins + 1), Block.UPDATE_CLIENTS);
                            level.getBlockEntity(topPos, TfCoinBlockEntities.COIN_PILE.get()).ifPresent(topPile -> topPile.addCoin(insertStack));
                            return InteractionResult.SUCCESS;
                        } else if (topState.isAir()) {
                            // We arrived at something that *isn't* an ingot pile, and we want to try and place another ingot on top
                            // We check for air, as we may have run into something solid - don't place anything if that's the case
                            final ItemStack stackBefore = stack.copy();
                            final BlockPos topOfCoinPilePos = topPos.below();
                            final UseOnContext topOfCoinPileContext = new UseOnContext(player, context.getHand(), new BlockHitResult(Vec3.ZERO, Direction.UP, topOfCoinPilePos, false));
                            final InteractionResult result = CoinPilePlacement.onItemUse(stack, topOfCoinPileContext);
                            if (result.consumesAction()) {
                                // Shrinking is already handled by the placement onItemUse() call, we just need to insert the stack
                                stackBefore.setCount(1);
                                level.getBlockEntity(topPos, TfCoinBlockEntities.COIN_PILE.get()).ifPresent(topPile -> topPile.addCoin(stackBefore));
                            }
                            return InteractionResult.SUCCESS;
                        }
                        return InteractionResult.FAIL;
                    }
                } else {
                    // We clicked on a non-ingot pile, so we want to try and place an ingot pile at the current location.
                    // Shrinking is already handled by the placement onItemUse() call, we just need to insert the stack
                    final ItemStack stackBefore = Helpers.copyWithSize(stack, 1);

                    // The blocks as set through onItemUse() might be set at either the clicked, or relative position.
                    // We need to construct this BlockPlaceContext before onItemUse is called, so it has the same value for the actual blocks placed pos
                    final BlockPos actualPlacedPos = new BlockPlaceContext(context).getClickedPos();
                    final InteractionResult result = CoinPilePlacement.onItemUse(stack, context);
                    if (result.consumesAction()) {
                        level.getBlockEntity(actualPlacedPos, TfCoinBlockEntities.COIN_PILE.get()).ifPresent(pile -> pile.addCoin(stackBefore));
                    }
                    return result;
                }
            }
            return InteractionResult.PASS;
        });
    }

}
