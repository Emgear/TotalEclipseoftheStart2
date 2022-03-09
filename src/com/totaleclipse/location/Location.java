package com.totaleclipse.location;


import java.util.Arrays;

public class Location {
    String location;
    int key;
    String npc;
    String item;
    String[] look;

    public Location() {
    }

    public Location(String key, String location, String item, String npc, String[] look) {

        setLocation(location);
        setKey(key);
        setItem(item);
        setNpc(npc);
        setLook(look);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = Integer.parseInt(key);
    }

    public String getNpc() {
        return npc;
    }

    public void setNpc(String npc) {
        this.npc = npc;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String[] getLook() {
        return look;
    }

    public void setLook(String[] look) {
        this.look = look;
    }

    @Override
    public String toString() {
        return "Location{" +
                "location='" + this.location + '\'' +
                ", npc='" + npc + '\'' +
                ", item='" + item + '\'' +
                ", look=" + Arrays.toString(look) +
                '}';
    }
}
