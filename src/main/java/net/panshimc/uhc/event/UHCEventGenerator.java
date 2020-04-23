package net.panshimc.uhc.event;

import net.panshimc.uhc.event.generator.JustNetherGenerator;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class UHCEventGenerator extends JavaPlugin {

    @Override
    public void onEnable() {

        this.getServer()
                .getPluginManager()
                .registerEvents(new Listener() {
                    @EventHandler
                    public void onLoadChunk(WorldInitEvent event){
                        event.getWorld().getPopulators().add(new JustNetherGenerator());
                    }
                }, this);

    }
}
