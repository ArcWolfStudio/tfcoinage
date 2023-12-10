package com.arcwolf.tfcoinage.blocks;

import com.arcwolf.tfcoinage.blockentities.TfCoinBlockEntities;
import com.arcwolf.tfcoinage.blocks.devices.CoinPileBlock;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.arcwolf.tfcoinage.Tfcoinage.MOD_ID;

public class CoinBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,MOD_ID);

    public static final RegistryObject<Block> COIN_PILE = register("coin_pile", ()-> new CoinPileBlock((ExtendedProperties.of(Material.METAL, MaterialColor.METAL).strength(4, 60).sound(SoundType.METAL).noOcclusion().blockEntity(TfCoinBlockEntities.COIN_PILE))));


//    public static final EnumMap<Metal.Default, Object> COIN_PILE = Helpers.mapOfKeys(Metal.Default.class, metal -> Helpers.mapOfKeys(Metal.ItemType.class, type -> type.has((Metal.Default) metal), type ->
//        register("coin_pile",() -> new CoinPileBlock((ExtendedProperties.of(Material.METAL, MaterialColor.METAL).strength(4, 60).sound(SoundType.METAL).noOcclusion().blockEntity(CoinPileBlockEntity.COIN_PILE))))));
//
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, CreativeModeTab group)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().tab(group)));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, Item.Properties blockItemProperties)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, blockItemProperties));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        return RegistrationHelpers.registerBlock(TFCBlocks.BLOCKS, TFCItems.ITEMS, name, blockSupplier, blockItemFactory);
    }

}
