package com.totaleclipse.commands;

import com.totaleclipse.music.Music;

public class Command {
    private String noun;
    private String verb;
    public Command(){}
    public Command(String commandVerb, String commandNoun) {
        this.noun = commandNoun;
        this.verb = commandVerb;
        this.verb = getSynonyms();
        getCommand(this.verb);
        getNoun();
    }

    /**
     * Checks commandVerb for synonyms
     * @return keyword of synonym
     */
    private String getSynonyms() {
        for (var localCommands : Commands.values()) {
            if (localCommands.getSynonyms()!=null) {
                for (var syn : localCommands.getSynonyms()) {
                    if (syn.equalsIgnoreCase(this.verb)) {
                        return localCommands.getKeyword();
                    }
                }
            }
        }
        return this.verb;
    }
    protected String getNoun(){
        return this.noun;
    }

    /**
     * Checks to see which commandVerb keyword was entered.
     * @param commandVerb verb parsed from command input
     */
    private void getCommand(String commandVerb) {
        Actions action=new Actions(this);
        if (commandVerb.equalsIgnoreCase(Commands.move.getKeyword())) {
            action.move(this.noun);
        } else if (commandVerb.equalsIgnoreCase(Commands.get.getKeyword())) {
            Actions.get(this.noun);
        } else if (commandVerb.equalsIgnoreCase(Commands.look.getKeyword())) {
            Actions.look(this.noun);
        } else if (this.verb.equalsIgnoreCase(Commands.talk.getKeyword())) {
            Actions.talk(this.noun);
        }else if(this.verb.equalsIgnoreCase("help")){
            Actions.help();
        }else if(this.verb.equalsIgnoreCase(Commands.close.getKeyword())){
            System.exit(0);
        }else if(this.verb.equalsIgnoreCase(Commands.music.getKeyword())){
            if(Music.playing){
                Music.stopMusic();
            }else{
                Music.playMusic();
            }
        }
    }
}
