package com.totaleclipse.client;

import com.totaleclipse.GameScreen.Game;
import com.totaleclipse.enemy.Enemy;
import com.totaleclipse.location.Locations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Encapsulates printing in order to change output type as needed.
 */
public class DisplayScreen {
    public static String displayConsole(String string){
        System.out.println(string);
        Game.setGameText(string);

        return string;
    }

    public static int displayConsole(String string, Integer integer){
        System.out.println(string + integer);
        Game.setGameText(string, integer);

        return integer;
    }

    public static Enemy displayConsole(Enemy enemy){
        System.out.println(enemy);
        Game.setGameText(enemy);

        return enemy;
    }

    public static int displayConsole(Integer integer){
        System.out.println(integer);
        Game.setGameText(integer);

        return integer;
    }

    public static void displayConsole(char cow) {
        Game.setGameText(String.valueOf(cow));
    }
}
