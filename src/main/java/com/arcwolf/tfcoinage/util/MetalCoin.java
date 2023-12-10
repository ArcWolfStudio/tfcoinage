package com.arcwolf.tfcoinage.util;

import com.google.gson.JsonObject;
import net.dries007.tfc.util.JsonHelpers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.ForgeRegistries;

public class MetalCoin {

    public static Ingredient coins;
    private final ResourceLocation id;
    private final ResourceLocation textureId;
    private final String translationKey;
    private final Fluid fluid;
    private final float meltTemperature;
    private final float specificHeatCapacity;

    public MetalCoin(Ingredient coins, ResourceLocation id, ResourceLocation textureId, String translationKey, Fluid fluid, float meltTemperature, float specificHeatCapacity) {
        this.coins = coins;
        this.id = id;
        this.textureId = textureId;
        this.translationKey = translationKey;
        this.fluid = fluid;
        this.meltTemperature = meltTemperature;
        this.specificHeatCapacity = specificHeatCapacity;
    }
//    @Nullable
//    public Metal getFromCoin (ItemStack stack){
//        for (Metal metal : MANAGER.getValues()){
//            if(isCoin(stack)){
//                return metal;
//            }
//        }
//        return null;
//    }
//    public boolean isCoin(ItemStack stack){
//        return coins.test(stack);
//    }
    public Ingredient getCoinIngredient(){
        return coins;
    }

    public MetalCoin (ResourceLocation id, JsonObject json) {
        this.id = id;
        this.textureId = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
        this.fluid = (Fluid)JsonHelpers.getRegistryEntry(json, "fluid", ForgeRegistries.FLUIDS);
        this.specificHeatCapacity = JsonHelpers.getAsFloat(json, "specific_heat_capacity");
        this.meltTemperature = JsonHelpers.getAsFloat(json, "melt_temperature");
        String var10001 = id.getNamespace();
        this.translationKey = "metal." + var10001 + "." + id.getPath();
        this.coins = Ingredient.fromJson(JsonHelpers.get(json, "coins"));

    }

    public MetalCoin (ResourceLocation id, FriendlyByteBuf buffer) {
        this.id = id;
        this.textureId = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
        this.fluid = (Fluid)buffer.readRegistryIdUnsafe(ForgeRegistries.FLUIDS);
        this.meltTemperature = buffer.readFloat();
        this.specificHeatCapacity = buffer.readFloat();
        this.translationKey = buffer.readUtf();
        this.coins = Ingredient.fromNetwork(buffer);
    }

    public MetalCoin (ResourceLocation id) {
        this.id = id;
        this.textureId = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
        this.fluid = Fluids.EMPTY;
        this.meltTemperature = 0.0F;
        this.specificHeatCapacity = 0.0F;
        this.translationKey = "";
        this.coins = Ingredient.EMPTY;
    }

}
