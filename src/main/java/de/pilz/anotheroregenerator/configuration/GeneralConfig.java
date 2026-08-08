package de.pilz.anotheroregenerator.configuration;

import com.gtnewhorizon.gtnhlib.config.Config;

import de.pilz.anotheroregenerator.AnotherOreGenerator;

@Config(modid = AnotherOreGenerator.MODID)
public class GeneralConfig {

    @Config.DefaultInt(0)
    @Config.Comment("Defines the deepslate height where deepslate is common.\nUse this to ensure all ores generated starting at a specific height and below are deepslate and not normal.\nThis fixes stone ares on deepslate, as sometimes stone ores are not converted to deesplate ores correctly.\n0 disables this feature.")
    public static int deepslateHeight;

    @Config.DefaultStringList({ "etfuturum.deepslate" })
    @Config.Comment("A list of unlocalized block names for deepslate detection. Mainly used for additional ore generation.")
    public static String[] deepslateBlocks;
}
