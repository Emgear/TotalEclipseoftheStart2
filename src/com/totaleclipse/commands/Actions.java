package com.totaleclipse.commands;

import com.totaleclipse.client.DisplayScreen;
import com.totaleclipse.clues.Clue;
import com.totaleclipse.clues.Clues;
import com.totaleclipse.enemy.Enemies;
import com.totaleclipse.enemy.Enemy;
import com.totaleclipse.location.LocationMap;
import com.totaleclipse.location.Locations;
import com.totaleclipse.music.SoundFx;
import com.totaleclipse.npc.NPC;
import com.totaleclipse.player.Journal;
import com.totaleclipse.player.Player;

import java.util.HashMap;
import java.util.Objects;

import static com.totaleclipse.commands.AttackEngine.zombiesKilled;
import static com.totaleclipse.player.Player.getPlayerHp;
import static com.totaleclipse.player.Player.player;

/**
 * Takes the parsed command and compares the noun based on the verb used.
 */
public class Actions {
    static int key;
    private Command command;
    private static final LocationMap map = LocationMap.getInstance();
    private int grouping = 0;
    public static final String BLUE = "\033[0;34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static boolean finalClue = false;


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
        HashMap<String, Enemy> enemyMap = Enemies.makeEnemy();
        Enemy enemy;

