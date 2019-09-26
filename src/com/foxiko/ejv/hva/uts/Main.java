package com.foxiko.ejv.hva.uts;

import com.foxiko.ejv.hva.uts.events.*;
import com.foxiko.ejv.hva.uts.items.armor.DiamondArmor;
import com.foxiko.ejv.hva.uts.items.weapons.DiamondCleaver;
import com.foxiko.ejv.hva.uts.objects.Armor;
import com.foxiko.ejv.hva.uts.objects.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin plugin;
    public static PluginManager pluginManager;

    public static Game game;

    @Override
    public void onEnable() {
        plugin = this;
        pluginManager = Bukkit.getPluginManager();

        newGame();

        pluginManager.registerEvents(new PlayerDamageEvent(), this);
        pluginManager.registerEvents(new PlayerJoinEvent(), this);
        pluginManager.registerEvents(new PlayerHitEvent(), this);
        pluginManager.registerEvents(new PlayerBlockPlaceEvent(), this);
        pluginManager.registerEvents(new PlayerInventoryClickEvent(), this);
        pluginManager.registerEvents(new PlayerPickupItemEvent(), this);
        pluginManager.registerEvents(new PlayerItemDropEvent(), this);

        if(Bukkit.getMaxPlayers() != game.getTeamBlue().maxPlayerSize + game.getTeamRed().maxPlayerSize) {
            plugin.getLogger().warning("The server slot size are not equal to the total maximum team sizes!");
        }

    }

    public static void newGame() {
        game = new Game(Bukkit.getWorld("world"));

        for(Player p : Bukkit.getOnlinePlayers()) {
            game.putPlayerInTeam(p);
        }

        ItemDropper dropper = new ItemDropper();
        dropper.dropItemStack(new DiamondArmor(Armor.Type.HELMET));
        dropper.dropItemStack(new DiamondArmor(Armor.Type.CHEST_PLATE));
        dropper.dropItemStack(new DiamondArmor(Armor.Type.LEGGINGS));
        dropper.dropItemStack(new DiamondArmor(Armor.Type.BOOTS));
        dropper.dropItemStack(new DiamondCleaver(), 50 ,101, 50);
    }
}
