import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    CardLayout cl = new CardLayout();
    JPanel menus;
    JPanel mainMenu;
    JPanel newGameMenu;
    JPanel gamePanel;
    Game game;
    public MainFrame(){
        menus  = new JPanel();
        menus.setLayout(cl);
        mainMenu = new MainMenu(menus);
        menus.add(mainMenu,"main menu");
        newGameMenu = new NewGameMenu(menus,game);
        menus.add(newGameMenu,"new game");
        gamePanel = new GamePanel(game);
        menus.add(gamePanel,"game panel");
        this.setSize(new Dimension(600,400));
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
