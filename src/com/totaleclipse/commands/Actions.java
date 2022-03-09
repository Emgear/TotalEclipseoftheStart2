package com.totaleclipse.commands;

import java.util.Locale;

public class Actions {

    public static void help() {
        StringBuilder helpString = new StringBuilder();
        for (var localCommands : Commands.LocalCommands.values()) {
            helpString.append(localCommands.keyword + ": " + localCommands.description);
            helpString.append(System.getProperty("line.separator"));
        }
        System.out.println("The actions you can perform are:\n" + helpString);
    }
    protected static void move(String noun){
        switch(noun.toUpperCase()){
            case "NORTH"-> System.out.println("Move North");
            case "EAST"-> System.out.println("Move East");
            case "SOUTH"-> System.out.println("Move South");
            case "WEST"-> System.out.println("Move West");
        }
    }

    /**
     * look can be used to look in a direction, at an object, or to get general information about the area.
     * @param noun the direction or object to be looked at
     */
    public static void look(String noun){
        //Generic hard-coded information for the first area for the first sprint
        //Will be updated to change based on items/directions from that location
        switch (noun.toLowerCase()){
            case "around" -> System.out.println("You are currently in a cornfield. There is a path to the north, and an object on the ground next to you." +
                        " You don't see anyone else around, and that floating cow you saw remains in your mind.");
            case "north" -> System.out.println("In the midst of the corn, you see a path leading to what looks like a bunker.");
            case "east" -> System.out.println("There seems to be some.");
            case "south" -> System.out.println("You see some corn.");
            case "west" -> System.out.println("Guess what. There's corn.");
            case "object" -> System.out.println("The object seems to be a hastily scribbled note.");
            default -> System.out.println("I don't see any " + noun.toLowerCase() + " around here.");
        }

    }
    protected static void get(){
        System.out.println("get");
    }
    protected static void talk(){
        System.out.println("talk");
    }

}
