import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> test_names = new ArrayList<String>();
        test_names.add("Bob");
        test_names.add("Alice");
        Game game_1 = new Game(30,test_names);
        MainFrame mainMenu= new MainFrame();
        mainMenu.addGame(game_1);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setVisible(true);

    }
}