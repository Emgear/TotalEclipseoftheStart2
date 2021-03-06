package com.totaleclipse.client;

import com.totaleclipse.clues.Clue;
import com.totaleclipse.clues.Clues;
import com.totaleclipse.commands.Command;
import com.totaleclipse.commands.parseCommands;
import com.totaleclipse.enemy.Enemies;
import com.totaleclipse.enemy.Enemy;
import com.totaleclipse.location.Location;
import com.totaleclipse.location.Locations;
import com.totaleclipse.music.SoundFx;
import com.totaleclipse.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.totaleclipse.commands.parseCommands.parseName;

/**
 * Sets up and plays the game
 */
public class PlayGame {
    public boolean playing = true;
    int rand;
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    /**
     * Sets up the game by reading Locations.json and randomizing the order of the interior locations
     */
    public void setUp() throws InterruptedException {
        /* These outputs will likely be refactored into a separate class after their creation. */
        SoundFx.volume = SoundFx.Volume.HIGH;
        SoundFx.TOTALECLIPSE.play1();
        //Explaining the start of the game
        String cow = GREEN_BRIGHT+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%. .. .  /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(. ...... . ....... .*&@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@# .... ..... ............. ,@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@% ........................ ... *@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@%. ............................ .,@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@&  ............................... #@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@&#/,...,( ................................  #. ..*(%@@@@@@@@@@@@@@\n" +
                "@@@@@&%*.  ........  ,/ . ........................ .  ... #..........   ,#&@@@@@\n" +
                "@&,....................%(   ... .....................  *%* ................. .#@\n" +
                ".......... ..... ........  ,(%%(/*,..       .,**(#%#*. .................. ......\n" +
                " ... .. .............. ... .........  .    ......... .. .............. .........\n" +
                "@/ ... (@@@%* .... .................................................,#@@@# .. .%\n" +
                "@@@&, ....   .. .. .     .......... ..... .. ..... . ..  .   .......   . .. #@@@\n" +
                "@@@@@@@&#,  ........,%@@@@@/.......  /%%&%%/........ ,@@@@@%,.......  ./%@@@@@@@\n" +
                "@@@@@@@@@@@@@@&#*,.   ............ . ,(#%%(, ............ .   .*(&@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@&&%#(//*,,.......,**/((%%&@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&.(((////****/////(#/(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,@@@@@@@@@@@@@@@@@@@@*(@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@,&@@@@@@@@@@@@@@@@@@@@@/%@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@,%@@@@@@@@@@@@@@@@@@@@@@@,@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@(%@@@@@@@@@@@@@@@@@@@@@@@@@.@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@%/@@@@@@@@@@@@@@@@@@@@@@@@@@&/@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@%.@@@@@@@@@@@@@@@@@@@@@@@@@@@@(/@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@((@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@.&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/&@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@*&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ &@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@(#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@(*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%*@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@%*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#*@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@##@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@.&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*%@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@/&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,&@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@/(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&*@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@(/@@@@@@@@@@@@@@@@@@@@@@@@@@@@( &@@@@@@@@@@(*@@@%.@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@&/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/ ,#*     .(/  @@@@&/@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/... ......&@@@@@@(#@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@,@@@@@@@@%.                    .(//% ((.,# *%##@@@@@@,%@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@*%@@@@@@@,... . ................. . #,..... %@@@@@@@@@@*&@@@@@@@@@@@\n" +
                "@@@@@@@@@@@*(@@@@@@@%%/......................... #.. . ,@@@@@@@@@@@&,@@@@@@@@@@@\n" +
                "@@@@@@@@@@#(@@@@@@@@%#@ ..........................&.. (@@@@@@@@@@@@@&.@@@@@@@@@@\n" +
                "@@@@@@@@@&*@@@@@@@@@%#@% ...... ............ . ..... %@@@@@@@@@@@@@@@%(@@@@@@@@@\n" +
                "@@@@@@@@&.@@@@@@@@@@*.@@,.....   .     .  ...... ... &@@@@@@@@@@@@@@@@/#@@@@@@@@\n" +
                "@@@@@@@@*@@@@@@@@@@@&%@&.... %@@@@@@@@@@@@@@. ./@#.. @@@@@@@@@@@@@@@@@@*#@@@@@@@\n" +
                "@@@@@@@,%@@@@@@@@@@@@@& .. /@@@@@@@@@@@@@@@@.. %@@.. @@@@@@@@@@@@@@@@@@@*@@@@@@@\n" +
                "@@@@@@/#@@@@@@@@@@@@@@@...*@@@@@@@@@@@@@@@@@. ,@@@*  @@@@@@@@@@@@@@@@@@@&.@@@@@@\n" +
                "@@@@@%(@@@@@@@@@@@@@@@@,. #@@@@@@@@@@@@@@@@@. #@@@# .@@@@@@@@@@@@@@@@@@@@@*@@@@@\n" +
                "@@@@%,@@@@@@@@@@@@@@@@@#((@@@@@@@@@@@@@@@@@@((@@@@@**@@@@@@@@@@@@@@@@@@@@@#(@@@@\n" +
                "@@@&,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/(@@@\n" +
                "@@@,&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/%@@\n" +
                "@@.%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,@@ \n"+ ANSI_RESET + "\nYou wake up in a crop circle, surrounded by corn. Your leather jacket is dirty.\n" +
                "You can't seem to remember who you are or how you ended up in the midst of all this corn, and you want answers.\n" +
                "Over the corn in the distance, you can see a cow floating up into a disc-like shape in the sky, before it flies away.\n" +
                "\t- Type out the action you wish to perform, with a verb first and a noun second.\n" +
                "\t- For example, you can look around by typing \"look north\" or \"look west\", or look at your map by typing \"look map\".\n" +
                "\t\t- For more commands, type \"help\".\n\n";
        char[] cows = cow.toCharArray();


        // Print a char from the array, then sleep for 1/10 second

        for (int i = 0; i < cows.length; i++) {
            System.out.print(cows[i]);
            Thread.sleep(1);
        }

        //Intro card to the game
        DisplayScreen.displayConsole("Welcome to Total Eclipse of the .start()");
        DisplayScreen.displayConsole("A text-based mystery game full of conspiracy! " +
                "Are you human? Alien? Or something else entirely? " +
                "Uncover your past and discover your true identity!" + " \nYour goal is to find a way out of the corn and bring back your memory. Your actions throughout the game will impact your game result so act wisely!");
        DisplayScreen.displayConsole("\t- To exit the game, simply type \"quit\".");
        DisplayScreen.displayConsole("\t- You can look at all of the clues that you have collected by looking at your inventory.");
        DisplayScreen.displayConsole("\t- You begin the game with only a map.");
        HashMap<Integer, Location> locationsMap = Locations.generateLocations();
        ArrayList range = new ArrayList();
        for (int i = 0; i < locationsMap.size() - 3; i++) {
            range.add(502 + i);
        }
        Collections.shuffle(range);
        for (int i = 0; i < range.size(); i++) {
            if (i != range.size()) {
                locationsMap.get(range.get(i)).setKey(String.valueOf(i + 2));
            }
        }
        HashMap<Integer, Location> finalLocations = Locations.newLocations(locationsMap);
        playGame(finalLocations);
    }

    /**
     * Main driver of the game. Calls parseCommand and checks end of game conditions.
     *
     * @param locationsMap A HashMap of the locations in randomized order
     */
    public void playGame(HashMap<Integer, Location> locationsMap) {
        String commandNoun, commandVerb;
        HashMap<String, Clue> cluesMap = Clues.generateClues();
        HashMap<String, Enemy> enemyHashMap = Enemies.createEnemies();

        DisplayScreen.displayConsole("What is your name?");
        Player player = Player.getInstance(parseName(), locationsMap.get(0), cluesMap.get("crop circle"), 100,enemyHashMap.get("MONSTER"));
        DisplayScreen.displayConsole(player.getLocation().getLook(0));
        SoundFx.TOTALECLIPSE.stop();
        SoundFx.MUSIC.play();
        while (playing) {
            if (Player.getPlayerHp() <= 0){
                DisplayScreen.displayConsole(RED_BACKGROUND_BRIGHT + "You have died. GAME OVER" + ANSI_RESET);
                playing = false;
                break;
            }
            parseCommands com = new parseCommands();
            DisplayScreen.displayConsole("Enter your command:");
            ArrayList commandArray = com.parseCommand();
            commandVerb = (String) commandArray.get(0);
            commandNoun = "";
            if (commandArray.size() > 1)
                commandNoun = (String) commandArray.get(1);
            new Command(commandVerb, commandNoun);
            try {
                if (player.getLocation().getLocation().equalsIgnoreCase("area 51")) {
                    DisplayScreen.displayConsole(player.getLocation().getLook(0));
                    if (player.getHumanity() > 0) {
                        DisplayScreen.displayConsole(GREEN_BRIGHT + "You are a human and have discovered proof of alien life!!!" + ANSI_RESET);
                    } else if (player.getHumanity() < 0) {
                        DisplayScreen.displayConsole(GREEN_BRIGHT + "You are an alien and you have sent a message that Earth is ripe for the taking!!!" + ANSI_RESET);
                    } else {
                        DisplayScreen.displayConsole(GREEN_BRIGHT + "You are a cow. MOOOOOOOOOOOO!" + ANSI_RESET);
                    }
                    playing = false;
                }
            } catch (Exception e) {
            }
        }
    }
}