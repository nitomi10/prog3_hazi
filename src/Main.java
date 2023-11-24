import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> test_names = new ArrayList<String>();
        test_names.add("Bob");
        test_names.add("Alice");
        Game game_1 = new Game("small",test_names);
        try {
            new Peasant(game_1,game_1.current_turn,game_1.current_turn.owned_tiles.get(1));
        }catch (NotEnoughMoneyError error){}
        //game_1.current_turn.army.get(0).available_tiles();
        //game_1.current_turn.army.get(0).move(game_1.find_neighbours_in_map(game_1.current_turn.owned_tiles.get(0)).get(0));
        //game_1.nextPlayersTurn();
        //game_1.nextPlayersTurn();
        /*JFrame testframe = new JFrame();
        HexGrid testpanel = new HexGrid(game_1);
        testframe.setSize(new Dimension(600,600));
        testframe.add(testpanel,BorderLayout.CENTER);

        testframe.setVisible(true);*/
        MainFrame mainMenu= new MainFrame(game_1);
        mainMenu.addGame(game_1);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setVisible(true);

    }
}