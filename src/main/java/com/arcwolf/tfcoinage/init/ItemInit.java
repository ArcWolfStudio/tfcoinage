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
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,MOD_ID);
    public static final RegistryObject<Item> BISMUTH_COIN = ITEMS.register("bismuth_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BISMUTH_BRONZE_COIN = ITEMS.register("bismuth_bronze_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BLACK_BRONZE_COIN = ITEMS.register("black_bronze_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BRONZE_COIN = ITEMS.register("bronze_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BRASS_COIN = ITEMS.register("brass_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> COPPER_COIN = ITEMS.register("copper_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> GOLD_COIN = ITEMS.register("gold_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> NICKEL_COIN = ITEMS.register("nickel_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ROSE_GOLD_COIN = ITEMS.register("rose_gold_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SILVER_COIN = ITEMS.register("silver_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> TIN_COIN = ITEMS.register("tin_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ZINC_COIN = ITEMS.register("zinc_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STERLING_SILVER_COIN = ITEMS.register("sterling_silver_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> WROUGHT_IRON_COIN = ITEMS.register("wrought_iron_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STEEL_COIN = ITEMS.register("steel_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BLACK_STEEL_COIN = ITEMS.register("black_steel_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> RED_STEEL_COIN = ITEMS.register("red_steel_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BLEU_STEEL_COIN = ITEMS.register("blue_steel_coin", ()-> new Item (new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public ItemInit(){

    }
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
