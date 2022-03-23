package com.totaleclipse.location;

import com.totaleclipse.client.DisplayScreen;
import com.totaleclipse.player.Player;
import java.util.ArrayList;
import java.util.List;

public class LocationMap {

    //map object for singleton
    private static LocationMap map;

    //private constructor to prevent outside instantiation
    private LocationMap(){}

    /**
     * Singleton factory for this map. Only one instance is ever needed.
     * @return The single instance of LocationMap
     */
    public static LocationMap getInstance(){
        if(map == null){
            map = new LocationMap();
        }
        return map;
    }

    /**
     * A partially-scalable map output the displays the locations of the map
     */
    public void printMap(Player player){

        //Add all strings to a list to help with readability
        List<String> names = new ArrayList<>();

        //scalable with size
        for(int i = Locations.locationsMap.size() - 2; i > 1; i--){
            String name = Locations.locationsMap.get(i).getLocation();

            //if the player is in that location, put a marker next to it
            if(name.equalsIgnoreCase(player.getLocation().getLocation())){
                name = " (X) -> " + name;
            }

            names.add(name);
        }

        /*
         * Marking player location on map, if they are in a static location
         */

        //if the player is in the static start location, put a marker next to it
        String start = Locations.locationsMap.get(0).getLocation();
        if(start.equalsIgnoreCase(player.getLocation().getLocation())){
            start = " (X) -> " + start;
        }

        //if the player is in the static second location, put a marker next to it
        String secondArea = Locations.locationsMap.get(1).getLocation();
        if(secondArea.equalsIgnoreCase(player.getLocation().getLocation())){
            secondArea = " (X) -> " + secondArea;
        }


        //number of locations in each row of the map
        int mapWidth = 3;
        //map height is determined based on the width of the map, not including the 3 static locations
        int mapHeight = names.size() / mapWidth;


        /*
         * Time to print the map. With borders so it looks more like a map
         */

        //top of map
//        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        DisplayScreen.displayConsole("-------------------------------------------------------------------------------------------------------------------------------");

        //fill each random location in map
        for(int i = 0; i < mapHeight; i++){
            //A list of names in the current row
            List<String> row = new ArrayList<>();

            for(int x = 0; x < mapWidth; x++){
                //index of the location is based on current row in map height, and current index in row.
                int index = x + (mapWidth *i);
                row.add(names.get(index));
            }
            //space each location out, and print another line for readability when printed
            System.out.format("|%32s%32s%32s%32s", row.get(0), row.get(1), row.get(2), "|\n");
            System.out.format("|%126s|\n", "");
            System.out.format("|%126s|\n", "");

        }

        //Static locations
        System.out.format("|%96s%32s", secondArea, "|\n");
        System.out.format("|%126s|\n", "");
        System.out.format("|%96s%32s", start, "|\n");

        //bottom of map
//        System.out.println("-------------------------------------------------------------------------------------------------------------------------------\n");
//        System.out.println("You are currently at the " + player.getLocation().getLocation() + ".\n");
        DisplayScreen.displayConsole("-------------------------------------------------------------------------------------------------------------------------------\n");
        DisplayScreen.displayConsole("You are currently at the " + player.getLocation().getLocation() + ".\n");
    }
}
