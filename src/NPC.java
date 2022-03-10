////get clue
////extend clue abstract and implement those methods
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.totaleclipse.clues.Clue;
//import com.totaleclipse.location.Location;
//
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//// extends clue class
//public class NPC extends Clue {
//    public NPC(String clue, String NPC, String location) {
//        super(clue, NPC, location);
//    }
//
//    @Override
//    public String getClue() {
//        HashMap<Integer, Location> locationsMap=new HashMap<>();
//        try{
//            ObjectMapper mapper =new ObjectMapper();
//            List<Location> locations = Arrays.asList(mapper.readValue(Paths.get("Clues.json").toFile(), Location[].class));
//            for(Location location:locations){
//                locationsMap.put(location.getKey(), location);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return "";
//    }
//}
