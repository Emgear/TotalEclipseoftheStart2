package com.totaleclipse.client;


import org.junit.*;
import static org.junit.Assert.*;

public class PlayGameTest {
    private PlayGame playGame;
    @Before
    public void setUp() throws Exception {
        System.out.println("Running Tests");
        playGame = new PlayGame();
        playGame.setUp();
    }

    @Test
    public void testSetUp() {
        assertEquals(true, playGame.playing);
    }

    @Test
    public void testPlayGame() {

    }
}
