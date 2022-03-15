package com.totaleclipse.npc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * The NPC class represents the different dialog options available from every NPC, not including clues..
 */
public class NPC {

    private static List<String> dialogLines;
    private static int lineNum = 0;

    //Fill list with lines of dialog
    static{
        try {
            dialogLines = Files.readAllLines(Paths.get("src/com/totaleclipse/npc/dialogs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //No instances needed
    private NPC(){}

    /**
     * Grabs next line of dialog
     * @return A dialog line for an NPC to speak
     */
    public static String getNextDialog() {
        if(lineNum >= dialogLines.size()){
            lineNum = 0;
        }
        return dialogLines.get(lineNum++);
    }

    /**
     * Grabs a random dialog to display.
     * @return A random dialog line for an NPC to speak
     */
    public static String getRandomDialog(){
        Random random = new Random();
        return dialogLines.get(random.nextInt(dialogLines.size()));
    }

    /**
     * Returns all dialog options available. Created for testing
     * @return all dialog options
     */
    public static List<String> getDialogLines(){
        return dialogLines;
    }
}
