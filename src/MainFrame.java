import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    CardLayout cl = new CardLayout();
    JPanel menus;
    JPanel mainMenu;
    JPanel newGameMenu;
    JPanel endgamescreen;
    HexGrid gamePanel;

    GamePanel2 gamePanel2;
    Game game;
    public MainFrame(Game game){
        if(game != null) {
            this.game = game;
        }
        menus  = new JPanel();
        menus.setLayout(cl);
        mainMenu = new MainMenu(menus,game);
        menus.add(mainMenu,"main menu");
        newGameMenu = new NewGameMenu(menus,game);
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

    }
}
