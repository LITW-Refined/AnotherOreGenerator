package de.pilz.anotheroregenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
    modid = AnotherOreGenerator.MODID,
    version = Tags.VERSION,
    name = AnotherOreGenerator.MODNAME,
    acceptedMinecraftVersions = "[1.7.10]",
    acceptableRemoteVersions = "*")
public class AnotherOreGenerator {

    public static final String MODID = "anotheroregenerator";
    public static final String MODGROUP = "de.pilz";
    public static final String MODNAME = "Another Ore Generator";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = MODGROUP + "." + MODID + ".client.AnotherOreGeneratorClientProxy",
        serverSide = MODGROUP + "." + MODID + ".AnotherOreGeneratorProxy")
    public static AnotherOreGeneratorProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
