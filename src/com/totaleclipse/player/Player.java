package com.totaleclipse.player;

import com.totaleclipse.clues.Clue;
import com.totaleclipse.location.Location;

public class Player {
    private String playerName;
    private Location location;
    private Clue clue;
    private int humanity=0;

    public int getHumanity() {
        return humanity;
    }

    public void setHumanity(int humanity) {
        this.humanity+= humanity;
    }

    // constructor to take journal obj & player name string (we'll ask for input in initial driver)
    public Player(String playerName, Location location, Clue clue) {
        this.playerName = playerName;
        this.location = location;
        this.clue=clue;
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
}
