package com.totaleclipse.location;

public class LocationMap {

    private static LocationMap map;

    //private constructor to prevent outside instantiation
    private LocationMap(){}

    //singleton factory, only one instance of map is ever needed, if any
    public static LocationMap getInstance(){
        if(map == null){
            map = new LocationMap();
        }
        return map;
    }

    public void printMap(){
        System.out.println(Locations.locationsMap.get(10).getLocation()+"                        "+Locations.locationsMap.get(9).getLocation()+"                         "+Locations.locationsMap.get(8).getLocation());
        System.out.println(Locations.locationsMap.get(7).getLocation()+"                       "+Locations.locationsMap.get(6).getLocation()+"                          "+Locations.locationsMap.get(5).getLocation());
        System.out.println(Locations.locationsMap.get(4).getLocation()+"                     "+Locations.locationsMap.get(3).getLocation()+"                           "+Locations.locationsMap.get(2).getLocation());
        System.out.println("                                 "+Locations.locationsMap.get(1).getLocation());
        System.out.println("                                 "+Locations.locationsMap.get(0).getLocation());

    }
}
