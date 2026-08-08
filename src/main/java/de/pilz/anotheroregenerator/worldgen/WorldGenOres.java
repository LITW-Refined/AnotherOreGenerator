package de.pilz.anotheroregenerator.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;
import de.pilz.anotheroregenerator.configuration.GeneralConfig;
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
                final int deepslateHeight = GeneralConfig.deepslateHeight;
                final int veinSize = entry.minVeinSize + random.nextInt(entry.maxVeinSize - entry.minVeinSize);

                // Minable for normal blocks
                final WorldGenMinable minable = new WorldGenMinable(
                    entry.getOreBlock(),
                    entry.oreBlockMeta,
                    veinSize,
                    entry.getSourceBlock());

                // Minable for deepslate blocks
                final WorldGenMinable minableDeepslate;
                final Block deepslateOreBlock = entry.getDeepslateOreBlock();
                if (entry.minY > deepslateHeight || deepslateOreBlock == null) {
                    minableDeepslate = null;
                } else {
                    minableDeepslate = new WorldGenMinable(
                        entry.getDeepslateOreBlock(),
                        entry.deepslateOreBlockMeta,
                        veinSize,
                        entry.getSourceBlock());
                }

                // Generate
                for (int i = 0; i < entry.intensity; ++i) {
                    final int posX = chunkX * 16 + random.nextInt(16);
                    final int posY = entry.minY + random.nextInt(entry.maxY - entry.minY);
                    final int posZ = chunkZ * 16 + random.nextInt(16);

                    if (posY <= deepslateHeight && minableDeepslate != null) {
                        minableDeepslate.generate(world, random, posX, posY, posZ);
                    } else {
                        minable.generate(world, random, posX, posY, posZ);
                    }
                }
            }
        }
    }
}
