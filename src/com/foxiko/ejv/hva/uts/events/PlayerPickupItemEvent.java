package com.foxiko.ejv.hva.uts.events;

import com.foxiko.ejv.hva.uts.Main;
import com.foxiko.ejv.hva.uts.items.armor.DiamondArmor;
import com.foxiko.ejv.hva.uts.items.armor.GoldArmor;
import com.foxiko.ejv.hva.uts.items.armor.IronArmor;
import com.foxiko.ejv.hva.uts.items.weapons.DiamondCleaver;
import com.foxiko.ejv.hva.uts.objects.Armor;
import com.foxiko.ejv.hva.uts.objects.TeamPlayer;
import com.foxiko.ejv.hva.uts.objects.Weapon;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import static com.foxiko.ejv.hva.uts.Main.game;

public class PlayerPickupItemEvent implements Listener {

    @EventHandler
    public void pickupPowerUp(EntityPickupItemEvent e) {

        if(e.getItem().getItemStack().getType() == Material.POTION) {
            ItemStack item = e.getItem().getItemStack();
            Location location = e.getItem().getLocation();
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    location.getWorld().dropItem(location, item.clone());
                }
            });
        }
    }

    @EventHandler
    public void pickupItem(EntityPickupItemEvent e) {
        if(!(e.getEntity() instanceof Player))
            return;
        Player player = (Player) e.getEntity();
        TeamPlayer teamPlayer = game.getPlayerTeam(player).getPlayers().get(player);
        player.sendMessage(e.getItem().getItemStack().getType().toString());

        ItemStack stack = e.getItem().getItemStack();
        ItemMeta meta = stack.getItemMeta();
        if(!meta.hasLore()) return;
        e.getItem().remove();
        e.setCancelled(true);

        /*
         * The reason i use an interpreter is that if you perform the #World.dropItem(itemStack, location)
         * and pickup the item again the EntityPickupItemEvent is called, and the #EntityPickupItemEvent.getItem().getItemStack()
         * is not an extended class of ItemStack (Like ItemStack > Weapon)
         *
         * Thats why i put a lore the folliwin
         */
        switch (meta.getLore().get(meta.getLore().size() - 3).substring(2).toUpperCase()) {
            case "ARMOR": {
                armorInterpreter(stack, meta, player, teamPlayer);
                break;
            }
            case "WEAPON": {
                weaponInterpreter(stack, meta, player, teamPlayer);
                break;
            }
        }
    }

    public void weaponInterpreter(ItemStack weapon, ItemMeta meta, Player player, TeamPlayer teamPlayer) {
        String weaponType = meta.getLore().get(meta.getLore().size() - 2).substring(2).toUpperCase();
        if(DiamondCleaver.isWeapon(weaponType)) {
            setWeapon(new DiamondCleaver(), teamPlayer);
        }
    }

    public void setWeapon(Weapon weapon, TeamPlayer player) {
        switch(weapon.getWeaponType()) {
            case TWO_HANDED: {
                player.setTwoHandedWeapon(weapon);
                break;
            }
            case SINGLE_HANDED: {
                player.setSingleHandedWeapon(weapon);
                break;
            }
            case MELEE: {
                player.setMeleeWeapon(weapon);
                break;
            }
        }
    }

    public void armorInterpreter(ItemStack armor, ItemMeta meta, Player player, TeamPlayer teamPlayer) {
        Armor.Type type = Armor.Type.valueOf(meta.getLore().get(meta.getLore().size() - 1).substring(2));

        switch(meta.getLore().get(meta.getLore().size() - 2).substring(2).toUpperCase()) {
            case "DIAMOND": {
                setArmor(teamPlayer, new DiamondArmor(type));
                break;
            }
            case "GOLD": {
                setArmor(teamPlayer, new GoldArmor(type));
                break;
            }
            case "IRON": {
                setArmor(teamPlayer, new IronArmor(type));
                break;
            }
            case "LEATHER": {
                setArmor(teamPlayer, new IronArmor(type));
                break;
            }
        }

    }


    public void setArmor(TeamPlayer player, Armor armor) {
        switch(armor.getArmorType()) {
            case HELMET: {
                player.setHelmet(armor);
                break;
            }
            case CHEST_PLATE: {
                player.setChestPlate(armor);
                break;
            }
            case LEGGINGS: {
                player.setLeggings(armor);
                break;
            }
            case BOOTS: {
                player.setBoots(armor);
                break;
            }
        }
    }
}
