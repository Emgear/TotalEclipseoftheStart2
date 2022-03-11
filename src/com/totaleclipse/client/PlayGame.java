package com.totaleclipse.client;

import com.totaleclipse.clues.Clue;
import com.totaleclipse.clues.Clues;
import com.totaleclipse.commands.Command;
import com.totaleclipse.commands.parseCommands;
import com.totaleclipse.location.Location;
import com.totaleclipse.location.Locations;
import com.totaleclipse.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

import static com.totaleclipse.commands.parseCommands.parseName;

public class PlayGame {
    public boolean playing = true;

    public void playGame() {
        String commandNoun, commandVerb;
        HashMap<Integer, Location> locationsMap= Locations.generateLocations();
        HashMap<String, Clue> cluesMap= Clues.generateClues();
        DisplayScreen.displayConsole("What is your name?");
        Player player = new Player(parseName(), locationsMap.get(0), cluesMap.get("Crop Circle"));
        DisplayScreen.displayConsole(player.getLocation().getLook(0));
        while (playing) {
            parseCommands com = new parseCommands();
            DisplayScreen.displayConsole("Enter your command");
            ArrayList commandArray = com.parseCommand();
            commandVerb = (String) commandArray.get(0);
            commandNoun = "";
            if (commandArray.size() > 1)
                commandNoun = (String) commandArray.get(1);
            new Command(commandVerb, commandNoun, player);
            if(player.getLocation().getLocation().equalsIgnoreCase("area 51")){
                if(player.getHumanity()>0){
                    DisplayScreen.displayConsole("You are a human and have discovered proof of alien life!!!");
                }else if(player.getHumanity()<0){
                    DisplayScreen.displayConsole("You are an alien and you have sent a message that Earth is ripe for the taking!!!");
                }else{
                    DisplayScreen.displayConsole("You are a cow. MOOOOOOOOOOOO!");
                }
                playing=false;
            }
        }
    }
}