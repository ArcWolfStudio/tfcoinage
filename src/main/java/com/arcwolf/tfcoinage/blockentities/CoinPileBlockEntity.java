package com.arcwolf.tfcoinage.blockentities;

import com.arcwolf.tfcoinage.util.MetalCoin;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.dries007.tfc.common.blockentities.TFCBlockEntity;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.List;
import java.util.function.Supplier;

import static net.dries007.tfc.util.Metal.MANAGER;


public class CoinPileBlockEntity extends TFCBlockEntity {

    private final List<ItemStack> stacks;
    private final List<Metal> cachedMetals;

    public CoinPileBlockEntity(BlockPos pos, BlockState state)
    {
        super(TfCoinBlockEntities.COIN_PILE.get(), pos, state);

        stacks = new ArrayList<>();
        cachedMetals = new ArrayList<>();
    }

    public void addCoin(ItemStack stack)
    {
        assert stacks.size() == cachedMetals.size();

        stacks.add(stack);
        cachedMetals.add(null);
        markForSync();
    }

    public List<ItemStack> removeAllCoins()
    {
        assert stacks.size() == cachedMetals.size();

        final List<ItemStack> stacks = new ArrayList<>(this.stacks);
        this.stacks.clear();
        this.cachedMetals.clear();
        markForSync();
        return stacks;
    }

    public ItemStack removeCoin()
    {
        assert stacks.size() == cachedMetals.size();

        final ItemStack remove = stacks.remove(stacks.size() - 1);
        cachedMetals.remove(cachedMetals.size() - 1);
        markForSync();
        return remove;
    }


    /**
     * Returns a cached metal for the given side, if present, otherwise grabs from the cache.
     * The metal is defined by checking what metal the stack would melt into if heated.
     * Any other items turn into {@link Metal#unknown()}.
     */
    public Metal getOrCacheMetal(int index)
    {
        assert stacks.size() == cachedMetals.size();

        if (index >= stacks.size())
        {
            return Metal.unknown();
        }

        final ItemStack stack = stacks.get(index);

        Metal metal = cachedMetals.get(index);
        if (metal == null)
        {
            metal = getFromCoin(stack);
            if (metal == null)
            {
                metal = Metal.unknown();
            }
            cachedMetals.set(index, metal);
        }
        return metal;
    }


    private Metal getFromCoin(ItemStack stack) {
        for (Metal metal : MANAGER.getValues()){
            if(isCoin(stack)){
                return metal;
            }
        }
        return null;
    }
    public boolean isCoin(ItemStack stack){
        return MetalCoin.coins.test(stack);
    }
    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        tag.put("stacks", Helpers.writeItemStacksToNbt(stacks));
        super.saveAdditional(tag);
    }

    @Override
    protected void loadAdditional(CompoundTag tag)
    {
        Helpers.readItemStacksFromNbt(stacks, tag.getList("stacks", Tag.TAG_COMPOUND));
        cachedMetals.clear();
        for (int i = 0; i < stacks.size(); i++)
        {
            cachedMetals.add(null);
        }
        super.loadAdditional(tag);
    }

//    public void fillTooltip(Consumer<Component> tooltip)
//    {
//        final Object2IntMap<Metal> map = new Object2IntOpenHashMap<>();
//        for (Metal metal : cachedMetals)
//        {
//            if (metal != null)
//            {
//                map.mergeInt(metal, 1, Integer::sum);
//            }
//        }
//        map.forEach((metal, ct) -> tooltip.accept(Helpers.literal("" + ct + "x ").append(metal.getDisplayName())));
//    }
}
