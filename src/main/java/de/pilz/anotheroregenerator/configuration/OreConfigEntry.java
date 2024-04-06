package de.pilz.anotheroregenerator.configuration;

import org.apache.commons.lang3.ArrayUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class OreConfigEntry {

    public String oreModName;
    public String oreBlockName;
    public int oreBlockMeta = 0;

    public String sourceModName = "minecraft";
    public String sourceBlockName = "stone";

    public int blocksPerVein;
    public int minVeinSize;
    public int maxVeinSize;
    public int minY;
    public int maxY;

    public boolean isAdditional = false;
    public int maxAdditionalY = 15;
    public int minAdditionalY = 25;

    public int[] dimensionIdsBlacklist = new int[0];
    public boolean dimensionIdsBlacklistAsWhitelist = false;

    public boolean allowInDimension(int dimensionId) {
        return ArrayUtils.contains(dimensionIdsBlacklist, dimensionId) == dimensionIdsBlacklistAsWhitelist;
    }

    public Block getSourceBlock() {
        return GameRegistry.findBlock(sourceModName, sourceBlockName);
    }

    public Block getOreBlock() {
        return GameRegistry.findBlock(oreModName, oreBlockName);
    }
}
