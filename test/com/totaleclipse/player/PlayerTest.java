package com.totaleclipse.player;

import com.totaleclipse.clues.Clue;
import com.totaleclipse.enemy.Enemy;
import com.totaleclipse.location.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Location loc1 = new Location();
    Clue clue1 = new Clue();
    Enemy enemy1 = new Enemy();
    Player p1 = Player.getInstance("Bob",loc1, clue1, 50, enemy1);


    @Test
    public void getInstance() {
        Player player = Player.getInstance("Bob",loc1, clue1, 50, enemy1);
        assertNotNull("Player class exists", player);
    }

    @Test
    public void setHumanity() {
        p1.setHumanity(50);
        assertEquals(50, p1.getHumanity());
    }

    @Test
    public void getClue() {
        assertNotNull("Player class exists", p1.getClue());
    }


    @Test
    public void getPlayerName() {
        assertEquals("Bob", p1.getPlayerName());
    }

    @Test
    public void getLocation() {
        assertNotNull("Player class exists", p1.getLocation());

    }

    @Test
    public void getPlayerHp() {
        assertEquals(50, p1.getPlayerHp());
    }

    @Test
    public void getEnemy() {
        assertNotNull("Player class exists", p1.getEnemy());
    }
}