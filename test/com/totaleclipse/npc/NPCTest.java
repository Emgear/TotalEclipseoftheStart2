package com.totaleclipse.npc;

import org.junit.Test;

import static org.junit.Assert.*;

public class NPCTest {

    @Test
    public void getNextDialog() {
//        //Each of the lines should appear
//        assertEquals("Moo.",NPC.getNextDialog());
//        assertEquals("Lovely weather lately.",NPC.getNextDialog());
//        assertEquals("Who are you?",NPC.getNextDialog());
//        assertEquals("I saw some strange lights in the sky last night. Wonder what that's about.",NPC.getNextDialog());
//        assertEquals("Hello there.",NPC.getNextDialog());
//        assertEquals("Hi.",NPC.getNextDialog());
//        assertEquals("I'm busy.",NPC.getNextDialog());
//        assertEquals("Sorry, but I have a lot to do.",NPC.getNextDialog());
//        assertEquals("No time to chat.",NPC.getNextDialog());
//
//        //should loop back to moo after the end of file is reached
//        assertEquals("Moo.",NPC.getNextDialog());
    }

    @Test
    public void getRandomDialog() {
        assertTrue(NPC.getDialogLines().contains(NPC.getRandomDialog()));
    }
}