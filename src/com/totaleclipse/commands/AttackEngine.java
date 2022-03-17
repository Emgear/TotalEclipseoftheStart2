//package com.totaleclipse.commands;
//
//import com.totaleclipse.client.DisplayScreen;
//import com.totaleclipse.player.Player;
//
//import java.util.ArrayList;
//import java.util.concurrent.ThreadLocalRandom;
//
//public class AttackEngine {
//    // properties
//    private int startingHp;
//   private Player player;
//   private  Monster monster;
//    static void run(Player player, Monster monster){
//        this.player = player;
//        this.startingHp = player.getPlayerHp();
//        this.monster = monster;
//        System.out.println(player.getPlayerName() + " is running the attack engine right now.");
//         while (player.getPlayerHp() > 0 || monster.getHp() > 0){
//             fight();
//         }
//    }
//    static void fight(){
//        int randomNum = (int)(Math.random() * 4); // 0 to 10
//        if (player.getPlayerHp() < (startingHp/2)){
//            parseCommands runOrNO = new parseCommands();
//            DisplayScreen.displayConsole("You are almost dead. Do you want to run away? (yes or no)");
//            ArrayList commandArray = com.parseCommand();
//            String runOrNoString = (String) commandArray.get(0);
//            if (runOrNoString == "yes" || runOrNoString == "y"){
//                break;
//            }
//        }
//        if (randomNum == 0){
//            System.out.println("you've hit the zombie with a corn stick");
//            monster.getHp = monster.getHp - 10;
//        }
//        else if(randomNum == 1){
//            System.out.println("You got hit by the zombie");
//            player.getPlayerHp() = player.getPlayerHp() - 10;
//        }
//        else if(randomNum == 2){
//            System.out.println("You got hit by the zombie");
//            player.getPlayerHp() = player.getPlayerHp() - 10;
//        }
//        else{
//            System.out.println("You got hit by the zombie");
//            player.getPlayerHp() = player.getPlayerHp() - 10;
//        }
//    }
//}
