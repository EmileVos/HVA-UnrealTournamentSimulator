package com.foxiko.ejv.hva.uts.items.armor;

import com.foxiko.ejv.hva.uts.objects.Armor;
import org.bukkit.Material;

public class IronArmor extends Armor {

    public static Material getMaterial(Armor.Type type) {
        switch (type) {
            case HELMET: return Material.IRON_HELMET;
            case CHEST_PLATE: return Material.IRON_CHESTPLATE;
            case LEGGINGS: return Material.IRON_LEGGINGS;
            case BOOTS: return Material.IRON_BOOTS;
            default: return Material.AIR;
        }
    }

    public IronArmor(Type armorType) {
        super(armorType, 10, IronArmor.getMaterial(armorType), "§7§lIron");
    }

}
