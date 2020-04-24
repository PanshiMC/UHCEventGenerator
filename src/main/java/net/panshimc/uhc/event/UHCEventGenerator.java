package net.panshimc.uhc.event;

import net.panshimc.uhc.event.generator.JustNetherGenerator;
import net.panshimc.uhc.event.random.RandomWorld;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class UHCEventGenerator extends JavaPlugin {

    @Override
    public void onEnable() {
        JustNetherGenerator generator = new JustNetherGenerator();

        this.getServer()
                .getPluginManager()
                .registerEvents(new RandomWorld(),this);

        this.getServer()
                .getPluginManager()
                .registerEvents(new Listener() {

                    @EventHandler
                    public void worldInit(WorldInitEvent event){
                        event.getWorld().getPopulators().add(new JustNetherGenerator());
                    }

                    @EventHandler(priority = EventPriority.MONITOR)
                    public void onBlockChange(BlockFromToEvent event){
                        if (event.getToBlock().getType() == Material.OBSIDIAN){
                            event.setCancelled(true);
                        }
                    }
                }, this);

    }
}
