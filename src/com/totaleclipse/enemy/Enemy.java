package com.totaleclipse.enemy;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totaleclipse.client.DisplayScreen;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

public class Enemy {
    private String name;
    private int enemyHealth = 100;
    private int attackPower;

    public Enemy(){}

    public void Enemy(String name, int enemyHealth, int attackPower){
        setName(name);
        setEnemyHealth(enemyHealth);
        setAttackPower(attackPower);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", enemyHealth=" + enemyHealth +
                ", attackPower=" + attackPower +
                '}';
    }
}
