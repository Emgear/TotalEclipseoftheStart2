package com.totaleclipse.commands;

import com.totaleclipse.client.DisplayScreen;
import com.totaleclipse.enemy.Enemies;
import com.totaleclipse.enemy.Enemy;
import com.totaleclipse.player.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class AttackEngine {
    // properties
    private static int startingHp;
    private static Player player;
    private static Enemy enemy;
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN



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
                    if (Objects.equals(runOrNoString, "yes") || Objects.equals(runOrNoString, "y")) {
                        DisplayScreen.displayConsole(player.getPlayerName() + " got away!");
                        break;
                    } else {
                        halfHealthQuestion = false;
                    }
                }
                break;
            }
            // if players health is less than 20;
            if (player.getPlayerHp() <= 20) {
                while (almostDeadQuestion) {
                    DisplayScreen.displayConsole("You are almost dead. Do you want to run away? (yes or no)");
                    ArrayList commandArray = runOrNO.parseCommand();
                    String runOrNoString = (String) commandArray.get(0);
                    if (Objects.equals(runOrNoString, "yes") || Objects.equals(runOrNoString, "y")) {
                        DisplayScreen.displayConsole(player.getPlayerName() + " got away!");
                        break;
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
            DisplayScreen.displayConsole(GREEN_BOLD + "You hit " + enemy.getName() + " with a corn stalk" + RESET);
            enemy.setEnemyHealth(enemy.getEnemyHealth() - 20);
            DisplayScreen.displayConsole(enemy.getName() + " health is now: " + enemy.getEnemyHealth());
            DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());

        } else if (randomNum == 1) {
            DisplayScreen.displayConsole(enemy.getName() + " stumbled towards you but fell over...");
            DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());
            DisplayScreen.displayConsole(enemy.getName() + " health: " + enemy.getEnemyHealth());

        } else if (randomNum == 2) {
            DisplayScreen.displayConsole(RED_BOLD + enemy.getName() + " attacked you!" + RESET);
            player.setPlayerHp(Player.getPlayerHp() - 10);
            DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());
            DisplayScreen.displayConsole(enemy.getName() + " health: " + enemy.getEnemyHealth());

        } else {
            DisplayScreen.displayConsole(RED_BOLD + enemy.getName() + " used a special attack!" + RESET);
            player.setPlayerHp(Player.getPlayerHp() - enemy.getAttackPower());
            DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());
            DisplayScreen.displayConsole(enemy.getName() + " health: "+ enemy.getEnemyHealth());

        }

    }
}
