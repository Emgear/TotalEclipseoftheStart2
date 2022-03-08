import com.totaleclipse.commands.Commands;
import com.totaleclipse.commands.parseCommands;

public class testCommands {
    public static void main(String[] args) {
        parseCommands com=new parseCommands();
        System.out.println("Enter your command");
        com.parseCommand();
    }
}
