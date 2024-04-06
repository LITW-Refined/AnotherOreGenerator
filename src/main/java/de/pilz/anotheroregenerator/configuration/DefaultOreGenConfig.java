package de.pilz.anotheroregenerator.configuration;

import com.gtnewhorizon.gtnhlib.config.Config;

import de.pilz.anotheroregenerator.AnotherOreGenerator;

@Config(modid = AnotherOreGenerator.MODID, category = "default_oregen")
public class DefaultOreGenConfig {

    @Config.DefaultBoolean(false)
    @Config.Comment("Disables the vanilla ore generation for iron ore.")
    public static boolean disableVanillaOreGenIron;

    @Config.DefaultBoolean(false)
    @Config.Comment("Disables the vanilla ore generation for coal ore.")
    public static boolean disableVanillaOreGenCoal;

    @Config.DefaultBoolean(false)
    @Config.Comment("Disables the vanilla ore generation for gold ore.")
    public static boolean disableVanillaOreGenGold;

    @Config.DefaultBoolean(false)
    @Config.Comment("Disables the vanilla ore generation for redstone ore.")
    public static boolean disableVanillaOreGenRedstone;

    @Config.DefaultBoolean(false)
    @Config.Comment("Disables the vanilla ore generation for lapis ore.")
    public static boolean disableVanillaOreGenLapis;

    @Config.DefaultBoolean(false)
    @Config.Comment("Disables the vanilla ore generation for diamond ore.")
    public static boolean disableVanillaOreGenDiamond;

    @Config.DefaultBoolean(false)
    @Config.Comment("Disables the vanilla ore generation for gravel within stone.")
    public static boolean disableVanillaOreGenGravel;

    @Config.DefaultBoolean(false)
    @Config.Comment("Disables the vanilla ore generation for dirt within stone.")
    public static boolean disableVanillaOreGenDirt;

    @Config.DefaultBoolean(false)
    @Config.Comment("Disables the vanilla ore generation for quartz ore.")
    public static boolean disableVanillaOreGenQuartz;

    @Config.DefaultBoolean(false)
    @Config.Comment("Disables the custom ore generation wich is used by some mods. Unfortunally the most mods don't generate ores using this event, so it will be requied for you to disable ore generation in your mod configs.")
    public static boolean disableCustomOreGen;
}
