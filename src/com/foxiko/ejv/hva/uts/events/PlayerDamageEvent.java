package com.foxiko.ejv.hva.uts.events;

import com.foxiko.ejv.hva.uts.Main;
import com.foxiko.ejv.hva.uts.objects.Team;
import com.foxiko.ejv.hva.uts.objects.TeamPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Arrays;

import static com.foxiko.ejv.hva.uts.Main.game;

public class PlayerDamageEvent implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void damageByEntity(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) {
            return;
        }

        Player damager = (Player) e.getDamager();
        Player player = (Player) e.getEntity();

        TeamPlayer teamDamager = game.getPlayerTeam(damager).getPlayers().get(damager);
        TeamPlayer teamPlayer = game.getPlayerTeam(player).getPlayers().get(player);

        if(!Arrays.asList(0,1,2).contains(damager.getInventory().getHeldItemSlot())) {
            return;
        }
        teamDamager.setLastUsedSlot(damager.getInventory().getHeldItemSlot());

        teamPlayer.setHp(player.getHealth());
        teamPlayer.setLastDamager(teamDamager);

    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void damage(EntityDamageEvent e)
    {
        if(!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();

        if(game.getPlayerTeam(p).getPlayers().get(p).isDead()) {
            e.setCancelled(true);
            return;
        }

        if (p.getHealth() - e.getFinalDamage() <= 0) { //So the player will die after this event ran
            e.setCancelled(true);
            /*
             * Canceling this event so the player will not have to click "RESPAWN" because
             * the player will not respawn until the next round and it is a known bug that
             * the respawn screen sometimes will freeze
             */

            Team team = game.getPlayerTeam(p);
            team.diePlayer(p, null);

            TeamPlayer teamPlayer = team.getPlayers().get(p);

            int slot = teamPlayer.getLastUsedSlot();
            if(p.getInventory().getItem(slot) == null)
                return;
            if(p .getInventory().getItem(slot).getType() == Material.BARRIER)
                return;

            //Dropping the last used item
            p.getLocation().getWorld().dropItem(p.getLocation(), p.getInventory().getItem(slot));

            //Add 2 hearts or 4 hp to the last player who damaged the player
            double health = teamPlayer.getLastDamager().getHp() + 4;
            if(health > 20)
                health = 20;
            teamPlayer.getLastDamager().setHp(health);
        }
    }
}
