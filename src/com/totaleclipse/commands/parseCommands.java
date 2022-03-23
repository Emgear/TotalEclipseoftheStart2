package com.totaleclipse.commands;

import com.totaleclipse.client.PlayGame;

import java.util.ArrayList;
import java.util.Scanner;

public class parseCommands {
    private static Scanner in = new Scanner(System.in);
    public static String parseName(){
        return in.nextLine();
//        return PlayGame.textInputFromGUI;
    }
    public ArrayList parseCommand() {
        String[] commandArray=in.nextLine().split(" ");
        ArrayList parsedCommand=new ArrayList();
        for(var c:commandArray){
            if(c.equalsIgnoreCase("to")||c.equalsIgnoreCase("the")||c.equalsIgnoreCase("of")||c.equalsIgnoreCase("a")||c.equalsIgnoreCase("and")||c.equalsIgnoreCase("for")){

            }else{
                parsedCommand.add(c);
            }
        }
        return parsedCommand;
    }
}
