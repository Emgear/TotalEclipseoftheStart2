package com.totaleclipse.commands;

import com.totaleclipse.client.DisplayScreen;
import com.totaleclipse.music.Music;
import com.totaleclipse.music.SoundFx;
import com.totaleclipse.music.SoundFx.Volume;

public class Command {
    private String noun;
    private String verb;

    public Command() {
    }

    public Command(String commandVerb, String commandNoun) {
        this.noun = commandNoun;
        this.verb = commandVerb;
        this.verb = getSynonyms();
        getCommand(this.verb);
    }

    /**
     * Checks commandVerb for synonyms
     *
     * @return keyword of synonym
     */
    private String getSynonyms() {
        for (var localCommands : Commands.values()) {
            if (localCommands.getSynonyms() != null) {
                for (var syn : localCommands.getSynonyms()) {
                    if (syn.equalsIgnoreCase(this.verb)) {
                        return localCommands.getKeyword();
                    }
                }
            }
        }
        return this.verb;
    }


    /**
     * Checks to see which commandVerb keyword was entered.
     *
     * @param commandVerb verb parsed from command input
     */
    private void getCommand(String commandVerb) {
        Actions action = new Actions(this);
        if (commandVerb.equalsIgnoreCase(Commands.move.getKeyword())) {
            action.move(this.noun);
        } else if (commandVerb.equalsIgnoreCase(Commands.hunt.getKeyword())){
                action.hunt(this.noun);
        } else if (commandVerb.equalsIgnoreCase(Commands.get.getKeyword())) {
            action.get(this.noun);
        } else if (commandVerb.equalsIgnoreCase(Commands.look.getKeyword())) {
            action.look(this.noun);
        } else if (this.verb.equalsIgnoreCase(Commands.talk.getKeyword())) {
            action.talk(this.noun);
        } else if (this.verb.equalsIgnoreCase("help")) {
            action.help();
        } else if (this.verb.equalsIgnoreCase(Commands.close.getKeyword())) {
            System.exit(0);
        }else if (this.verb.equalsIgnoreCase(Commands.sound.getKeyword())) {
            action.sound(this.noun);
//                SoundFx.volume = Volume.LOW;
                SoundFx.WALK.sound=true;
        } else if(this.verb.equalsIgnoreCase(Commands.attack.getKeyword())){
            action.attack(this.noun);
        } else{
            DisplayScreen.displayConsole("This action cannot be executed. Please choose a different command or type \"help\" command for additional info.");
        }
    }
}

