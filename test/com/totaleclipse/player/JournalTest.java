package com.totaleclipse.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JournalTest {

    @Test
    public void addClue() {
        String expected = "Current clues: \n";
        assertEquals(expected, Journal.getClueString());
        Journal.addClue("Clue1", "This is a clue");
        expected += "Clue1: This is a clue\n";
        assertEquals(expected, Journal.getClueString());
    }
}