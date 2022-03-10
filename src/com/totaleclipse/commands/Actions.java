package com.totaleclipse.commands;

import com.totaleclipse.client.Client;
import com.totaleclipse.player.Player;

public class Actions {
    private Player player;
    private Command command;

    public Actions(Player player, Command command) {
        this.player = player;
        this.command = command;
    }

    protected static void help() {
        StringBuilder helpString = new StringBuilder();
        for (var command : Commands.values()) {
            helpString.append(command.getKeyword() + ": " + command.getDescription());
            helpString.append(System.getProperty("line.separator"));
        }
        System.out.println("The actions you can perform are:\n" + helpString);
    }

    protected void move(String noun, Player player) {
        int key = player.getLocation().getKey();
        if (noun.equalsIgnoreCase("north")) {
            key++;
            player.setLocation(Client.locationsMap.get(key));
            System.out.println(player.getLocation().getLook(0));
        } else if (noun.equalsIgnoreCase("south")) {
            key--;
            player.setLocation(Client.locationsMap.get(key));
            System.out.println(player.getLocation().getLook(0));
        }
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
            case "around" : System.out.println(player.getLocation().getLook(0));
            break;
            case "north" : System.out.println(player.getLocation().getLook(1));
            break;
            case "east" : System.out.println("There seems to be some corn.");
            break;
            case "south" : System.out.println(player.getLocation().getLook(2));
            break;
            case "west" : System.out.println("Guess what. There's corn.");
            break;

            case "object" : System.out.println("The object seems to be a hastily scribbled note.");
            break;
            default : System.out.println("I don't see any " + noun.toLowerCase() + " around here.");
        }

    }

    protected static void get() {
        System.out.println("get");
    }

    protected static void talk() {
        System.out.println("talk");
    }

}
