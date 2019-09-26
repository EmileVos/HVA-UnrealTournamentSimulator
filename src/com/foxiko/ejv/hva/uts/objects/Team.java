package com.foxiko.ejv.hva.uts.objects;

import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {
    public enum Type {
        RED("§cRED"),
        BLUE("§bBLUE");

        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private Map<Player, TeamPlayer> players = new HashMap<>();
    private List<TeamPlayer> deadPlayers = new ArrayList<>();

    private Game game;

    public final int maxPlayerSize = 16;

    private int kills;

    private Location spawnPoint;

    private Team.Type teamType;

    private int roundsWon = 0;

    public Team(Game game, Team.Type teamType, Location spawnPoint) {
        this.teamType = teamType;
        this.spawnPoint = spawnPoint;
        this.game = game;
    }

    public void reset() {
        deadPlayers = new ArrayList<>();
        kills = 0;

        for(TeamPlayer player : players.values()) {
            player.start();
            player.getPlayer().sendMessage("§aA new round has started and you have been respawned!");
        }
    }

    /**
     * Die animation with lightning and removes the player from the 'viewable players'
     * @param player Player that will die
     * @param playerBy Player that will get the kill
     */
    public void diePlayer(@NotNull Player player, @Nullable Player playerBy) {
        TeamPlayer teamPlayer = getPlayers().get(player);
        TeamPlayer teamPlayerBy = game.getPlayerTeam(playerBy).getPlayers().get(playerBy);

        teamPlayer.die(playerBy);


        if (playerBy != null) {
            Bukkit.broadcastMessage(teamType.getName() + " " + teamPlayer.getName() + " was slain by " + teamPlayerBy.getName() + "! " +
                    "§a" + (this.getPlayers().size() - this.getDeadPlayers().size()) + " players left!");
        } else {
            Bukkit.broadcastMessage(teamType.getName() + " " + teamPlayer.getName() + " died! " +
                    "§a" + (this.getPlayers().size() - this.getDeadPlayers().size()) + " players left!");
        }

        if(this.getDeadPlayers().size() == this.getPlayers().size()) {
            game.nextRound(this);
        }
    }

    public boolean addPlayer(Player player) {
        if(this.players.size() == maxPlayerSize) return false;

        TeamPlayer teamPlayer = new TeamPlayer(player, this);
        players.put(player, teamPlayer);

        player.setPlayerListName(teamType.getName() + " " + player.getName());

        if(this.game.isInGame()) {
            this.diePlayer(player, null);
        } else {
            teamPlayer.start();
        }

        return true;
    }

    /*
     * Generated getters
     */

    /**
     * Returns the (Bukkit) player connected with the TeamPlayer
     * @return Map<Player, TeamPlayer>
     */
    public Map<Player, TeamPlayer> getPlayers() {
        return players;
    }

    /**
     * Returns the TeamType
     * @return Team.Type
     */
    public Type getTeamType() {
        return teamType;
    }

    /**
     * Returns how much round the team won
     * @return int
     */
    public int getRoundsWon() {
        return roundsWon;
    }

    /**
     * Returns all dead players
     * @return List<TeamPlayer>
     */
    public List<TeamPlayer> getDeadPlayers() {
        return deadPlayers;
    }

    /**
     * Returns the team spawn point in the Bukkit Location
     * @return Location
     */
    public Location getSpawnPoint() {
        return spawnPoint;
    }

    public void setPlayers(Map<Player, TeamPlayer> players) {
        this.players = players;
    }

    public void setDeadPlayers(List<TeamPlayer> deadPlayers) {
        this.deadPlayers = deadPlayers;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setSpawnPoint(Location spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public void setTeamType(Type teamType) {
        this.teamType = teamType;
    }

    public void setRoundsWon(int roundsWon) {
        this.roundsWon = roundsWon;
    }
}
