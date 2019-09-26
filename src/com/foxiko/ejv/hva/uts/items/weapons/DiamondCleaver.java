package com.foxiko.ejv.hva.uts.items.weapons;

import com.foxiko.ejv.hva.uts.objects.Weapon;
import org.bukkit.Material;

public class DiamondCleaver extends Weapon {

    private static final String name = "§b§lDIAMOND CLEAVER";

    public DiamondCleaver() {
        super(Type.SINGLE_HANDED, 2, name, Material.DIAMOND_SWORD);
    }

    /**
     * This is used to compare a string to this weapon name
     * @param compare String to compare
     * @return boolean is equal
     */
    public static boolean isWeapon(String compare) {
        return compare.toUpperCase().equals(name.substring(4).toUpperCase());
    }
}
