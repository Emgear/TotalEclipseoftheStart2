package com.totaleclipse.commands;

public class Actions {

    public static void help() {
        StringBuilder helpString = new StringBuilder();
        for (var localCommands : Commands.LocalCommands.values()) {
            helpString.append(localCommands);
            helpString.append(System.getProperty("line.separator"));
        }
        System.out.println(helpString.toString());
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
