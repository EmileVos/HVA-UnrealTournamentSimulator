package com.foxiko.ejv.hva.uts.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.List;

public class PlayerInventoryClickEvent implements Listener {

    @EventHandler
    public void playerInventoryClickEvent(InventoryClickEvent e) {
        if(!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        if(e.getClickedInventory() != player.getInventory())
            return;
        if(Arrays.asList(0,1,2).contains(e.getSlot())) {
            e.setCancelled(true);
        }

        //make sure the item is a item and has an type
        if(e.getCurrentItem() == null )
            return;
        if(e.getCurrentItem().getType() == null)
            return;

        if(e.getCurrentItem().getType() == Material.POTION) {
            ItemStack item = e.getCurrentItem();
            PotionMeta meta = (PotionMeta) item.getItemMeta();

            for(PotionEffect effect : meta.getCustomEffects()) {
                player.addPotionEffect(effect, true);
            }
        }
    }
}
