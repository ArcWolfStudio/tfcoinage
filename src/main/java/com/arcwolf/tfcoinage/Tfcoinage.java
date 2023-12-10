package com.arcwolf.tfcoinage;

import com.arcwolf.tfcoinage.init.ItemInit;
import com.arcwolf.tfcoinage.render.blockentity.CoinPileBlockEntityRenderer;
import com.arcwolf.tfcoinage.util.TFCoinInteraction;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

import static com.arcwolf.tfcoinage.blockentities.TfCoinBlockEntities.COIN_PILE;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Tfcoinage.MOD_ID)
//@Mod.EventBusSubscriber(modid = Tfcoinage.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class Tfcoinage {

    public static final String MOD_ID = "tfcoinage";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Tfcoinage() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TFCoinInteraction.registerDefaultInteractions();

        ItemInit.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::registerEntityRenderers);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(COIN_PILE.get(), ctx -> new CoinPileBlockEntityRenderer());
    }


}
