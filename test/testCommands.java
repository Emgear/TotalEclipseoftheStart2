import com.fasterxml.jackson.databind.ObjectMapper;
import com.totaleclipse.commands.parseCommands;
import com.totaleclipse.location.Location;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class testCommands {

    public static void main(String[] args) {
        parseCommands com=new parseCommands();
        System.out.println("Enter your command");
        com.parseCommand();
        HashMap<Integer, Location> locationsMap=new HashMap<>();
        try{
        ObjectMapper mapper =new ObjectMapper();
        List<Location> locations = Arrays.asList(mapper.readValue(Paths.get("src/com/totaleclipse/location/Locations.json").toFile(), Location[].class));
        for(Location location:locations){
            locationsMap.put(location.getKey(), location);
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for(var l:locationsMap.keySet()){
            System.out.println(locationsMap.get(l));
        }
        String[] directions=locationsMap.get(1).getLook();
        System.out.println(directions[2]);
    }
}
