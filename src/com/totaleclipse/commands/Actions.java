package com.totaleclipse.commands;

public class Actions {

    public static void help() {
        StringBuilder helpString = new StringBuilder();
        for (var localCommands : Commands.LocalCommands.values()) {
            helpString.append(localCommands.keyword + ": " + localCommands.description);
            helpString.append(System.getProperty("line.separator"));
        }
        System.out.println("The actions you can perform are:\n" + helpString);
    }
    protected static void move(){
        System.out.println("move");
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
