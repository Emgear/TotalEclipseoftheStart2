// get clue

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totaleclipse.clues.Clue;
import com.totaleclipse.location.Location;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Item extends Clue {
    public Item(String clue, String NPC, String location) {
        super(clue, NPC, location);
    }

    public Item() {
        super();
    }

    @Override
    public void getClue() {
        HashMap<String, Clue> cluesMap=new HashMap<>();
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
        // to print clue
        for(var clue: cluesMap.keySet()){
            System.out.println(cluesMap.get(clue));
        }
        System.out.println(cluesMap.get("Bunker"));
    }
}
