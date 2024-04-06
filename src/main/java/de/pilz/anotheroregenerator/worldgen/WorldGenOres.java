package de.pilz.anotheroregenerator.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;
import de.pilz.anotheroregenerator.configuration.oreconfig.OreConfig;
import de.pilz.anotheroregenerator.configuration.oreconfig.OreConfigEntry;

public class WorldGenOres implements IWorldGenerator {

    private final OreConfig oreConfig;

    public WorldGenOres(OreConfig oreConfig) {
        this.oreConfig = oreConfig;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
        IChunkProvider chunkProvider) {
        for (OreConfigEntry entry : oreConfig.getOres()) {
            if (entry.enabled && !entry.isAdditional && entry.allowInDimension(world.provider.dimensionId)) {
                final int veinSize = entry.minVeinSize + random.nextInt(entry.maxVeinSize - entry.minVeinSize);
                final WorldGenMinable minable = new WorldGenMinable(
                    entry.getOreBlock(),
                    entry.oreBlockMeta,
                    veinSize,
                    entry.getSourceBlock());

                for (int i = 0; i < entry.blocksPerVein; ++i) {
                    final int posX = chunkX + random.nextInt(16);
                    final int posY = entry.minY + random.nextInt(entry.maxY - entry.minY);
                    final int posZ = chunkZ + random.nextInt(16);
                    minable.generate(world, random, posX, posY, posZ);
                }
            }
        }
    }
}
