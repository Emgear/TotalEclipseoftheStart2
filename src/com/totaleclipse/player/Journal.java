package com.totaleclipse.player;

import com.totaleclipse.clues.Clue;

import java.util.*;

public class Journal {

    private Journal(){}

    private static Map<String, Clue> clues = new HashMap<>();

    public static Map<String, Clue> getClues() {
        return clues;
    }

    public static void addClue(String source, Clue clue) {
        clues.put(source, clue);
    }

    public static String getClueString(){
        StringBuilder cluesString = new StringBuilder();

        for(String source : clues.keySet()){
            cluesString.insert(0,source + ": " + clues.get(source).getClue() + '\n');
        }

        cluesString.insert(0,"Current clues: \n");
        return cluesString.toString();
    }

}
