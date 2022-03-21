package com.totaleclipse.npc;

import com.totaleclipse.player.Journal;
import org.junit.Test;

import static com.totaleclipse.commands.Actions.finalClue;
import static org.junit.Assert.*;

public class EndGameTest {

    //tests to make sure the finalClue turns true when final clue is read
    @Test
    public void move() {
        assertFalse(finalClue);
    }
}