//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.totaleclipse.commands.Command;
//import com.totaleclipse.commands.parseCommands;
//import com.totaleclipse.location.Location;
//import com.totaleclipse.player.Player;
//
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//public class testCommands {
//
//    public static void main(String[] args) {
//        boolean playing = true;
//        String commandNoun, commandVerb;
//        HashMap<Integer, Location> locationsMap = new HashMap<>();
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            List<Location> locations = Arrays.asList(mapper.readValue(Paths.get("src/com/totaleclipse/location/Locations.json").toFile(), Location[].class));
//            for (Location location : locations) {
//                locationsMap.put(location.getKey(), location);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        Player player=new Player("Zack",locationsMap.get(0));
//
//        while (playing) {
//            System.out.println(player.getLocation());
//            parseCommands com = new parseCommands();
//            System.out.println("Enter your command");
//            String[] commandArray=com.parseCommand();
//            commandVerb = commandArray[0];
//            commandNoun = "";
//            if (commandArray.length > 1)
//                commandNoun = commandArray[1];
//            new Command(commandVerb, commandNoun, player);
//        }
//    }
//}
