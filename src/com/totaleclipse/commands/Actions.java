package com.totaleclipse.commands;

import com.totaleclipse.client.DisplayScreen;
import com.totaleclipse.clues.Clue;
import com.totaleclipse.clues.Clues;
import com.totaleclipse.location.LocationMap;
import com.totaleclipse.location.Locations;
import com.totaleclipse.music.SoundFx;
import com.totaleclipse.npc.NPC;
import com.totaleclipse.player.Journal;

import java.util.HashMap;

import static com.totaleclipse.player.Player.player;

/**
 * Takes the parsed command and compares the noun based on the verb used.
 */
public class Actions {
    static int key;
    private Command command;
    private static final LocationMap map = LocationMap.getInstance();
    private int grouping = 0;

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

        switch (noun.toLowerCase()) {
            case "inventory":
                DisplayScreen.displayConsole(Journal.getClueString());
                break;
            case "around":
                if (player.getLocation().getLocation() == Locations.locationsMap.get(0).getLocation()){
                    DisplayScreen.displayConsole("Guess what. There's corn.");
                    break;
                }
                if (key < 2) {
                    DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 1).getLook(1) + "\nTo the south " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the east there is corn\nTo the west... corn");

                } else {
                    printMap();
                }
                break;
            case "north":
                if (key < 2) {
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key + 1).getLook(1));

                } else {
                    try {
                        DisplayScreen.displayConsole(Locations.locationsMap.get(key + 3).getLook(1));
                    }catch(Exception e){
                        DisplayScreen.displayConsole("There is only corn.");
                    }

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
                if (player.getLocation().getLocation() == Locations.locationsMap.get(0).getLocation()){
                    DisplayScreen.displayConsole("Guess what. There's corn.");
                    break;
                }
                if (key > 1) {
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key - 3).getLook(1));

                } else
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key - 1).getLook(1));

                // DisplayScreen.displayConsole(player.getLocation().getLook(2));
                break;
            case "west":
                try {
                    if (player.getLocation().getLocation() == Locations.locationsMap.get(0).getLocation() || player.getLocation().getLocation() == Locations.locationsMap.get(1).getLocation()){
                        DisplayScreen.displayConsole("Guess what. There's corn.");
                    } else {
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key + 1).getLook(1)); }
                } catch(Exception e) {
                    DisplayScreen.displayConsole("Guess what. There's corn.");
                }
                break;

            case "map":
                map.printMap(player);
                break;
            case "hp":
                DisplayScreen.displayConsole("Current player health points: " + player.getPlayerHp());
                break;
            default:
                DisplayScreen.displayConsole("I don't see any " + noun.toLowerCase() + " around here.");
        }

    }

    /**
     * Gets an item from the given location. Player receives a Clue when the item is taken.
     * @param noun
     */
    protected void get(String noun) {
        HashMap<String, Clue> cluesMap = Clues.getClues();
        Clue clue;
        if (noun.equalsIgnoreCase(player.getClue().getItem())) {
            if (player.getClue().isStory()) {
                switch (player.getLocation().getLocation()) {
                    case "bunker":
                        clue = cluesMap.get("library");
                        break;
                    case "library":
                        clue = cluesMap.get("bar");
                        break;
                    case "bar":
                        clue = cluesMap.get("crash site");
                        break;
                    case "crash site":
                        clue = cluesMap.get("area 51");
                        break;
                    default:
                        clue = cluesMap.get("crop circle");
                }
            } else {
                clue = cluesMap.get(Locations.locationsMap.get(key).getLocation());

            }

            String clueString = clue.getClue();
            Journal.addClue(noun, clueString);
            DisplayScreen.displayConsole("The " + player.getClue().getItem() + " tells you " + clueString);
            player.getClue().removeItem();
        } else {
            DisplayScreen.displayConsole("That item is not here.");
        }

        player.setHumanity(-1);
    }

    /**
     * Player talks to the NPC at a given location. The first time the player speaks to them, the player receives a Clue. After
     * @param noun
     */
    protected void talk(String noun) {
        HashMap<String, Clue> cluesMap = Clues.getClues();
        Clue clue;
        if (noun.equalsIgnoreCase(player.getClue().getNpc())) {
            if (player.getClue().isStory()) {
                switch (player.getLocation().getLocation()) {
                    case "bunker":
                        clue = cluesMap.get("library");
                        break;
                    case "library":
                        clue = cluesMap.get("bar");
                        break;
                    case "bar":
                        clue = cluesMap.get("crash site");
                        break;
                    case "crash site":
                        clue = cluesMap.get("area 51");
                        break;
                    default:
                        clue = cluesMap.get("crop circle");
                }
            } else {
                clue = cluesMap.get(Locations.locationsMap.get(key).getLocation());

            }
            //if the player doesn't have the clue, the npc will give it to them. If they do have the clue, the npc spouts nonsense.
            if (!Journal.hasClue(clue)) {
                String clueString = clue.getClue();
                Journal.addClue(noun, clueString);
                System.out.println("You speak to the " + player.getClue().getNpc() + " and they tell you  " + clueString);
                player.setHumanity(1);
            } else {
                DisplayScreen.displayConsole(NPC.getRandomDialog());
            }
        }
    }

    protected void move(String noun) {
        key = player.getLocation().getKey();

        //call walking sound fx
        SoundFx.WALK.play();

        if (noun.equalsIgnoreCase("north")) {
            if (key < Locations.locationsMap.size()-2) {
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
                    try {
                        player.setLocation(Locations.locationsMap.get(key));
                        player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                        printMap();
                    }catch(Exception e){
                        DisplayScreen.displayConsole("There is only corn ahead.");
                        player.setLocation(Locations.locationsMap.get(key-3));
                    }
                }
            } else {
                DisplayScreen.displayConsole("There is only corn ahead.");
            }
        } else if (noun.equalsIgnoreCase("south")) {
            if (key == 0) {
                player.setLocation(Locations.locationsMap.get(999));
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
                if (key == 0 || key == 1){
                    DisplayScreen.displayConsole("There is nothing but corn");
                }
                else {
                    key--;
                    try {
                        player.setLocation(Locations.locationsMap.get(key));
                        player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                        printMap();
                    } catch (Exception e) {
                        DisplayScreen.displayConsole("There's nothing but corn");
                    }
                }

            }
        } else if (noun.equalsIgnoreCase("west")) {
            if (key % 3 == 1) {
                DisplayScreen.displayConsole("There is nothing but corn ahead.");
            } else {
                if (key == 0 || key == 1){
                    DisplayScreen.displayConsole("There is nothing but corn");
                }
                else {
                    key++;
                    try {
                        player.setLocation(Locations.locationsMap.get(key));
                        player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                        printMap();
                    } catch (Exception e) {
                        DisplayScreen.displayConsole("There's nothing but corn");
                    }
                }
            }
        }

    }

    public void printMap() {
        if (key % 3 == 2) {
            if (key < 5) {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(1).getLook(1) + "\nTo the east is corn\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));
            } else {
                try {
                    DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(key - 3).getLook(1) + "\nTo the east is corn as far as the eye can see" + "\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));
                } catch (Exception e) {
                    DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north lies corn" + "\nTo the south " + Locations.locationsMap.get(key - 3).getLook(1) + "\nTo the east is yet more corn"+ "\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));

                }
            }
        } else if (key % 3 == 1) {
            if (key < 5) {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(1).getLook(1) + "\nTo the east is " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west is corn");
            } else {
                try {
                    DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(key - 3).getLook(1) + "\nTo the east " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west is corn.");
                } catch (Exception e) {
                    DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north lies corn\nTo the south " + Locations.locationsMap.get(key - 3).getLook(1) + "\nTo the east " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west is corn.");

                }
            }
        } else if (key == 0) {
            try {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south is simply corn" + "\nTo the east is " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west is corn");
            }catch(Exception e){
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south is simply corn" + "\nTo the east is more corn" + "\nTo the west is corn");

            }
        } else {
            if (key < 5) {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(1).getLook(1) + "\nTo the east " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));
            } else if (key > 7) {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north is corn" + "\nTo the south " + Locations.locationsMap.get(1).getLook(1) + "\nTo the east " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));

            } else {
                DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 3).getLook(1) + "\nTo the south " + Locations.locationsMap.get(key - 3).getLook(1) + "\nTo the east " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));
            }
        }
    }
    /**
     * Player attacks an enemy/monster/zombie at a given location
     * @param noun
     */
    protected void attack(String noun){
        if (noun.equalsIgnoreCase(player.getLocation().getNpc())){
            DisplayScreen.displayConsole("Probably note a wise move to attack the " + noun);
            return;
        }
        DisplayScreen.displayConsole("Attacking the " + noun);

    }

    public void sound(String noun) {
        switch (noun.toLowerCase()) {
            case "low":
                SoundFx.volume = SoundFx.Volume.LOW;
                SoundFx.MUSIC.play();
                break;
            case "medium":
                SoundFx.volume = SoundFx.Volume.MEDIUM;
                SoundFx.MUSIC.play();
                break;
            case "high":
                SoundFx.volume = SoundFx.Volume.HIGH;
                SoundFx.MUSIC.play();
                break;
            case "off":
                SoundFx.volume = SoundFx.Volume.MUTE;
                SoundFx.MUSIC.stop();
                SoundFx.WALK.sound=false;
                break;
            case "on":
                SoundFx.MUSIC.play();
                SoundFx.volume = SoundFx.Volume.MEDIUM;
                SoundFx.WALK.sound=true;
                break;
        }

    }

}
