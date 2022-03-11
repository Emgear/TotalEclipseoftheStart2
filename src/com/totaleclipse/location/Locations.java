package com.totaleclipse.location;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Locations {
    public static HashMap<Integer, Location> locationsMap = new HashMap<>();
    public static HashMap<Integer, Location> startLocations=new HashMap<>();

    public static HashMap<Integer, Location> generateLocations(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Location> locations = Arrays.asList(mapper.readValue(Paths.get("src/com/totaleclipse/location/LocationsTester.json").toFile(), Location[].class));
            for (Location location : locations) {
                startLocations.put(location.getKey(), location);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return startLocations;
    }
    public static HashMap<Integer, Location> newLocations(HashMap<Integer, Location> locations){
        for(int key:locations.keySet()){
            locationsMap.put(locations.get(key).getKey(), locations.get(key));
        }
        return locationsMap;
    }
}
