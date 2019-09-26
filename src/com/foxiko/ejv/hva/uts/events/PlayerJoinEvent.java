package com.foxiko.ejv.hva.uts.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static com.foxiko.ejv.hva.uts.Main.game;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent e) {
        game.putPlayerInTeam(e.getPlayer());
    }

    

}
