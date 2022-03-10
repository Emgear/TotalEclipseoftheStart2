package com.totaleclipse.clues;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Clues {
    private static HashMap<String, Clue> cluesMap=new HashMap<>();
    public Clues(){}
    public static HashMap<String, Clue> generateClues(){
        try{
            ObjectMapper mapper =new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // use generics
            List<Clue> clues = Arrays.asList(mapper.readValue(Paths.get("src/com/totaleclipse/clues/Clues.json").toFile(), Clue[].class));
            //for loop - probably figure out easier way after 1st sprint
            for(var clue: clues){
                cluesMap.put(clue.getLocation(), clue);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cluesMap;
    }
    public static HashMap<String, Clue> getClues(){
        return cluesMap;
    }
}
