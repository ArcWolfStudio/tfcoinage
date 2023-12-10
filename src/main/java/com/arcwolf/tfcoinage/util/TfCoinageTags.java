package com.arcwolf.tfcoinage.util;

import net.dries007.tfc.util.Helpers;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;


public class TfCoinageTags {
    public static class Items{
        public static final TagKey<Item> PILEABLE_COINS = create("pileable_coins");

        private static TagKey<Item> create(String id)
        {
            return TagKey.create(Registry.ITEM_REGISTRY, Helpers.identifier(id));
        }
    }
}
