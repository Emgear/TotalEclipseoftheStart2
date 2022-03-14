package com.totaleclipse.clues;

// make clue an abstract class, make item and npc extend it (then NPC and item will parse the clue from the JSON. put a to string method in this class


import java.util.Arrays;

public class Clue {
    //strings for each json clue
    private String[] clue;
    private String npc;
    private String location;
    private String item;

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    private String[] keywords;

    public Clue(String[] clue, String NPC, String location, String item, String[] keywords) {
        this.clue = clue;
        this.npc = NPC;
        this.location = location;
        this.keywords=keywords;
    }

    public Clue() {

    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setClue(String[] clue) {
        this.clue = clue;
    }

    public void removeItem(){ this.item = "";}

    public String getNpc() {
        return npc;
    }

    public void setNpc(String NPC) {
        this.npc = NPC;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }




    // get clue method
    public String getClue() {
        return this.clue[Dice.roll()];

    }

    /**
     * returns the array of clues, allowing for tests for equality and comparisons.
     * @return The array of clues possible
     */
    public String[] getClues(){
        return clue;
    }

    /**
     * equals method created using the clue string, which shouldn't be shared between clues (at least for now)
     * @param clue The object test for equality with
     * @return true if the object provided is a clue with matching clue string
     */
    @Override
    public boolean equals(Object clue) {
        if (this == clue) return true;

        Clue clue1 = (Clue) clue;

        return getClues()[0].equalsIgnoreCase(clue1.getClues()[0]);
    }
    
    /**
     * Generated hash code method to pair with equals
     * @return the hash code representation of this Clue.
     */
    @Override
    public int hashCode() {
        return clue != null ? Arrays.hashCode(clue) : 0;
    }

    @Override
    public String toString() {
        return "Clue{" +
                "clues='" + Arrays.toString(clue) + '\'' +
                ", npc='" + npc + '\'' +
                ", location='" + location + '\'' +
                ", item='" + item + '\'' +
                '}';
    }
// get location method

}
