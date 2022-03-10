package com.totaleclipse.player;

import com.totaleclipse.location.Location;

public class Player {
    String playerName;
    Location location;


    // constructor to take journal obj & player name string (we'll ask for input in initial driver)
    public Player(String playerName, Location location) {
        this.playerName = playerName;
        this.location = location;
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
