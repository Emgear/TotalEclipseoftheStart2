package com.totaleclipse.commands;

import com.totaleclipse.player.Player;

public class Command {
    private String noun;
    private String verb;
    Player player;
    public Command(){}
    public Command(String commandVerb, String commandNoun, Player player) {
        this.noun = commandNoun;
        this.verb = commandVerb;
        this.player=player;
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
        Actions action=new Actions(player, this);
        if (commandVerb.equalsIgnoreCase(Commands.move.getKeyword())) {
            action.move(this.noun, this.player);
        } else if (commandVerb.equalsIgnoreCase(Commands.get.getKeyword())) {
            Actions.get(this.noun, this.player);
        } else if (commandVerb.equalsIgnoreCase(Commands.look.getKeyword())) {
            Actions.look(this.noun, this.player);
        } else if (this.verb.equalsIgnoreCase(Commands.talk.getKeyword())) {
            Actions.talk(this.noun, this.player);
        }else if(this.verb.equalsIgnoreCase("help")){
            Actions.help();
        }else if(this.verb.equalsIgnoreCase(Commands.close.getKeyword())){
            System.exit(0);
        }
    }
}
