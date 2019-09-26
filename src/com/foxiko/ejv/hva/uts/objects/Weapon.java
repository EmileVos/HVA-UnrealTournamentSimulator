package com.foxiko.ejv.hva.uts.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class Weapon extends ItemStack {

    public enum Type {
        SINGLE_HANDED,
        TWO_HANDED,
        MELEE
    }

    private Weapon.Type type;
    private int damage;
    private String name;
    private Material material;

    public Weapon(Weapon.Type type, int damage, String name, Material material) {
        super(material, 1);
        this.damage = damage;
        this.name = name;
        this.material = material;
        this.type = type;

        ItemMeta meta = this.getItemMeta();
        meta.setLore(Arrays.asList(
                "§cDamage: §f" + this.damage,
                "",
                "§a§lWeapon Type:",
                "§f" + this.damage,
                "",
                "§0WEAPON",
                "§0" + name.substring(4).toUpperCase(),
                "§0" + type.toString()
        ));
        meta.setDisplayName(this.name);
        this.setItemMeta(meta);
    }


    public Type getWeaponType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }
}
