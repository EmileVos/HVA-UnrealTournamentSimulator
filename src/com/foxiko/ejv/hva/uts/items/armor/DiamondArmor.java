package com.foxiko.ejv.hva.uts.items.armor;

import com.foxiko.ejv.hva.uts.objects.Armor;
import org.bukkit.Material;

public class DiamondArmor extends Armor {


    private static Material getMaterial(Armor.Type type) {
        switch (type) {
            case HELMET: return Material.DIAMOND_HELMET;
            case CHEST_PLATE: return Material.DIAMOND_CHESTPLATE;
            case LEGGINGS: return Material.DIAMOND_LEGGINGS;
            case BOOTS: return Material.DIAMOND_BOOTS;
            default: return Material.AIR;
        }
    }

    public DiamondArmor(Type armorType) {
        super(armorType, 20, DiamondArmor.getMaterial(armorType), "§b§lDiamond");
    }

}
