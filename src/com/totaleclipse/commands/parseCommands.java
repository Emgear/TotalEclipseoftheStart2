package com.totaleclipse.commands;

import java.util.Scanner;

public class parseCommands {
    private static Scanner in = new Scanner(System.in);
    public static String parseName(){
        return in.nextLine();
    }
    public void parseCommand() {
        String commandString = in.nextLine();
        String[] commandArray = commandString.split(" ");
        String commandVerb = commandArray[0];
        String commandNoun = "";
        if (commandArray.length > 1)
            commandNoun = commandArray[1];
        new Commands(commandVerb, commandNoun);
    }
}
