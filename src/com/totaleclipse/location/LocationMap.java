package com.totaleclipse.location;

import java.util.ArrayList;
import java.util.List;

public class LocationMap {

    private static LocationMap map; //map object for singleton

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
    public void printMap(){

        //Add all strings to a list to help with readability
        List<String> names = new ArrayList<>();

        //scalable with size
        for(int i = Locations.locationsMap.size() - 2; i > 1; i--){
            names.add(Locations.locationsMap.get(i).getLocation());
        }

        //map height is determined based on the width of the map, not including the 3 static locations
        //number of locations in each row of the map
        int mapWidth = 3;
        int mapHeight = names.size() / mapWidth;

        //top of map
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        //fill each random location in map
        for(int i = 0; i < mapHeight; i++){
            List<String> row = new ArrayList<>();
            for(int x = 0; x < mapWidth; x++){
                int index = x + (mapWidth *i);
                row.add(names.get(index));
            }
            System.out.format("|%32s%32s%32s%32s", row.get(0), row.get(1), row.get(2), "|\n");
            System.out.format("|%126s|\n", "");
        }

        //Static locations
        System.out.format("|%96s%32s", Locations.locationsMap.get(1).getLocation(), "|\n");
        System.out.format("|%126s|\n", "");
        System.out.format("|%96s%32s", Locations.locationsMap.get(0).getLocation(), "|\n");

        //bottom of map
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------\n");
    }
}
