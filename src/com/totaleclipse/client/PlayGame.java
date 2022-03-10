package com.totaleclipse.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totaleclipse.clues.Clue;
import com.totaleclipse.clues.Clues;
import com.totaleclipse.commands.Command;
import com.totaleclipse.commands.parseCommands;
import com.totaleclipse.location.Location;
import com.totaleclipse.location.Locations;
import com.totaleclipse.player.Player;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.totaleclipse.commands.parseCommands.parseName;

public class PlayGame {
    public boolean playing = true;

    public void playGame() {
        String commandNoun, commandVerb;
        HashMap<Integer, Location> locationsMap= Locations.generateLocations();
        HashMap<String, Clue> cluesMap= Clues.generateClues();
        System.out.println("What is your name?");
        Player player = new Player(parseName(), locationsMap.get(0), cluesMap.get("Crop Circle"));
        System.out.println(player.getLocation().getLook(0));
        while (playing) {
            parseCommands com = new parseCommands();
            System.out.println("Enter your command");
            String[] commandArray = com.parseCommand();
            commandVerb = commandArray[0];
            commandNoun = "";
            if (commandArray.length > 1)
                commandNoun = commandArray[1];
            new Command(commandVerb, commandNoun, player);
            if(player.getLocation().getLocation().equalsIgnoreCase("area 51")){
                if(player.getHumanity()>0){
                    System.out.println("You are a human and have discovered proof of alien life!!!");
                }else if(player.getHumanity()<0){
                    System.out.println("You are an alien and you have sent a message that Earth is ripe for the taking!!!");
                }else{
                    System.out.println("You are a cow. MOOOOOOOOOOOO!");
                }
                playing=false;
            }
        }
    }
}