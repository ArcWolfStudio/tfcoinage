package com.arcwolf.tfcoinage.items;

import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Properties;



public class CoinItem extends Item {
    public CoinItem(Properties properties){
        super(properties);
    }
//    public static final IntegerProperty COUNT = TFCBlockStateProperties.COUNT_1_64;
//    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit){
//        if(!player.isShiftKeyDown()){
//            BlockPos Pos1 = pos;
//            while(Helpers.isBlock(level.getBlockState(Pos1.above()), Block.byItem(this))){
//                Pos1 = Pos1.above();
//            }
//            final BlockState Pos1State = level.getBlockState(Pos1);
//            final int topCoins = Pos1State.getValue(COUNT);
//
//            level.getBlockEntity(Pos1, COIN_PILE.get()).ifPresent(pile -> ItemHandlerHelper.giveItemToPlayer(player, pile.removeCoin()));
//            if(topCoins == 1){
//                level.removeBlock(Pos1,false);
//            }
//            else {
//                level.setBlock(Pos1,Pos1State.setValue(COUNT,topCoins -1),Block.UPDATE_CLIENTS);
//            }
//            return InteractionResult.SUCCESS;
//        }
//        return InteractionResult.PASS;
//    }
}
