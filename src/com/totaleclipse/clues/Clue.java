package com.totaleclipse.clues;

// make clue an abstract class, make item and npc extend it (then NPC and item will parse the clue from the JSON. put a to string method in this class



public class Clue {

    //strings for each json clue
    String clue;
    String npc;
    String location;

    public Clue(String clue, String NPC, String location) {
        this.clue = clue;
        this.npc = NPC;
        this.location = location;
    }

    public Clue() {

    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public String getNPC() {
        return npc;
    }

    public void setNPC(String NPC) {
        this.npc = NPC;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    // get clue method
    public void getClue() {

    }

    @Override
    public String toString() {
        return "Clue{" +
                "clue='" + clue + '\'' +
                ", npc='" + npc + '\'' +
                ", location='" + location + '\'' +
                '}';
    }


    // get location method

}
