package com.totaleclipse.client;

import com.totaleclipse.music.Music;

public class Client {

    public static void main(String[] args) {
        /* These outputs will likely be refactored into a separate class after their creation. */

        //Intro card to the game
        DisplayScreen.displayConsole("Total Eclipse of the .start()");
        DisplayScreen.displayConsole("A text-based mystery game full of conspiracy! " +
                "Are you human? Alien? Or something else entirely? " +
                "Uncover your past and discover your true identity!");
        DisplayScreen.displayConsole("Type \"start\" to start playing, or to exit the game, simply type \"quit\"\n\n");

        //player types start.

        //Explaining the start of the game
        DisplayScreen.displayConsole("You wake up in a crop circle, surrounded by corn. Your leather jacket is dirty.\nYou can't seem to remember who you are or how you ended up in the midst of all this corn, and you want answers.\nOver the corn in the distance, you can see a cow floating up into a disc-like shape in the sky, before it flies away.\nType out the action you wish to perform, with a verb first an noun second.\nFor example, you can look around by typing \"look north\" or \"look west\".\nFor more commands, type \"help\".");
        PlayGame newGame=new PlayGame();
        //Music.playMusic();
        newGame.playGame();
    }
}
