package com.arcwolf.tfcoinage.blockentities;

import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import com.arcwolf.tfcoinage.blocks.CoinBlocks;

import java.util.Map;
import java.util.function.Supplier;

import static net.dries007.tfc.common.blockentities.TFCBlockEntities.BLOCK_ENTITIES;

public class TfCoinBlockEntities {
    public static final RegistryObject<BlockEntityType<CoinPileBlockEntity>> COIN_PILE = register("coin_pile", CoinPileBlockEntity::new, CoinBlocks.COIN_PILE);
    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block> block)
    {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block);
    }
}
