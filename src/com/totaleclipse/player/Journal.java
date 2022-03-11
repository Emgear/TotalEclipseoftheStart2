package com.totaleclipse.player;

import com.totaleclipse.clues.Clue;
import java.util.*;

/**
 * The Journal class hold the information about the current clues the player has obtained.
 */
public class Journal {

    private static Map<String, Clue> clues = new HashMap<>();

    /**
     * Private constructor to prevent instances being created. Everything in this class is static, no instances needed
     */
    private Journal(){}

    /**
     * Adding a clue to the journal if it is not already there
     * @param source the source of the clue, so we can display accurately how the player obtained it
     * @param clue The clue itself that is being added to the journal
     */
    public static void addClue(String source, Clue clue) {
        if(!hasClue(clue)){
            clues.put(source, clue);
        }
    }

    /**
     * Checks if the current journal already has a clue or not, to prevent duplicates from being created
     * @param clue the clue to check for duplicates of
     * @return true if the clue already exists, false if the clue has not been seen yet.
     */
    public static boolean hasClue(Clue clue){
        return clues.containsValue(clue);
    }

    /**
     * A method to get a string value of the journal.
     * This method is needed since an instance of journal can not be created
     * @return The current contents of Journal in string format
     */
    public static String getClueString(){
        StringBuilder cluesString = new StringBuilder();

        for(String source : clues.keySet()){
            cluesString.insert(0,source + ": " + clues.get(source).getClue() + '\n');
        }

        cluesString.insert(0,"Current clues: \n");
        return cluesString.toString();
    }

}
