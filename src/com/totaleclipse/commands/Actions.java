package com.totaleclipse.commands;

import com.totaleclipse.client.DisplayScreen;
import com.totaleclipse.clues.Clue;
import com.totaleclipse.clues.Clues;
import com.totaleclipse.location.Locations;
import com.totaleclipse.player.Journal;
import com.totaleclipse.player.Player;

import java.util.HashMap;

public class Actions {
    static int key;
    private Player player;
    private Command command;

    public Actions(Player player, Command command) {
        this.player = player;
        this.command = command;
    }

    protected static void help() {
        StringBuilder helpString = new StringBuilder();
        for (var command : Commands.values()) {
            helpString.append(command.getKeyword()).append(": ").append(command.getDescription());
            helpString.append(System.getProperty("line.separator"));
        }
        System.out.println("The actions you can perform are:\n" + helpString);
    }

    /**
     * look can be used to look in a direction, at an object, or to get general information about the area.
     *
     * @param noun the direction or object to be looked at
     */
    public static void look(String noun, Player player) {

        //Generic hard-coded information for the first area for the first sprint
        //Will be updated to change based on items/directions from that location
        switch (noun.toLowerCase()) {
            case "inventory":
                DisplayScreen.displayConsole(Journal.getClueString());
                break;
            case "around":
                System.out.println(player.getLocation().getLook(0)+" To the north "+Locations.locationsMap.get(key + 1).getLook(1)+" To the south "+Locations.locationsMap.get(key - 1).getLook(1));
                break;
            case "north":
                System.out.println(Locations.locationsMap.get(key + 1).getLook(1));
               // System.out.println(player.getLocation().getLook(1));
                break;
            case "east":
                System.out.println("There seems to be some corn.");
                break;
            case "south":
                System.out.println(Locations.locationsMap.get(key - 1).getLook(1));

               // System.out.println(player.getLocation().getLook(2));
                break;
            case "west":
                System.out.println("Guess what. There's corn.");
                break;

            case "object":
                System.out.println("The object seems to be a hastily scribbled note.");
                break;
            default:
                System.out.println("I don't see any " + noun.toLowerCase() + " around here.");
        }

    }

    protected static void get(String noun, Player player) {
        HashMap<String, Clue> cluesMap = Clues.getClues();
        if (noun.equalsIgnoreCase(player.getClue().getItem())) {
            Clue clue = cluesMap.get(Locations.locationsMap.get(key + 1).getLocation());
            String clueString = clue.getClue();
            Journal.addClue(noun, clueString);
            System.out.println("The " + player.getClue().getItem() + " tells you to " + clueString);
        }else{
            System.out.println("That item is not in the area.");
        }
        player.setHumanity(-1);
    }

    protected static void talk(String noun, Player player) {
        HashMap<String, Clue> cluesMap = Clues.getClues();
        if (noun.equalsIgnoreCase(player.getClue().getNpc())) {
            Clue clue = cluesMap.get(Locations.locationsMap.get(key + 1).getLocation());
            String clueString = clue.getClue();
            Journal.addClue(noun,clueString);
            System.out.println("You speak to the " + player.getClue().getNpc() + " and they tell you to " + clueString);
        }
        player.setHumanity(1);
    }

    protected void move(String noun, Player player) {
        key = player.getLocation().getKey();
        if (noun.equalsIgnoreCase("north")) {
            if(key<4) {
                key++;
                player.setLocation(Locations.locationsMap.get(key));
                player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                System.out.println(player.getLocation().getLook(0));
            }else{
                System.out.println("There is only corn ahead.");
            }
        } else if (noun.equalsIgnoreCase("south")) {
            if(key==0){
                player.setLocation(Locations.locationsMap.get(5));
                System.out.println(player.getLocation().getLook(0));
            }else {
                key--;
                player.setLocation(Locations.locationsMap.get(key));
                player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                System.out.println(player.getLocation().getLook(0));
            }
        }
    }

}
