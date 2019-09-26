package com.foxiko.ejv.hva.uts;

import com.foxiko.ejv.hva.uts.objects.Armor;
import com.foxiko.ejv.hva.uts.objects.Weapon;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

import static com.foxiko.ejv.hva.uts.Main.game;

public class ItemDropper {

    public int[] getRandomLocation() {
        int random = 10-((int)Math.round((Math.random())*(10)));
        int randomX = game.maxWorldX-((int)Math.round((Math.random())*(game.maxWorldX-game.minWorldX + random) ));
        int randomY = game.maxWorldY-((int)Math.round((Math.random())*(game.maxWorldY-game.minWorldY + random)));
        return new int[]{randomX, randomY};
    }

    public void dropItemStack(ItemStack item) {
        int[] location = getRandomLocation();
        dropItemStack(item, location[0], 101, location[1]);
    }

    public void dropItemStack(ItemStack item, int x, int y, int z) {
        game.getPlayingWorld().dropItem(new Location(game.getPlayingWorld(), x, y, z), item);
    }

    private Object randomFromArray(Object[] array) {
        return array[array.length-((int)Math.round((Math.random())*array.length))];
    }
}
