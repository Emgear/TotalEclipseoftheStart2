package com.totaleclipse.client;

import com.totaleclipse.location.Locations;

/**
 * Encapsulates printing in order to change output type as needed.
 */
public class DisplayScreen {
    public static String displayConsole(String string){
        System.out.println(string);
        return string;
    }
    public static void displayMap(){
        System.out.println(Locations.locationsMap.get(10).getLocation()+"                        "+Locations.locationsMap.get(9).getLocation()+"                         "+Locations.locationsMap.get(8).getLocation());
        System.out.println(Locations.locationsMap.get(7).getLocation()+"                       "+Locations.locationsMap.get(6).getLocation()+"                          "+Locations.locationsMap.get(5).getLocation());
        System.out.println(Locations.locationsMap.get(4).getLocation()+"                     "+Locations.locationsMap.get(3).getLocation()+"                           "+Locations.locationsMap.get(2).getLocation());
        System.out.println("                                 "+Locations.locationsMap.get(1).getLocation());
        System.out.println("                                 "+Locations.locationsMap.get(0).getLocation());

    }
}
