package com.foxiko.ejv.hva.uts.objects;

import com.foxiko.ejv.hva.uts.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Game {

    private Team teamRed;
    private Team teamBlue;

    private World playingWorld;

    private int currentRound = 0;

    private boolean inGame = false;

    public final int maxWorldX = 50;
    public final int minWorldX = -50;
    public final int maxWorldY = 50;
    public final int minWorldY = -50;

    public Game(World playingWorld) {
        this.playingWorld = playingWorld;
        teamRed = new Team(this, Team.Type.RED, new Location(playingWorld, -50, 100, -50));
        teamBlue = new Team(this, Team.Type.BLUE, new Location(playingWorld, 50,100,50));
    }

    public void endRound() {
        this.inGame = false;
    }

    public boolean isInGame() {
        return inGame;
    }

    public Team getTeamRed() {
        return teamRed;
    }

    public Team getTeamBlue() {
        return teamBlue;
    }

    public World getPlayingWorld() {
        return playingWorld;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void nextRound(Team loserTeam) {
        currentRound++;
        teamRed.reset();
        teamBlue.reset();

        switch (loserTeam.getTeamType()) {
            case RED: {
                Bukkit.broadcastMessage("§eTeam " + teamBlue.getTeamType().getName() + " §e WON THIS ROUND!");
                teamBlue.setRoundsWon(teamBlue.getRoundsWon() + 1);
                if(teamBlue.getRoundsWon() >= 2) {
                    end(teamBlue.getTeamType());
                }
                break;
            }
            case BLUE: {
                Bukkit.broadcastMessage("§eTeam " + teamBlue.getTeamType().getName() + " §e WON THIS ROUND!");
                teamRed.setRoundsWon(teamRed.getRoundsWon() + 1);
                if(teamRed.getRoundsWon() >= 2) {
                    end(teamRed.getTeamType());
                }
                break;
            }
        }

    }

    public Team getPlayerTeam(Player p) {
        if(this.getTeamRed().getPlayers().keySet().contains(p)) return this.getTeamRed();
        else return this.getTeamBlue();
    }

    public void putPlayerInTeam(Player player) {
        if(getTeamRed().getPlayers().size() < getTeamBlue().getPlayers().size()) {
            if(!getTeamRed().addPlayer(player)) {
                if(!getTeamBlue().addPlayer(player)) {
                    player.kickPlayer("Server full...");
                }
            }
        } else {
            if(!getTeamBlue().addPlayer(player)) {
                if(!getTeamRed().addPlayer(player)) {
                    player.kickPlayer("Server full...");
                }
            }
        }
    }

    /**
     * This function will tell every player which team won the game, kick them after 160 ticks and immanently start a new game
     * @param winnerTeam Team that won the game
     */
    public void end(Team.Type winnerTeam) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.teleport(new Location(playingWorld, 0, 100, 0));
            p.setGameMode(GameMode.SPECTATOR);
        }
        Bukkit.broadcastMessage("§r");
        Bukkit.broadcastMessage("§r");
        Bukkit.broadcastMessage("§aTeam " + winnerTeam.getName() + " §a WINS THE GAME");
        Bukkit.broadcastMessage("§r");
        Bukkit.broadcastMessage("§r");

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    p.kickPlayer("§aGAME ENDED\n" + winnerTeam.getName() + " §aWON THE GAME!");
                }
                Main.newGame();
            }
        }, 160);
    }
}
