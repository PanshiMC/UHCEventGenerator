package net.panshimc.uhc.event.generator;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.WorldGenerator;
import net.panshimc.uhc.event.util.LocationUtil;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JustNetherGenerator extends BlockPopulator {
    private final Map<Material,Material> replaceMap;

    public JustNetherGenerator() {
        this.replaceMap = new HashMap<>();

        replaceMap.put(Material.WATER,Material.LAVA);
        replaceMap.put(Material.STATIONARY_WATER,Material.LAVA);
        replaceMap.put(Material.OBSIDIAN,Material.LAVA);
        replaceMap.put(Material.STONE,Material.NETHERRACK);
        replaceMap.put(Material.SAND,Material.SOUL_SAND);
        replaceMap.put(Material.GRASS,Material.NETHERRACK);
        replaceMap.put(Material.DIRT,Material.NETHERRACK);
        replaceMap.put(Material.SANDSTONE,Material.NETHERRACK);
        replaceMap.put(Material.SANDSTONE_STAIRS,Material.NETHER_BRICK_STAIRS);
        replaceMap.put(Material.FENCE,Material.NETHER_FENCE);
        replaceMap.put(Material.STONE_SLAB2,Material.STEP);

        System.out.println("init...");
    }

    public void populate(World world, Random random, Chunk chunk) {

        int bx = chunk.getX()<<4;
        int bz = chunk.getZ()<<4;

        for(int xx = bx; xx < bx+16; xx++) {
            for(int zz = bz; zz < bz+16; zz++) {
                for(int yy = 0; yy < 128; yy++) {
                    Block block = chunk.getBlock(xx, yy, zz);
                    if (replaceMap.containsKey(block.getType())){
                        block.setType(replaceMap.get(block.getType()));
                    }
                }
            }
        }


    }

}
