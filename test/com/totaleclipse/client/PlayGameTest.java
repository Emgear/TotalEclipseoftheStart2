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

    @After
    public void tearDown() throws Exception {
        System.out.println("All the tests are finished");
    }

    @Test
    public void testSetUp() {

    }

    @Test
    public void testPlayGame() {

    }
}
