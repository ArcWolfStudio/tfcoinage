package com.arcwolf.tfcoinage.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.arcwolf.tfcoinage.Tfcoinage.MOD_ID;

public class ItemInit {
    public static final DeferredRegister<Item> COIN_ITEM = DeferredRegister.create(ForgeRegistries.ITEMS,MOD_ID);
    public static final RegistryObject<Item> BISMUTH_COIN = COIN_ITEM.register("bismuth_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BISMUTH_BRONZE_COIN = COIN_ITEM.register("bismuth_bronze_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BLACK_BRONZE_COIN = COIN_ITEM.register("black_bronze_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BRONZE_COIN = COIN_ITEM.register("bronze_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BRASS_COIN = COIN_ITEM.register("brass_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> COPPER_COIN = COIN_ITEM.register("copper_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> GOLD_COIN = COIN_ITEM.register("gold_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> NICKEL_COIN = COIN_ITEM.register("nickel_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ROSE_GOLD_COIN = COIN_ITEM.register("rose_gold_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SILVER_COIN = COIN_ITEM.register("silver_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> TIN_COIN = COIN_ITEM.register("tin_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ZINC_COIN = COIN_ITEM.register("zinc_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STERLING_SILVER_COIN = COIN_ITEM.register("sterling_silver_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> WROUGHT_IRON_COIN = COIN_ITEM.register("wrought_iron_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STEEL_COIN = COIN_ITEM.register("steel_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BLACK_STEEL_COIN = COIN_ITEM.register("black_steel_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> RED_STEEL_COIN = COIN_ITEM.register("red_steel_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BLEU_STEEL_COIN = COIN_ITEM.register("blue_steel_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public ItemInit(){

    }
    public static void register(IEventBus eventBus){
        COIN_ITEM.register(eventBus);
    }
}
