package com.totaleclipse.commands;

import com.totaleclipse.client.DisplayScreen;
import com.totaleclipse.enemy.Enemies;
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
        boolean halfHealthQuestion = true;
        boolean almostDeadQuestion = true;
        AttackEngine.player = player;
        AttackEngine.startingHp = player.getPlayerHp();
        AttackEngine.enemy = enemy;
        parseCommands runOrNO = new parseCommands();

        while (player.getPlayerHp() > 0 || enemy.getEnemyHealth() > 0) {
            fight();
            if (Player.getPlayerHp() <= 0){
                return;
            }
            if (enemy.getEnemyHealth() <= 0){
                System.out.println("Enemy has died");
                Enemies.enemyMap.remove(enemy.getName());
                return;
            }
            // if players health is less than half
            if (player.getPlayerHp() < (startingHp / 2)) {
                while (halfHealthQuestion) {
                    DisplayScreen.displayConsole("You have lost over half you health. Do you want to run away? (yes or no)");
                    ArrayList commandArray = runOrNO.parseCommand();
                    String runOrNoString = (String) commandArray.get(0);
                    if (runOrNoString == "yes" || runOrNoString == "y") {
                        return;
                    } else {
                        halfHealthQuestion = false;
                    }

                }
            }
            // if players health is less than 20;
            if (player.getPlayerHp() < (startingHp / 2)) {
                while (almostDeadQuestion) {
                    DisplayScreen.displayConsole("You are almost dead. Do you want to run away? (yes or no)");
                    ArrayList commandArray = runOrNO.parseCommand();
                    String runOrNoString = (String) commandArray.get(0);
                    if (runOrNoString == "yes" || runOrNoString == "y") {
                        return;
                    }
                    else {
                        almostDeadQuestion = false;
                    }
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
            enemy.setEnemyHealth(enemy.getEnemyHealth() - 10);
            System.out.println("Enemy health is now :" + enemy.getEnemyHealth());

        } else if (randomNum == 1) {
            System.out.println("You got hit by the zombie");
            player.setPlayerHp(Player.getPlayerHp() - 10);
            System.out.println("Your health: " + Player.getPlayerHp());
            System.out.println("Zombie health: " + enemy.getEnemyHealth());
            enemy.setEnemyHealth(enemy.getEnemyHealth() - 10);
            System.out.println("Enemy health is now :" + enemy.getEnemyHealth());

        } else if (randomNum == 2) {
            System.out.println("You got hit by the zombie");
            player.setPlayerHp(Player.getPlayerHp() - 10);
            System.out.println("Your health: " + Player.getPlayerHp());
            System.out.println("Zombie health: " + enemy.getEnemyHealth());
            enemy.setEnemyHealth(enemy.getEnemyHealth() - 10);
            System.out.println("Enemy health is now :" + enemy.getEnemyHealth());

        } else {
            System.out.println("You got hit by the zombie");
            player.setPlayerHp(Player.getPlayerHp() - 10);
            System.out.println("Your health: " + Player.getPlayerHp());
            System.out.println("Zombie health: " + enemy.getEnemyHealth());
            enemy.setEnemyHealth(enemy.getEnemyHealth() - 10);
            System.out.println("Enemy health is now :" + enemy.getEnemyHealth());

        }

    }
}
