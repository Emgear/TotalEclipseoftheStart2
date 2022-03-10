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
//        switch(noun.toUpperCase()){
//            case "NORTH"-> System.out.println("Move North");
//            case "EAST"-> System.out.println("Move East");
//            case "SOUTH"-> System.out.println("Move South");
//            case "WEST"-> System.out.println("Move West");
//        }
    }
    protected static void look(){
        System.out.println("look");
    }
    protected static void get(){
        System.out.println("get");
    }
    protected static void talk(){
        System.out.println("talk");
    }

}
