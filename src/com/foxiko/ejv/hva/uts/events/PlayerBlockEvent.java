package com.foxiko.ejv.hva.uts.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerBlockEvent implements Listener {

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent e) {
        e.setCancelled(true);
    }
}
