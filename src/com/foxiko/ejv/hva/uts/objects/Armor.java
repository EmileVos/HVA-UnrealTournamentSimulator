package com.foxiko.ejv.hva.uts.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class Armor extends ItemStack {
    public enum Type {
        HELMET(20),
        CHEST_PLATE(40),
        LEGGINGS(30),
        BOOTS(10);

        private int percentageDefence;

        Type(int percentageDefence) {
            this.percentageDefence = percentageDefence;
        }

        /**
         * Returns the specific
         * @return int
         */
        public int getPercentageDefence() {
            return percentageDefence;
        }
    }

    private Armor.Type armorType;

    /**
     * This is the total level of of the player for wearing the full set (HELMET, CHEST_PLATE, LEGGINGS, BOOTS)
     */
    private int defence;

    public Armor(Armor.Type armorType, int defence, Material material, String name) {
        super(material, 1);
        this.armorType = armorType;
        this.defence = defence;

        ItemMeta meta = this.getItemMeta();

        String typeName = this.armorType.toString().toLowerCase().replaceAll("_", " ");

        meta.setDisplayName(name + " " + typeName);
        meta.setLore(Arrays.asList(
                "",
                "§7When worn this " + name + " " + typeName + " §7will give you §a" + this.getDefence() + " §7defence",
                "",
                "",
                "§0ARMOR",
                "§0" + name.substring(4),
                "§0" + this.getArmorType().toString()
        ));
        this.setItemMeta(meta);
    }

    /**
     * @return int The percentage of defence by type
     */
    public int getDefence() {
        return (defence * this.armorType.getPercentageDefence() / 100);
    }


    /**
     * Returns the armor type Armor.Type
     * @return
     */
    public Type getArmorType() {
        return armorType;
    }
}
