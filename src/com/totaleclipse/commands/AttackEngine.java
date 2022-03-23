package com.totaleclipse.commands;

import com.totaleclipse.client.DisplayScreen;
import com.totaleclipse.enemy.Enemies;
import com.totaleclipse.enemy.Enemy;
import com.totaleclipse.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class AttackEngine {
    // properties
    private static int startingHp;
    private static Player player;
    private static Enemy enemy;
    public static int zombiesKilled;
    public static boolean bossKilled = false;
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
        parseCommands chooseAction = new parseCommands();

        while (player.getPlayerHp() > 0 || enemy.getEnemyHealth() > 0 ) {
            DisplayScreen.displayConsole("FIGHT THE ZOMBIE");

            ArrayList choiceArray = chooseAction.parseCommand();
            String chooseActingString = (String) choiceArray.get(0);

            if ((Objects.equals(chooseActingString, "fight")) || (Objects.equals(chooseActingString, "hit")) || (Objects.equals(chooseActingString, "attack"))){
                fight();
//                System.out.println(choiceArray);
            } else if ((Objects.equals(chooseActingString, "run")) || (Objects.equals(chooseActingString, "escape"))){
                DisplayScreen.displayConsole("You cannot escape!");
            } else {
                DisplayScreen.displayConsole("Hmm... maybe I should try to (fight, hit, attack)");
            }
            if (Player.getPlayerHp() <= 0){
                return;
            }
            if (enemy.getEnemyHealth() <= 0){
                if (enemy.getName() == "ZOMBIE BOSS") {
                    bossKilled = true;
                }
//                System.out.println(enemy.getName() + " has been defeated.");
                DisplayScreen.displayConsole(enemy.getName() + " has been defeated.");
                zombiesKilled++;
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
                        return;
                    } else if (Objects.equals(runOrNoString, "no") || Objects.equals(runOrNoString, "n")) {
                        halfHealthQuestion = false;
                    }
                }
//                break;
            }
            // if players health is less than 20;
            if (player.getPlayerHp() <= 20) {
                halfHealthQuestion = false;
                while (almostDeadQuestion) {
                    DisplayScreen.displayConsole("You are almost dead. Do you want to run away? (yes or no)");
                    ArrayList commandArray = runOrNO.parseCommand();
                    String runOrNoString = (String) commandArray.get(0);
                    if (Objects.equals(runOrNoString, "yes") || Objects.equals(runOrNoString, "y")) {
                        DisplayScreen.displayConsole(player.getPlayerName() + " got away!");
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
        int randomNum = (int) (Math.random() * (6-0)) + 0; // 0 to 6
        if (randomNum == 0) {
            DisplayScreen.displayConsole(GREEN_BOLD + "You hit " + enemy.getName() + " with a corn stalk" + RESET);
            enemy.setEnemyHealth(enemy.getEnemyHealth() - 20);
            DisplayScreen.displayConsole(enemy.getName() + " health is now: " + enemy.getEnemyHealth());
            DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());

        } else if (randomNum == 1) {
            int randomNum2 = (int) (Math.random() * (2-0)) + 0; // 0 to
            if (randomNum == 1){
                DisplayScreen.displayConsole(GREEN_BOLD + "You punched " + enemy.getName() + " right in the FACE" + RESET);
                enemy.setEnemyHealth(enemy.getEnemyHealth() - 15);
            }
            else {
                DisplayScreen.displayConsole(GREEN_BOLD + enemy.getName() + " stumbled towards you but fell over so you kick them in the head" + RESET);
                enemy.setEnemyHealth(enemy.getEnemyHealth() - 20);
            }
            DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());
            DisplayScreen.displayConsole(enemy.getName() + " health: " + enemy.getEnemyHealth());

        } else if (randomNum == 2) {
            DisplayScreen.displayConsole(RED_BOLD + enemy.getName() + " attacked you!" + RESET);
            player.setPlayerHp(Player.getPlayerHp() - 10);
            DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());
            DisplayScreen.displayConsole(enemy.getName() + " health: " + enemy.getEnemyHealth());

        } else if (randomNum == 3) {
            DisplayScreen.displayConsole(GREEN_BOLD + "You kicked dirt in " + enemy.getName() + "'s eyes" + RESET);
            enemy.setEnemyHealth(enemy.getEnemyHealth() - 10);
            DisplayScreen.displayConsole(enemy.getName() + " health is now: " + enemy.getEnemyHealth());
            DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());

        } else if (randomNum == 4) {
            DisplayScreen.displayConsole(GREEN_BOLD + "You put " + enemy.getName() + " in a full nelson! They are grasping for air " + RESET);
            enemy.setEnemyHealth(enemy.getEnemyHealth() - 25);
            DisplayScreen.displayConsole(enemy.getName() + " health is now: " + enemy.getEnemyHealth());
            DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());

        } else if (randomNum == 5){
            DisplayScreen.displayConsole(player.getPlayerName() + " tried to attack but got distracted by a buzzing bumble bee...");
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
