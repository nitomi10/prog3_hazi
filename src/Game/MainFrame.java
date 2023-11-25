package Game;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainFrame extends JFrame {
    CardLayout cl = new CardLayout();
    JPanel menus;
    JPanel mainMenu;
    JPanel newGameMenu;
    JPanel endgamescreen;

    GamePanel2 gamePanel2;
    Game game;
    public MainFrame(Game game){
        if(game != null) {
            this.game = game;
        }
        menus  = new JPanel();
        menus.setLayout(cl);
        mainMenu = new MainMenu(menus,game,this);
        menus.add(mainMenu,"main menu");
        newGameMenu = new NewGameMenu(menus,game,this);
        menus.add(newGameMenu,"new game");
        if (game != null) {
            gamePanel2 = new GamePanel2(game,menus);
            menus.add(gamePanel2.GamePanel2, "game panel");
            endgamescreen = new GameWonScreen(game,menus);
            menus.add(endgamescreen,"endgame");
        }

        this.setSize(new Dimension(800,800));
        this.add(menus);
    }

    public void switchMenus(Container container, String panelName) {
        CardLayout cl = (CardLayout) (container.getLayout());
        cl.show(container, panelName);
    }
    public void addGame (Game game){
        this.game = game;
        gamePanel2 = new GamePanel2(game,menus);
        menus.add(gamePanel2.GamePanel2, "game panel");
        endgamescreen = new GameWonScreen(game,menus);
        menus.add(endgamescreen,"endgame");
    }

    public void saveGame(){
        try {
            if (game != null) {
                FileOutputStream outputStream = new FileOutputStream("save.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(game);
            }
        }catch (java.io.IOException exception){
            exception.printStackTrace();
        }
    }

    public void load(){
        try {
            FileInputStream inputStream = new FileInputStream("save.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            game = (Game) objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        if (game != null){
            gamePanel2 = new GamePanel2(game,menus);
            menus.add(gamePanel2.GamePanel2, "game panel");
            endgamescreen = new GameWonScreen(game,menus);
            menus.add(endgamescreen,"endgame");
        }
    }
}
