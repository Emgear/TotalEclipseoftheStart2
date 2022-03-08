package com.totaleclipse.location;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Location {
    String location;
    String key;
    String npc;
    String item;
    String[] look;
    public Location(String key, String location, String search, String npc, String[] look){
        this.key=key;
        this.location=location;
        this.item=search;
        this.npc=npc;
        this.look=look;
    }
}
