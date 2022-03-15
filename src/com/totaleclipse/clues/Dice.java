package com.totaleclipse.clues;

/**
 * Random number generator to pick which clue the player receives
 */
public class Dice {
    private static int randomNum;

    public static int roll(){
       randomNum = (int)(Math.random() * 3);
       return randomNum;
    }
}
