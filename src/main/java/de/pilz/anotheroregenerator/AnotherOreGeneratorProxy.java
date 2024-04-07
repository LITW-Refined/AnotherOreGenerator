package de.pilz.anotheroregenerator;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.pilz.anotheroregenerator.configuration.ConfigManager;
import de.pilz.anotheroregenerator.configuration.oreconfig.OreConfig;
import de.pilz.anotheroregenerator.events.OreGenEventHandler;
import de.pilz.anotheroregenerator.worldgen.WorldGenOres;
import de.pilz.anotheroregenerator.worldgen.WorldGenOresAdditional;

public class AnotherOreGeneratorProxy {

    private String oreConfigFolder;
    private OreConfig oreConfig;
    private WorldGenOres worldGenOres;
    private WorldGenOresAdditional worldGenOredAdditional;
    private OreGenEventHandler oreGenEventHandler;

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        AnotherOreGenerator.LOG.info("I am " + AnotherOreGenerator.MODNAME + " at version " + Tags.VERSION);

        // Load mod config
        ConfigManager.initConfig();

        // Load ore configs
        oreConfigFolder = new File(event.getModConfigurationDirectory(), AnotherOreGenerator.MODID).getPath();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        // Load ore configs
        oreConfig = OreConfig.loadConfig(oreConfigFolder);

        // Ore generation event
        oreGenEventHandler = new OreGenEventHandler();
        MinecraftForge.ORE_GEN_BUS.register(oreGenEventHandler);

        // World generation
        worldGenOres = new WorldGenOres(oreConfig);
        worldGenOredAdditional = new WorldGenOresAdditional(oreConfig);
        GameRegistry.registerWorldGenerator(worldGenOres, 0);
        GameRegistry.registerWorldGenerator(worldGenOredAdditional, 0);
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}
