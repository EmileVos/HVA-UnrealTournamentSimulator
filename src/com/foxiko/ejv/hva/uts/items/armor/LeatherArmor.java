package com.foxiko.ejv.hva.uts.items.armor;

import com.foxiko.ejv.hva.uts.objects.Armor;
import org.bukkit.Material;

public class LeatherArmor extends Armor {

    public static Material getMaterial(Armor.Type type) {
        switch (type) {
            case HELMET: return Material.LEATHER_HELMET;
            case CHEST_PLATE: return Material.LEATHER_HELMET;
            case LEGGINGS: return Material.LEATHER_LEGGINGS;
            case BOOTS: return Material.LEATHER_BOOTS;
            default: return Material.AIR;
        }
    }

    public LeatherArmor(Type armorType) {
        super(armorType, 5, LeatherArmor.getMaterial(armorType), "§e§lLeather");
    }

}
