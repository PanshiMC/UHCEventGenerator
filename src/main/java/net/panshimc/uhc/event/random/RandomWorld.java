package net.panshimc.uhc.event.random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RandomWorld implements Listener {
    private final Map<UUID,Map<Material,Material>> randomMap;
    private final Random random;

    public RandomWorld() {
        this.randomMap = new HashMap<>();
        this.random = new Random();
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event){
        Map<Material,Material> map = new HashMap<>();
        List<Material> temp = Arrays.asList(Material.values());
        List<Material> todo = new ArrayList<>(temp);
        List<Material> replace = new ArrayList<>(temp);

        while (todo.size() > 1){
            Material material = todo.get(random.nextInt(todo.size()));
            Material result = replace.get(random.nextInt(replace.size()));

            map.put(material,result);

            todo.remove(material);
            replace.remove(result);
        }

        randomMap.put(event.getUniqueId(),map);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Map<Material, Material> map = randomMap.get(event.getPlayer().getUniqueId());

        if (map.containsKey(event.getBlock().getType())){
            Material material = map.get(event.getBlock().getType());
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            event.getBlock()
                    .getLocation()
                    .getWorld()
                    .dropItemNaturally(event.getBlock().getLocation(),new ItemStack(material));
        }
    }

}