        switch (noun.toLowerCase()) {
            case "stats":
            case "statistics":
                DisplayScreen.displayConsole("Zombies defeated: " + zombiesKilled);
                break;
            case "zombie":
            case "monster":
            case "enemy":
                hunt(noun);
                break;
            case "inventory":
            case "items":
                DisplayScreen.displayConsole(Journal.getClueString());
                break;
            case "around":
                if (player.getLocation().getLocation() == Locations.locationsMap.get(0).getLocation()) {
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
            case "up":
            case "nort":
                if (key < 2) {
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key + 1).getLook(1));
                } else {
                    try {
                        DisplayScreen.displayConsole(Locations.locationsMap.get(key + 3).getLook(1));
                    } catch (Exception e) {
                        DisplayScreen.displayConsole("There is only corn.");
                    }

                }
                // DisplayScreen.displayConsole(player.getLocation().getLook(1));
                break;
            case "east":
            case "right":
                if (key > 1) {
                    DisplayScreen.displayConsole(Locations.locationsMap.get(key - 1).getLook(1));
                } else
                    DisplayScreen.displayConsole("Corn, more corn.");
                break;
            case "south":
            case "down":
                if (player.getLocation().getLocation() == Locations.locationsMap.get(0).getLocation()) {
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
            case "left":
                try {
                    if (player.getLocation().getLocation() == Locations.locationsMap.get(0).getLocation() || player.getLocation().getLocation() == Locations.locationsMap.get(1).getLocation()) {
                        DisplayScreen.displayConsole("Guess what. There's corn.");
                    } else {
                        DisplayScreen.displayConsole(Locations.locationsMap.get(key + 1).getLook(1));
                    }
                } catch (Exception e) {
                    DisplayScreen.displayConsole("Guess what. There's corn.");
                }
                break;
            case "map":
                map.printMap(player);
                break;
            case "hp":
            case "health":
            case "life":
                DisplayScreen.displayConsole("Current player health points: " + player.getPlayerHp());
                break;
            case "aircraft":
                DisplayScreen.displayConsole("It appears to be a fresh wreckage");
                break;
            case "well":
                DisplayScreen.displayConsole("It looks historical");
                break;
            default:
                DisplayScreen.displayConsole("It is about what you would expect.");
        }
    }

    protected void hunt(String noun) {
        HashMap<String, Enemy> enemyMap = Enemies.makeEnemy();
        Enemy enemy;

        if (noun.equalsIgnoreCase("monster") || noun.equalsIgnoreCase("monsters") || noun.equalsIgnoreCase("zombie") || noun.equalsIgnoreCase("")){
            switch (player.getLocation().getLocation()) {
                case "bar":
                    enemy = enemyMap.get("Zombie Zack");
                    if ((enemy == null) || (enemy.getEnemyHealth() == 0)) {
                        DisplayScreen.displayConsole("Zombie Zack is already defeated!");
                        break;
                    }
                    DisplayScreen.displayConsole(enemy.getName() + " appeared!");
                    AttackEngine.run(player, enemy);
                    break;
                case "library":
                    enemy = enemyMap.get("Zombie Brit");
                    if ((enemy == null) || (enemy.getEnemyHealth() == 0)) {
                        DisplayScreen.displayConsole("Zombie Brit is already defeated!");
                        break;
                    }
                    DisplayScreen.displayConsole(enemy.getName() + " appeared!");
                    AttackEngine.run(player, enemy);
                    break;
                case "bookstore":
                    enemy = enemyMap.get("Zombie Amy");
                    if ((enemy == null) || (enemy.getEnemyHealth() == 0)) {
                        DisplayScreen.displayConsole("Zombie Amy is already defeated!");
                        break;
                    }
                    DisplayScreen.displayConsole(enemy.getName() + " appeared!");
                    AttackEngine.run(player, enemy);
                    break;
                case "cafe":
                    enemy = enemyMap.get("Zombie Julian");
                    if ((enemy == null) || (enemy.getEnemyHealth() == 0)) {
                        DisplayScreen.displayConsole("Zombie Julian is already defeated!");
                        break;
                    }
                    DisplayScreen.displayConsole(enemy.getName() + " appeared!");
                    AttackEngine.run(player, enemy);
                    break;
                case "crash site":
                    enemy = enemyMap.get("ZOMBIE BOSS");
                    if ((enemy == null) || (enemy.getEnemyHealth() == 0)) {
                        DisplayScreen.displayConsole("Zombie Boss is already defeated!");
                        break;
                    }
                    DisplayScreen.displayConsole(enemy.getName() + " appeared!");
                    AttackEngine.run(player, enemy);
                    break;
                default:
                    DisplayScreen.displayConsole("THE COAST IS CLEAR");
            }
        }
    }

    /**
     * Gets an item from the given location. Player receives a Clue when the item is taken.
     *
     * @param noun
     */
    protected void get(String noun) {
//        HashMap<String, Clue> cluesMap = Clues.getClues();
//        Clue clue;
        if (player.getLocation().getLocation().equalsIgnoreCase("bar") && (noun.equalsIgnoreCase("whiskey") || noun.equalsIgnoreCase("drink") || noun.equalsIgnoreCase("alcohol"))) {
            if (player.getPlayerHp() >= 91) {
                DisplayScreen.displayConsole("You're so full you feel like you're going to explode!");
                return;
            } else {
                DisplayScreen.displayConsole(GREEN_BOLD + "You chug the whiskey down in a matter of seconds. So delicious! And you feel great!" + ANSI_RESET);
                player.setPlayerHp(getPlayerHp() + 10);
                DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());
            }
        }
        else if (player.getLocation().getLocation().equalsIgnoreCase("cafe") && (noun.equalsIgnoreCase("coffee") || noun.equalsIgnoreCase("drink") || noun.equalsIgnoreCase("latte"))) {
            if (player.getPlayerHp() >= 91) {
                DisplayScreen.displayConsole("You're so full you feel like you're going to explode!");
                return;
            } else {
                DisplayScreen.displayConsole(GREEN_BOLD + "You ask the barista for a coffee and they hand you a fresh cup. How refreshing!" + ANSI_RESET);
                player.setPlayerHp(getPlayerHp() + 10);
                DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());
            }
        }
         else if (player.getLocation().getLocation().equalsIgnoreCase("park") && noun.equalsIgnoreCase("dog")) {
                DisplayScreen.displayConsole("The dog rolls over and absorbs your affection. How cute!");
                return;
        } else if (player.getLocation().getLocation().equalsIgnoreCase("crop circle") && noun.equalsIgnoreCase("corn")){
            if (player.getPlayerHp() >= 91) {
                DisplayScreen.displayConsole("You're so full you feel like you're going to explode!");
                return;
            } else {
                DisplayScreen.displayConsole(GREEN_BOLD + "You pick a husk of corn off the stock and eat it" + ANSI_RESET);
                player.setPlayerHp(getPlayerHp() + 5);
                DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());
            }
        } else if (player.getLocation().getLocation().equalsIgnoreCase("oasis") && noun.equalsIgnoreCase("lizard")) {
            DisplayScreen.displayConsole(RED + "The lizard hissed and ran towards you and bit your leg!!! You shake your leg frantically to get it off." + ANSI_RESET);
            player.setPlayerHp(getPlayerHp() - 8);
            DisplayScreen.displayConsole("Your health: " + Player.getPlayerHp());
            return;
        } else if (player.getLocation().getLocation().equalsIgnoreCase("library") && noun.equalsIgnoreCase("book")) {
            DisplayScreen.displayConsole("You open the book and all the dust smacks you in the face giving you the sneezes..");
            return;
        } else {
            DisplayScreen.displayConsole("Hmm that doesn't seem to be a good idea right now");
        }
    }
    /**
     * Player talks to the NPC at a given location. The first time the player speaks to them, the player receives a Clue. After
     *
     * @param noun
     */
    protected void talk(String noun) {
        HashMap<String, Clue> cluesMap = Clues.getClues();
        Clue clue;

        if (noun.equalsIgnoreCase(player.getClue().getNpc())) {
            if (player.getClue().isStory()) {
                switch (player.getLocation().getLocation()) {
                    case "bunker":
                        clue = cluesMap.get("bunker");
                        break;
                    case "library":
                        clue = cluesMap.get("library");
                        break;
                    case "bar":
                        clue = cluesMap.get("bar");
                        break;
                    case "crash site":
                        clue = cluesMap.get("crash site");
                        finalClue = true;
                        //trying this for final clue
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
                System.out.println("You speak to the " + player.getClue().getNpc() + " who tells you " + BLUE + clueString + ANSI_RESET);
                player.setHumanity(1);
            } else {
                DisplayScreen.displayConsole(NPC.getRandomDialog());
            }
        } else if (Objects.equals(player.getLocation().getLocation(), "park")) {
            if (noun.equalsIgnoreCase("dog")) {
                DisplayScreen.displayConsole("The " + noun + " says woof.");
            }
        } else if (noun.equalsIgnoreCase("")) {
            DisplayScreen.displayConsole("You start talking to yourself.");
        } else {
            System.out.println("You say hello to the " + noun + " but it does not respond back to you. Not sure what you were expecting?");
        }
    }

    protected void move(String noun) {
        key = player.getLocation().getKey();

        //call walking sound fx
        SoundFx.WALK.play();
        if (noun.equalsIgnoreCase("bookstore") || noun.equalsIgnoreCase("library") || noun.equalsIgnoreCase("bar") || noun.equalsIgnoreCase("crop circle") || noun.equalsIgnoreCase("park") || noun.equalsIgnoreCase("bunker")) {
            DisplayScreen.displayConsole("You unfortunately do not have teleportation powers and will need to move there on your own");
        }
        if (noun.equalsIgnoreCase("north") || noun.equalsIgnoreCase("up")) {
            if (key < Locations.locationsMap.size() - 2) {
                if (key == 0) {
                    key++;
                    player.setLocation(Locations.locationsMap.get(key));
                    player.setClue(Clues.getClues().get(player.getLocation().getLocation()));
                    DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north " + Locations.locationsMap.get(key + 1).getLook(1) + "\nTo the south " + Locations.locationsMap.get(key - 1).getLook(1) + "\nTo the east is corn\nTo the west is... corn");
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
                    } catch (Exception e) {
                        DisplayScreen.displayConsole("There is only corn ahead.");
                        player.setLocation(Locations.locationsMap.get(key - 3));
                    }
                }
            } else {
                DisplayScreen.displayConsole("There is only corn ahead.");
            }
        } else if (noun.equalsIgnoreCase("south") || noun.equalsIgnoreCase("down")) {
            if (key == 0) {
//                IF YOU HAVE THE FINAL CLUE AND CAN WIN THE GAME AREA 51 REVEALS ITSELF
                if ((finalClue) && (AttackEngine.bossKilled)) {
                    player.setLocation(Locations.locationsMap.get(999)); //area 51 location, ends game
                } else {
                    DisplayScreen.displayConsole("You see more corn. Maybe go a different direction?");
                }
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
        } else if (noun.equalsIgnoreCase("east") || noun.equalsIgnoreCase("right")) {
            if (key % 3 == 2) {
                DisplayScreen.displayConsole("Ahead lies nothing but corn.");
            } else {
                if (key == 0 || key == 1) {
                    DisplayScreen.displayConsole("There is nothing but corn");
                } else {
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
        } else if (noun.equalsIgnoreCase("west") || noun.equalsIgnoreCase("left")) {
            if (key % 3 == 1) {
                DisplayScreen.displayConsole("There is nothing but corn ahead.");
            } else {
                if (key == 0 || key == 1) {
                    DisplayScreen.displayConsole("There is nothing but corn");
                } else {
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
                    DisplayScreen.displayConsole(player.getLocation().getLook(0) + "\nTo the north lies corn" + "\nTo the south " + Locations.locationsMap.get(key - 3).getLook(1) + "\nTo the east is yet more corn" + "\nTo the west " + Locations.locationsMap.get(key + 1).getLook(1));
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
            } catch (Exception e) {
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
     *
     * @param noun
     */
    protected void attack(String noun) {
        if (noun.equalsIgnoreCase(player.getLocation().getNpc())) {
            DisplayScreen.displayConsole("The " + noun + " says " + RED + "\"Get away from me!\"" + ANSI_RESET + " and pushes you away.");
            player.setPlayerHp(getPlayerHp() - 10);
            System.out.println("Your health: " + Player.getPlayerHp());
            return;
        }
        if (noun.equalsIgnoreCase("")) {
            DisplayScreen.displayConsole("You must select something to attack.");
            return;
        }
        if (noun.equalsIgnoreCase("dog")) {
            DisplayScreen.displayConsole(RED + "Really? You would attack a harmless dog? The dog bites your leg and you cry like a baby." + ANSI_RESET);
            player.setPlayerHp(getPlayerHp() - 10);
            System.out.println("Your health: " + Player.getPlayerHp());
            return;
        }
        if (noun.equalsIgnoreCase("zombie") || noun.equalsIgnoreCase("monster")) {
            DisplayScreen.displayConsole("You don't see any monsters right now...maybe try hunting for one first?");
            return;
        }
        DisplayScreen.displayConsole("You smashed the " + noun);
        return;
//        AttackEngine.run(player, player.getEnemy());
    }

    public void sound(String noun) {
        switch (noun.toLowerCase()) {
            case "low":
            case "down":
            case "min":
                if (SoundFx.volume == SoundFx.Volume.LOW) {
                    DisplayScreen.displayConsole("This is as low as it gets.");
                }
                SoundFx.volume = SoundFx.Volume.LOW;
                SoundFx.MUSIC.play();
                break;
            case "medium":
                SoundFx.volume = SoundFx.Volume.MEDIUM;
                SoundFx.MUSIC.play();
                break;
            case "high":
            case "up":
            case "loud":
            case "max":
                if (SoundFx.volume == SoundFx.Volume.HIGH) {
                    DisplayScreen.displayConsole("This is as loud as it gets- wouldn't want to break your computer speakers would we?");
                }
                SoundFx.volume = SoundFx.Volume.HIGH;
                SoundFx.MUSIC.play();
                break;
            case "off":
            case "mute":
            case "silence":
            case "stop":
                if (SoundFx.volume == SoundFx.Volume.MUTE) {
                    DisplayScreen.displayConsole("The sound is already muted. If you would like to hear some crunk beats you may enter 'volume on' or another synonym for volume such as 'music' or 'tunes'");
                }
                SoundFx.volume = SoundFx.Volume.MUTE;
                SoundFx.MUSIC.stop();
                SoundFx.WALK.sound = false;
                break;

            case "on":
                if (SoundFx.volume == SoundFx.Volume.MEDIUM) {
                    DisplayScreen.displayConsole("The sound is already on. Do you need some hearing aids? Otherwise, turning up the volume is an option.");
                }
                SoundFx.volume = SoundFx.Volume.MEDIUM;
                SoundFx.MUSIC.play();
                SoundFx.WALK.sound = true;
                break;
        }
    }
}
