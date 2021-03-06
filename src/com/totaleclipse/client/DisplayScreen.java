package com.totaleclipse.client;

import com.totaleclipse.enemy.Enemy;
import com.totaleclipse.location.Locations;

/**
 * Encapsulates printing in order to change output type as needed.
 */
public class DisplayScreen {
    public static String displayConsole(String string){
        System.out.println(string);
        return string;
    }

    public static int displayConsole(String string, Integer integer){
        System.out.println(string + integer);
        return integer;
    }

    public static Enemy displayConsole(Enemy enemy){
        System.out.println(enemy);
        return enemy;
    }

    public static int displayConsole(Integer integer){
        System.out.println(integer);
        return integer;
    }
}
