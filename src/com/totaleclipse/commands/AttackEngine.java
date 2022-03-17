package com.totaleclipse.commands;

import com.totaleclipse.client.DisplayScreen;
import com.totaleclipse.enemy.Enemy;
import com.totaleclipse.player.Player;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AttackEngine {
    // properties
    private static int startingHp;
    private static Player player;
    private static Enemy enemy;

     static void run(Player player, Enemy enemy) {
        AttackEngine.player = player;
        AttackEngine.startingHp = player.getPlayerHp();
        AttackEngine.enemy = enemy;

        while (player.getPlayerHp() > 0 || enemy.getEnemyHealth() > 0) {
            fight();
            if (player.getPlayerHp() < (startingHp / 2)) {
                parseCommands runOrNO = new parseCommands();
                DisplayScreen.displayConsole("You are almost dead. Do you want to run away? (yes or no)");
                ArrayList commandArray = runOrNO.parseCommand();
                String runOrNoString = (String) commandArray.get(0);
                if (runOrNoString == "yes" || runOrNoString == "y") {
                    break;
                }
            }
        }
    }

    static void fight() {
        int randomNum = (int) (Math.random() * 4); // 0 to 10
        if (randomNum == 0) {
            System.out.println("you've hit the zombie with a corn stick");
            System.out.println(enemy.getEnemyHealth());
            System.out.println("Your health: " + Player.getPlayerHp());
            System.out.println("Zombie health: " + enemy.getEnemyHealth());
        } else if (randomNum == 1) {
            System.out.println("You got hit by the zombie");
            player.setPlayerHp(Player.getPlayerHp() - 10);
            System.out.println("Your health: " + Player.getPlayerHp());
            System.out.println("Zombie health: " + enemy.getEnemyHealth());
        } else if (randomNum == 2) {
            System.out.println("You got hit by the zombie");
            player.setPlayerHp(Player.getPlayerHp() - 10);
            System.out.println("Your health: " + Player.getPlayerHp());
            System.out.println("Zombie health: " + enemy.getEnemyHealth());
        } else {
            System.out.println("You got hit by the zombie");
            player.setPlayerHp(Player.getPlayerHp() - 10);
            System.out.println("Your health: " + Player.getPlayerHp());
            System.out.println("Zombie health: " + enemy.getEnemyHealth());
        }

    }
}
