package de.pilz.anotheroregenerator.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.common.IWorldGenerator;
import de.pilz.anotheroregenerator.configuration.GeneralConfig;
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
            final int x = chunkX * 16 + random.nextInt(16) + 8;
            final int z = chunkZ * 16 + random.nextInt(16) + 8;

            if (world.getHeightValue(x, z) > 0 && random.nextInt(3) == 1) {
                for (OreConfigEntry entry : oreConfig.getOres()) {
                    if (entry.enabled && entry.isAdditional && entry.allowInDimension(world.provider.dimensionId)) {
                        final int y = entry.minY + random.nextInt(entry.maxY - entry.minY);

                        for (int l = 0; l < entry.intensity; ++l) {
                            final int xDest = x + random.nextInt(8) - random.nextInt(8);
                            final int yDest = y + random.nextInt(4) - random.nextInt(4);
                            final int zDest = z + random.nextInt(8) - random.nextInt(8);

                            if (world.blockExists(xDest, yDest, zDest) && world.isAirBlock(xDest, yDest, zDest)) {
                                if (isValidDestination(world, entry, xDest, yDest + 1, zDest)) {} // 1
                                else if (isValidDestination(world, entry, xDest, yDest - 1, zDest)) {} // 7
                                else if (isValidDestination(world, entry, xDest, yDest, zDest + 1)) {} // 8
                                else if (isValidDestination(world, entry, xDest, yDest, zDest - 1)) {} // 9
                                else if (isValidDestination(world, entry, xDest + 1, yDest, zDest)) {} // 10
                                else if (isValidDestination(world, entry, xDest - 1, yDest, zDest)) {} // 11
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isValidDestination(World world, OreConfigEntry entry, int x, int y, int z) {
        // Avoid cascade worlgen
        if (!world.blockExists(x, y, z)) {
            return false;
        }

        final Block oreBlock = entry.getOreBlock();
        final Block deepslateOreBlock = entry.getDeepslateOreBlock();
        final Block destBlock = world.getBlock(x, y, z);

        // Sanity checks
        if (destBlock == oreBlock || destBlock == deepslateOreBlock
            || destBlock == Blocks.mob_spawner
            || destBlock == Blocks.end_portal_frame) {
            return false;
        }

        // Only allow solid sides
        if (!destBlock.isSideSolid(world, x, y, z, ForgeDirection.getOrientation(0))) {
            return false;
        }

        // Set block depending on existing one
        if (deepslateOreBlock != null && isDeepslate(destBlock, y)) {
            world.setBlock(x, y, z, deepslateOreBlock, entry.deepslateOreBlockMeta, 2);
        } else if (oreBlock != null) {
            world.setBlock(x, y, z, oreBlock, entry.oreBlockMeta, 2);
        } else {
            return false;
        }

        return true;
    }

    private static boolean isDeepslate(Block block, int y) {
        // Check against block name
        final String blockName = block.getUnlocalizedName();
        for (String deepslateBlock : GeneralConfig.deepslateBlocks) {
            if (blockName.equals(deepslateBlock)) {
                return true;
            }
        }

        // Check height
        if (y <= GeneralConfig.deepslateHeight) {
            return true;
        }

        // Negative
        return false;
    }
}
