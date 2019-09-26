package com.foxiko.ejv.hva.uts.objects;

import com.foxiko.ejv.hva.uts.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nullable;

public class TeamPlayer {

    private double hp;
    private int lastUsedSlot = 0;
    private String name;
    private boolean isDead;

    private TeamPlayer lastDamager;

    private Player player;

    private Team team;

    private Armor helmet;
    private Armor chestPlate;
    private Armor leggings;
    private Armor boots;

    private Weapon singleHandedWeapon = null; //in player inventory slot 0
    private Weapon twoHandedWeapon = null; //in player inventory slot 1
    private Weapon meleeWeapon = null; //in player inventory slot 2


    public TeamPlayer(Player player, Team team) {
        this.player = player;
        this.name = player.getName();
        this.hp = player.getHealth();
        this.team = team;
        this.isDead = false;
    }


    /**
     * Used to init the player or remove the
     */
    public void start() {
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player1 == player) continue;
            player1.showPlayer(Main.plugin, player);
        }
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20);
        player.teleport(team.getSpawnPoint());
        player.getInventory().clear();
        player.removePotionEffect(PotionEffectType.INVISIBILITY);

        isDead = false;

        setSingleHandedWeapon(null);
        setTwoHandedWeapon(null);
        setMeleeWeapon(null);
        setHelmet(null);
        setChestPlate(null);
        setLeggings(null);
        setBoots(null);
    }

    /**
     * Plays the dead animations and send a message to the server, needs the param byPlayer (slayer).
     *
     * @param byPlayer Player
     */
    public void die(Player byPlayer) {
        isDead = true;
        team.getDeadPlayers().add(this);
        player.setHealth(20);
        player.getWorld().strikeLightning(player.getLocation().add(0, 3, 0));
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1, true, false), false);

        setSingleHandedWeapon(null);
        setTwoHandedWeapon(null);
        setMeleeWeapon(null);
        setHelmet(null);
        setChestPlate(null);
        setLeggings(null);
        setBoots(null);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p == player) continue;
            p.hidePlayer(Main.plugin, p);
        }

    }


    /**
     * Sets the two handed weapon slot if null then the weapon will be removed
     * @param weapon
     */
    public void setSingleHandedWeapon(@Nullable Weapon weapon) {
        this.singleHandedWeapon = weapon;
        if(weapon != null) {
            player.getInventory().setItem(0, weapon);
        } else {
            ItemStack itemStack = new ItemStack(Material.BARRIER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§4§lNO SINGLE HANDED WEAPON ACQUIRED");
            itemStack.setItemMeta(itemMeta);
            player.getInventory().setItem(0, itemStack);
        }
    }

    /**
     * Sets the two handed weapon slot if null then the weapon will be removed
     * @param weapon
     */
    public void setTwoHandedWeapon(@Nullable Weapon weapon) {
        this.twoHandedWeapon = weapon;
        if(weapon != null) {
            player.getInventory().setItem(1, weapon);
        } else {
            ItemStack itemStack = new ItemStack(Material.BARRIER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§4§lNO TWO HADED WEAPON ACQUIRED");
            itemStack.setItemMeta(itemMeta);
            player.getInventory().setItem(1, itemStack);
        }
    }

    /**
     * Sets the melee weapon slot if null then the weapon will be removed
     * @param weapon
     */
    public void setMeleeWeapon(@Nullable Weapon weapon) {
        this.meleeWeapon = weapon;
        if(weapon != null) {
            player.getInventory().setItem(2, weapon);
        } else {
            ItemStack itemStack = new ItemStack(Material.BARRIER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§4§lNO MELEE WEAPON ACQUIRED");
            itemStack.setItemMeta(itemMeta);
            player.getInventory().setItem(2, itemStack);
        }
    }

    /**
     * @param armor can be null, if null will set to a nothing
     */
    public void setHelmet(@Nullable Armor armor) {
        this.helmet = armor;

        if (this.helmet == null) {
            player.getInventory().setHelmet(new ItemStack(Material.AIR));
        } else {
            player.getInventory().setHelmet(armor);
        }
    }

    /**
     * @param armor can be null, if null will set to a nothing
     */
    public void setChestPlate(@Nullable Armor armor) {
        this.chestPlate = armor;

        if (this.chestPlate == null) {
            player.getInventory().setChestplate(new ItemStack(Material.AIR));
        } else {
            player.getInventory().setChestplate(armor);
        }
    }

    /**
     * @param armor can be null, if null will set to a nothing
     */
    public void setLeggings(@Nullable Armor armor) {
        this.leggings = armor;

        if (this.leggings == null) {
            player.getInventory().setLeggings(new ItemStack(Material.AIR));
        } else {
            player.getInventory().setLeggings(armor);
        }
    }

    /**
     * @param armor can be null, if null will set to a nothing
     */
    public void setBoots(@Nullable Armor armor) {
        this.boots = armor;

        if (this.boots == null) {
            player.getInventory().setBoots(new ItemStack(Material.AIR));
        } else {
            player.getInventory().setBoots(armor);
        }
    }

    /*
     * Generated getters & setters
     */

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public Team getTeam() {
        return team;
    }

    public Armor getHelmet() {
        return helmet;
    }

    public Armor getChestPlate() {
        return chestPlate;
    }

    public Armor getLeggings() {
        return leggings;
    }

    public Armor getBoots() {
        return boots;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Weapon getSingleHandedWeapon() {
        return singleHandedWeapon;
    }

    public Weapon getTwoHandedWeapon() {
        return twoHandedWeapon;
    }

    public Weapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public int getLastUsedSlot() {
        return lastUsedSlot;
    }

    public void setLastUsedSlot(int lastUsedSlot) {
        this.lastUsedSlot = lastUsedSlot;
    }

    public TeamPlayer getLastDamager() {
        return lastDamager;
    }

    public void setLastDamager(TeamPlayer lastDamager) {
        this.lastDamager = lastDamager;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
        this.player.setHealth(hp);
    }
}
