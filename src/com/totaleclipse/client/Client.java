package com.totaleclipse.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totaleclipse.commands.Command;
import com.totaleclipse.commands.parseCommands;
import com.totaleclipse.location.Location;
import com.totaleclipse.player.Player;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.totaleclipse.commands.parseCommands.parseName;

public class Client {
    public static HashMap<Integer, Location> locationsMap = new HashMap<>();

    public static void main(String[] args) {
        /* These outputs will likely be refactored into a separate class after their creation. */

        //Intro card to the game
        System.out.println("Total Eclipse of the .start()");
        System.out.println("A text-based mystery game full of conspiracy! " +
                "Are you human? Alien? Or something else entirely? " +
                "Uncover your past and discover your true identity!");
        System.out.println("Type \"start\" to start playing, or to exit the game, simply type \"quit\"\n\n");

        //player types start.

        //Explaining the start of the game
        System.out.println("""
                You wake up in a crop circle, surrounded by corn. Your leather jacket is dirty.
                You can't seem to remember who you are or how you ended up in the midst of all this corn, and you want answers.
                Over the corn in the distance, you can see a cow floating up into a disc-like shape in the sky, before it flies away.
                                
                Type out the action you wish to perform, with a verb first an noun second.
                \tFor example, you can look around by typing "look north" or "look west".
                \t\tFor more commands, type "help".""");
        boolean playing = true;
        String commandNoun, commandVerb;
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Location> locations = Arrays.asList(mapper.readValue(Paths.get("src/com/totaleclipse/location/Locations.json").toFile(), Location[].class));
            for (Location location : locations) {
                locationsMap.put(location.getKey(), location);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("What is your name?");
        Player player = new Player(parseName(), locationsMap.get(0));

        while (playing) {
            parseCommands com = new parseCommands();
            System.out.println("Enter your command");
            String[] commandArray = com.parseCommand();
            commandVerb = commandArray[0];
            commandNoun = "";
            if (commandArray.length > 1)
                commandNoun = commandArray[1];
            new Command(commandVerb, commandNoun, player);
        }
    }
}
