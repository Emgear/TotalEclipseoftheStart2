package com.totaleclipse.commands;

import com.totaleclipse.client.DisplayScreen;
import com.totaleclipse.clues.Clue;
import com.totaleclipse.clues.Clues;
import com.totaleclipse.location.Locations;
import com.totaleclipse.npc.NPC;
import com.totaleclipse.player.Journal;

import java.util.HashMap;

import static com.totaleclipse.player.Player.player;

public class Actions {
    static int key;
    private Command command;

    public Actions(Command command) {
        this.command = command;
    }

    protected void help() {
        StringBuilder helpString = new StringBuilder();
        for (var command : Commands.values()) {
            helpString.append(command.getKeyword()).append(": ").append(command.getDescription());
            helpString.append(System.getProperty("line.separator"));
        }
        DisplayScreen.displayConsole("The actions you can perform are:\n" + helpString);
    }

    /**
     * look can be used to look in a direction, at an object, or to get general information about the area.
     *
     * @param noun the direction or object to be looked at
     */
    public void look(String noun) {

        //Generic hard-coded information for the first area for the first sprint
        //Will be updated to change based on items/directions from that location
        switch (noun.toLowerCase()) {
            case "inventory":
                DisplayScreen.displayConsole(Journal.getClueString());
                break;
            case "around":
                if (key < 2) {
                    DisplayScreen.displayConsole(player.getLocation().getLook(0) + " To the north " + Locations.locationsMap.get(key + 1).getLook(1) + "\nTo the south " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the east there is corn\nTo the west... corn");

                } else {
                    printMap();
                }
                break;
            case "north":
                if (key < 2) {
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key + 1).getLook(1));

                } else {
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key + 3).getLook(1));

                }
                // DisplayScreen.displayConsole(player.getLocation().getLook(1));
                break;
            case "east":
                if (key > 1) {
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key - 1).getLook(1));
                } else
                    DisplayScreen.displayConsole("Corn, more corn.");


                break;
            case "south":
                if (key > 1) {
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key - 3).getLook(1));

                } else
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key - 1).getLook(1));

                // DisplayScreen.displayConsole(player.getLocation().getLook(2));
                break;
            case "west":
                if (key > 1) {
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key + 1).getLook(1));
                } else
                    DisplayScreen.displayConsole("Guess what. There's corn.");
                break;

            case "object":
                DisplayScreen.displayConsole("The object seems to be a hastily scribbled note.");
                break;
            default:
                DisplayScreen.displayConsole("I don't see any " + noun.toLowerCase() + " around here.");
        }

    }

    protected void get(String noun) {
        HashMap<String, Clue> cluesMap = Clues.getClues();
        if (noun.equalsIgnoreCase(player.getClue().getItem())) {
            Clue clue = cluesMap.get(Locations.locationsMap.get(key + 1).getLocation());
            String clueString = clue.getClue();
            Journal.addClue(noun, clueString);
            DisplayScreen.displayConsole("The " + player.getClue().getItem() + " tells you to " + clueString);
            player.getClue().removeItem();
        } else {

        }
        player.setHumanity(-1);
    }

    protected void talk(String noun) {
        HashMap<String, Clue> cluesMap = Clues.getClues();
        if (noun.equalsIgnoreCase(player.getClue().getNpc())) {
            Clue clue = cluesMap.get(Locations.locationsMap.get(key + 1).getLocation());

            //if the player doesn't have the clue, the npc will give it to them. If they do have the clue, the npc spouts nonsense.
            if(!Journal.hasClue(clue)){
                String clueString = clue.getClue();
                Journal.addClue(noun, clueString);
                System.out.println("You speak to the " + player.getClue().getNpc() + " and they tell you to " + clueString);
                player.setHumanity(1);
            }else{
                DisplayScreen.displayConsole(NPC.getRandomDialog());
            }
        }
    }

    protected void move(String noun) {
        key = player.getLocation().getKey();
        if (noun.equalsIgnoreCase("north")) {
            if (key < Locations.locationsMap.size()) {
                if (key == 0) {
                    key++;
                    player.setLocation(Locations.locationsMap.get(key));
                    player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                    DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 1).getLook(1) + "\nTo the south " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the east is corn\n To the west is... corn");

                } else if (key == 1) {
                    key++;
                    player.setLocation(Locations.locationsMap.get(key));
                    player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                    printMap();
                } else {
                    key += 3;
                    player.setLocation(Locations.locationsMap.get(key));
                    player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                    printMap();
                }
            } else {
                DisplayScreen.displayConsole("There is only corn ahead.");
            }
        } else if (noun.equalsIgnoreCase("south")) {
            if (key == 0) {
                player.setLocation(Locations.locationsMap.get(11));
                printMap();
            } else if (key == 1) {
                key--;
                player.setLocation(Locations.locationsMap.get(key));
                player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                printMap();
            } else if (key < 5) {
                key = 1;
                player.setLocation(Locations.locationsMap.get(key));
                player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                printMap();
            } else {
                key -= 3;
                player.setLocation(Locations.locationsMap.get(key));
                player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                printMap();
            }
        } else if (noun.equalsIgnoreCase("east")) {
            if (key % 3 == 2) {
                DisplayScreen.displayConsole("Ahead lies nothing but corn.");
            } else {
                key--;
                player.setLocation(Locations.locationsMap.get(key));
                player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                printMap();
            }
        } else if (noun.equalsIgnoreCase("west")) {
            if (key % 3 == 1) {
                DisplayScreen.displayConsole("There is nothing but corn ahead.");
            } else {
                key++;
                player.setLocation(Locations.locationsMap.get(key));
                player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                printMap();
            }
        }
    }

    public void printMap() {
        if (key % 3 == 2) {
            if (key < 5) {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + " To the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(1).getLook(1) + "\nTo the east is corn\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));
            } else {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + " To the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(key - 3).getLook(1) + "\nTo the east " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));
            }
        } else if (key % 3 == 1) {
            if (key < 5) {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + " To the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(1).getLook(1) + "\nTo the east is " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west is corn");
            } else {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + " To the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(key - 3).getLook(1) + "\nTo the east " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west is corn.");
            }
        } else {
            if (key < 5) {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + " To the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(1).getLook(1) + "\nTo the east " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));
            } else {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + " To the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(key - 3).getLook(1) + "\nTo the east " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));
            }
        }
    }
}
