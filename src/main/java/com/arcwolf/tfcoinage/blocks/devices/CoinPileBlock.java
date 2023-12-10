package com.arcwolf.tfcoinage.blocks.devices;

import com.arcwolf.tfcoinage.blockentities.CoinPileBlockEntity;
import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedBlock;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

import static com.arcwolf.tfcoinage.blockentities.TfCoinBlockEntities.COIN_PILE;

public class CoinPileBlock extends ExtendedBlock implements EntityBlockExtension
    {
        public static final IntegerProperty COUNT = TFCBlockStateProperties.COUNT_1_64;

        private static final VoxelShape[] SHAPES = {
                box(0.25, 0, 0.25, 15.75, 2, 15.75),
                box(0.25, 0, 0.25, 15.75, 4, 15.75),
                box(0.25, 0, 0.25, 15.75, 6, 15.75),
                box(0.25, 0, 0.25, 15.75, 8, 15.75),
                box(0.25, 0, 0.25, 15.75, 10, 15.75),
                box(0.25, 0, 0.25, 15.75, 12, 15.75),
                box(0.25, 0, 0.25, 15.75, 14, 15.75),
                Shapes.block()
        };

    public CoinPileBlock(ExtendedProperties properties)
        {
            super(properties);

            registerDefaultState(getStateDefinition().any().setValue(COUNT, 1));
        }

        @Override
        @SuppressWarnings("deprecation")
        public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
        {
            if (direction == Direction.DOWN && !neighborState.isFaceSturdy(level, neighborPos, direction.getOpposite()) && !Helpers.isBlock(neighborState, this))
            {
                level.scheduleTick(currentPos, this, 1);
            }
            return state;
        }

        @Override
        @SuppressWarnings("deprecation")
        public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
        {
            if (!player.isShiftKeyDown())
            {
                // Attempt to remove from the ingot pile, or one above
                // First, climb up the current stack until we locate the top ingot pile
                BlockPos topPos = pos;
                while (Helpers.isBlock(level.getBlockState(topPos.above()), this))
                {
                    topPos = topPos.above();
                }

                // topPos is an ingot pile
                final BlockState topState = level.getBlockState(topPos);
                final int topCoins = topState.getValue(COUNT);

//                level.getBlockEntity(topPos, TFCBlockEntities.INGOT_PILE.get()).ifPresent(pile -> ItemHandlerHelper.giveItemToPlayer(player, pile.removeCoin()));
                level.getBlockEntity(topPos, COIN_PILE.get()).ifPresent(pile -> ItemHandlerHelper.giveItemToPlayer(player, pile.removeCoin()));

                if (topCoins == 1)
                {
                    level.removeBlock(topPos, false);
                }
                else
                {
                    level.setBlock(topPos, topState.setValue(COUNT, topCoins - 1), Block.UPDATE_CLIENTS);
                }
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        @Override
        @SuppressWarnings("deprecation")
        public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random)
        {
            if (!canSurvive(state, level, pos))
            {
                // Neighbor state is not sturdy, so pop off and drop items
                level.destroyBlock(pos, true);
            }
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
        {
            final BlockPos adjacentPos = pos.below();
            final BlockState adjacentState = level.getBlockState(adjacentPos);
            return adjacentState.isFaceSturdy(level, adjacentPos, Direction.UP) || Helpers.isBlock(adjacentState, this);
        }

        @Override
        public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
        {
            final boolean canActuallyHarvest = state.canHarvestBlock(level, pos, player);
            if (player.isCreative() && canActuallyHarvest && level.getBlockEntity(pos) instanceof CoinPileBlockEntity pile)
            {
                // void contents
                pile.removeAllCoins();
            }
            return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
        }

        @Override
        @SuppressWarnings("deprecation")
        public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving)
        {
            if (level.getBlockEntity(pos) instanceof CoinPileBlockEntity pile && newState.getBlock() != this)
            {
                pile.removeAllCoins().forEach(coin -> popResource(level, pos, coin));
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
        {
            super.createBlockStateDefinition(builder.add(COUNT));
        }

        @Override
        @SuppressWarnings("deprecation")
        public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
        {
            return SHAPES[(state.getValue(COUNT) - 1) / 8];
        }
    }