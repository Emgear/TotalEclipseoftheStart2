package com.totaleclipse.commands;

import java.util.Scanner;

public class parseCommands {
    private static Scanner in = new Scanner(System.in);
    public static String parseName(){
        return in.nextLine();
    }
    public String[] parseCommand() {
        return in.nextLine().split(" ");
    }
}
