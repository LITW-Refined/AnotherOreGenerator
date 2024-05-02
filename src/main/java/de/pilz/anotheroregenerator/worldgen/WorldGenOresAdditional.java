package de.pilz.anotheroregenerator.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.common.IWorldGenerator;
import de.pilz.anotheroregenerator.configuration.oreconfig.OreConfig;
import de.pilz.anotheroregenerator.configuration.oreconfig.OreConfigEntry;

public class WorldGenOresAdditional implements IWorldGenerator {

    private final OreConfig oreConfig;

    public WorldGenOresAdditional(OreConfig oreConfig) {
        this.oreConfig = oreConfig;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
        IChunkProvider chunkProvider) {
        if (world.getWorldInfo()
            .getTerrainType() != WorldType.FLAT || world.getWorldInfo()
                .getGeneratorOptions()
                .contains("decoration")) {
            int x = chunkX * 16 + random.nextInt(16) + 8;
            int z = chunkZ * 16 + random.nextInt(16) + 8;

            if ((world.getHeightValue(x, z) > 0) && random.nextInt(3) == 1) {
                for (OreConfigEntry entry : oreConfig.getOres()) {
                    if (entry.enabled && entry.isAdditional && entry.allowInDimension(world.provider.dimensionId)) {
                        int y = entry.minY + random.nextInt(entry.maxY - entry.minY);

                        for (int l = 0; l < entry.intensity; ++l) {
                            int i1 = x + random.nextInt(8) - random.nextInt(8);
                            int j1 = y + random.nextInt(4) - random.nextInt(4);
                            int k1 = z + random.nextInt(8) - random.nextInt(8);

                            if (world.isAirBlock(i1, j1, k1)) {
                                if (isValidDestination(world, entry, i1, j1 + 1, k1)) {} // 1
                                else if (isValidDestination(world, entry, i1, j1 - 1, k1)) {} // 7
                                else if (isValidDestination(world, entry, i1, j1, k1 + 1)) {} // 8
                                else if (isValidDestination(world, entry, i1, j1, k1 - 1)) {} // 9
                                else if (isValidDestination(world, entry, i1 + 1, j1, k1)) {} // 10
                                else if (isValidDestination(world, entry, i1 - 1, j1, k1)) {} // 11
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isValidDestination(World world, OreConfigEntry entry, int i1, int j1, int k1) {
        Block oreBlock = entry.getOreBlock();
        Block deepslateOreBlock = entry.getDeepslateOreBlock();
        Block destBlock = world.getBlock(i1, j1, k1);

        if (destBlock == oreBlock || destBlock == deepslateOreBlock
            || destBlock == Blocks.mob_spawner
            || destBlock == Blocks.end_portal_frame) {
            return false;
        }

        if (destBlock.isSideSolid(world, i1, j1, k1, ForgeDirection.getOrientation(0))) {
            return false;
        }

        if (deepslateOreBlock != null && isDeepslate(destBlock)) {
            world.setBlock(i1, j1, k1, deepslateOreBlock, entry.deepslateOreBlockMeta, 2);
        } else if (oreBlock != null) {
            world.setBlock(i1, j1, k1, oreBlock, entry.oreBlockMeta, 2);
        } else {
            return false;
        }

        return true;
    }

    private static boolean isDeepslate(Block block) {
        return block.getUnlocalizedName()
            .equals("etfuturum.deepslate");
    }
}
