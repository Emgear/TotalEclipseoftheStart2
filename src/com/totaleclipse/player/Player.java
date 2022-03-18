package com.totaleclipse.player;

import com.totaleclipse.clues.Clue;
import com.totaleclipse.enemy.Enemy;
import com.totaleclipse.location.Location;

public class Player {
    private final String playerName;
    private Location location;
    private Clue clue;
    private int humanity=0;
    private static int playerHp;
    private Enemy enemy;
    public static Player player=null;

    public static Player getInstance(String playerName, Location location, Clue clue, int playerHp, Enemy enemy){
        if(player==null){
            player=new Player(playerName, location, clue, playerHp, enemy);
        }
        return player;
    }

    public int getHumanity() {
        return humanity;
    }

    public void setHumanity(int humanity) {
        this.humanity+= humanity;
    }

    // constructor to take journal obj & player name string (we'll ask for input in initial driver)
    private Player(String playerName, Location location, Clue clue, int playerHp, Enemy enemy) {
        this.playerName = playerName;
        this.location = location;
        this.clue=clue;
        this.playerHp = playerHp;
        this.enemy = enemy;
    }
    public Clue getClue() {

        return clue;
    }

    public void setClue(Clue clue) {
        this.clue = clue;
    }


    public String getPlayerName() {
        return playerName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static int getPlayerHp() {
        return playerHp;
    }

    public void setPlayerHp(int playerHp) {
        this.playerHp = playerHp;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
