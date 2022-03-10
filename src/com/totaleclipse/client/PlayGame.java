package com.totaleclipse.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totaleclipse.commands.Command;
import com.totaleclipse.commands.parseCommands;
import com.totaleclipse.location.Location;
import com.totaleclipse.player.Player;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.totaleclipse.client.Client.locationsMap;
import static com.totaleclipse.commands.parseCommands.parseName;

public class PlayGame {
    public static void playGame() {
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