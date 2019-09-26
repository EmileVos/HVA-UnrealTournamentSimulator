package com.foxiko.ejv.hva.uts.items.armor;

import com.foxiko.ejv.hva.uts.objects.Armor;
import org.bukkit.Material;

public class GoldArmor extends Armor {

    private static Material getMaterial(Armor.Type type) {
        switch (type) {
            case HELMET: return Material.GOLDEN_HELMET;
            case CHEST_PLATE: return Material.GOLDEN_CHESTPLATE;
            case LEGGINGS: return Material.GOLDEN_LEGGINGS;
            case BOOTS: return Material.GOLDEN_BOOTS;
            default: return Material.AIR;
        }
    }

    public GoldArmor(Type armorType) {
        super(armorType, 15, GoldArmor.getMaterial(armorType), "§6§lGold");
    }
}
