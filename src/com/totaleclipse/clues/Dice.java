package com.totaleclipse.clues;

public class Dice {
    private static int randomNum;

    public static int roll(){
       randomNum = (int)(Math.random() * 3);
       return randomNum;
    }
}
