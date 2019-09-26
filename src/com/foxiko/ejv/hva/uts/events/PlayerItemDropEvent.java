package com.foxiko.ejv.hva.uts.events;

import com.foxiko.ejv.hva.uts.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerItemDropEvent implements Listener {

    @EventHandler
    public void dropItem(PlayerDropItemEvent e) {
        if(e.getItemDrop().getItemStack().getType() == Material.BARRIER) {
            e.setCancelled(true);
            return;
        }
        if(e.getPlayer().getInventory().getItem(0) == null)
            Main.game.getPlayerTeam(e.getPlayer()).getPlayers().get(e.getPlayer()).setSingleHandedWeapon(null);

        if(e.getPlayer().getInventory().getItem(1) == null)
            Main.game.getPlayerTeam(e.getPlayer()).getPlayers().get(e.getPlayer()).setTwoHandedWeapon(null);

        if(e.getPlayer().getInventory().getItem(2) == null)
            Main.game.getPlayerTeam(e.getPlayer()).getPlayers().get(e.getPlayer()).setMeleeWeapon(null);

    }
}
