import java.util.ArrayList;

public class Journal {
    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    String clue;
    //won't be main method, this is just for testing
    public static void main(String[] args){
        ArrayList<String> clues = new ArrayList<String>();
        // instance variable string clue
        clues.add("go to the library!");
        clues.add("storm area 51!");
        clues.add("moooo!");
        System.out.println(clues);
    }
}
