package com.totaleclipse.npc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// extends clue class
public class NPC {

    private static List<String> dialogLines;
    private static int lineNum = 0;

    static{
        try {
            dialogLines = Files.readAllLines(Paths.get("src/com/totaleclipse/npc/dialogs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private NPC(){}

    public static String getNextDialog() {
        if(lineNum >= dialogLines.size()){
            lineNum = 0;
        }
        return dialogLines.get(lineNum++);
    }

    public static String getRandomDialog(){
        Random random = new Random();
        return dialogLines.get(random.nextInt(dialogLines.size()));
    }

    public static List<String> getDialogLines(){
        return dialogLines;
    }
}
