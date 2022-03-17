package com.totaleclipse.enemy;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Enemies {
    private static HashMap<String, Enemy> enemyMap = new HashMap<>();

    public Enemies(){};


    public static HashMap<String, Enemy> createEnemies(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            List<Enemy> enemies = Arrays.asList(mapper.readValue(Paths.get("src/com/totaleclipse/enemy/Enemies.json").toFile(), Enemy[].class));

            for (var enemy: enemies){
                enemyMap.put(enemy.getName(), enemy);
//                System.out.println(enemyMap);
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return enemyMap;
    }

    public static  HashMap<String, Enemy> makeEnemy(){
        return enemyMap;
    }
}
