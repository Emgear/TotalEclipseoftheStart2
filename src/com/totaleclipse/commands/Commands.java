package com.totaleclipse.commands;

import java.util.Arrays;


public class Commands {
    String noun;
    String verb;

    public Commands(String commandVerb, String commandNoun) {
        this.noun = commandNoun;
        this.verb = commandVerb;
        this.verb = getSynonyms(commandVerb);
        getCommand(this.verb);
    }

    /**
     * Checks commandVerb for synonyms
     * @param commandVerb parsed verb used to search for synonym
     * @return keyword of synonym
     */
    private String getSynonyms(String commandVerb) {
        for (var localCommands : LocalCommands.values()) {
            if (localCommands.synonyms!=null) {
                for (var syn : localCommands.synonyms) {
                    if (syn.equalsIgnoreCase(commandVerb)) {
                        return localCommands.keyword;
                    }
                }
            }
        }
        return commandVerb;
    }

    /**
     * Checks to see which commandVerb keyword was entered.
     * @param command verb parsed from command input
     */
    private void getCommand(String command) {
        if (command.equalsIgnoreCase(LocalCommands.move.keyword)) {
            Actions.move(this.noun);
        } else if (command.equalsIgnoreCase(LocalCommands.get.keyword)) {
            Actions.get();
        } else if (command.equalsIgnoreCase(LocalCommands.look.keyword)) {
            Actions.look();
        } else if (this.verb.equalsIgnoreCase(LocalCommands.talk.keyword)) {
            Actions.talk();
        }else if(this.verb.equalsIgnoreCase("help")){
            Actions.help();
        }else if(this.verb.equalsIgnoreCase(LocalCommands.close.keyword)){
            System.exit(0);
        }
    }

    /**
     * Local command enum to check for the parsed verb
     */
    enum LocalCommands {
        move("move", "Moves the player in the selected direction, ie: 'move east'", "go", "walk", "run"),
        get("get", "Pick up an item, ie: 'get key'", "grab", "pickup"),
        look("look", "Looks around the current location, ie:'look around'", "search", "peek", "investigate"),
        save("save", "Saves user data to disk"),
        talk("talk", "Speaks with an NPC, ie: 'talk librarian", "speak", "question", "interrogate"),
        close("close", "Closes Total Eclipse of the Start", "quit","exit");
        String keyword;
        String description;
        String[] synonyms;


        /**
         * No parameter command constructor
         *
         * @param keyword     String that gets compared to first word user types
         * @param description String of what this command does as far as user is concerned
         */
        LocalCommands(String keyword, String description) {
            this.keyword = keyword;
            this.description = description;
        }

        /**
         * Parameterable command constructor
         *
         * @param keyword     String that gets compared to first word user types
         * @param description String of what this command does as far as user is concerned
         * @param synonyms    String vararg of known parameter options
         */
        LocalCommands(String keyword, String description, String... synonyms) {
            this.keyword = keyword;
            this.description = description;
            for (var s : synonyms) {
                this.synonyms = synonyms;
            }
        }

        @Override
        public String toString() {
            return "LocalCommands{" +
                    "keyword='" + keyword + '\'' +
                    ", description='" + description + '\'' +
                    ", synonyms=" + Arrays.toString(synonyms) +
                    '}';
        }
    }
}