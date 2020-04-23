package net.panshimc.uhc.event.generator;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class JustNetherGenerator extends BlockPopulator {
    private Map<Material,Material> replaceMap;

    public JustNetherGenerator() {
        this.replaceMap = new HashMap<>();

        replaceMap.put(Material.WATER,Material.LAVA);
        replaceMap.put(Material.STATIONARY_WATER,Material.STATIONARY_LAVA);
        replaceMap.put(Material.STONE,Material.NETHERRACK);
        replaceMap.put(Material.SAND,Material.SOUL_SAND);
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int x = chunk.getX();
        int z = chunk.getZ();

        for (int posX = x; posX < x + 16; posX++) {
            for (int posZ = z; posZ < z + 16; posZ++) {
                for (int posY = 0; posY < 256; posY++) {
                    Block block = chunk.getBlock(posX, posY, posZ);
                    if (replaceMap.containsKey(block.getType())){
                        block.setType(replaceMap.get(block.getType()));
                    }
                }
            }
        }
    }


}
