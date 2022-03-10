package com.totaleclipse.commands;

import com.totaleclipse.client.Client;
import com.totaleclipse.player.Player;
public class Actions {
    private Player player;
    private Command command;
    public Actions(Player player, Command command){
        this.player=player;
        this.command=command;
    }
    protected static void help() {
        StringBuilder helpString = new StringBuilder();
        for (var command : Commands.values()) {
            helpString.append(command.getKeyword() + ": " + command.getDescription());
            helpString.append(System.getProperty("line.separator"));
        }
        System.out.println("The actions you can perform are:\n" + helpString);
    }
    protected void move(String noun, Player player){
        int key=player.getLocation().getKey();
        switch(noun.toUpperCase()){
            case "NORTH"-> {
                key++;
                player.setLocation(Client.locationsMap.get(key));
                System.out.println(player.getLocation().getLook(0));
            }
            case "SOUTH"-> {
                key--;
                player.setLocation(Client.locationsMap.get(key));
                System.out.println(player.getLocation().getLook(0));
            }
        }
    }
    protected static void look(){
        System.out.println("look");
    }
    protected static void get(){
        System.out.println("get");
    }
    protected static void talk(){
        System.out.println("talk");
    }

}
