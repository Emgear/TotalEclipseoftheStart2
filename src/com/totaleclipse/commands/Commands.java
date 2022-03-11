package com.totaleclipse.commands;

import java.util.Arrays;

public enum Commands {
    move("move", "Moves the player in the selected direction, ie: 'move east'", "go", "walk", "run"),
    get("get", "Pick up an item, ie: 'get key'", "grab", "pickup", "take"),
    look("look", "Looks around the current location, ie:'look around'", "search", "peek", "investigate", "view"),
    save("save", "Saves user data to disk"),
    talk("talk", "Speaks with an NPC, ie: 'talk librarian'", "speak", "question", "interrogate"),
    close("close", "Closes Total Eclipse of the Start", "quit", "exit", "stop", "end"),
    music("music", "Stops/starts the music","sound");

    private String keyword;
    private String description;
    private String[] synonyms;

    /**
     * No parameter command constructor
     *
     * @param keyword     String that gets compared to first word user types
     * @param description String of what this command does as far as user is concerned
     */
    Commands(String keyword, String description) {
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
    Commands(String keyword, String description, String... synonyms) {
        this.keyword = keyword;
        this.description = description;
        for (var s : synonyms) {
            this.synonyms = synonyms;
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public String getDescription() {
        return description;
    }

    public String[] getSynonyms() {
        return synonyms;
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
