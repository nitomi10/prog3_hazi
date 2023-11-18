import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

public class NewGameMenu extends JPanel
{
    static int minPlayers = 2;
    static int maxPlayers = 4;
    static int mapsize = 1;
    JSlider slider;
    JPanel parent;
    Game game;
    public NewGameMenu(JPanel parent,Game game){
        this.parent = parent;
        this.game = game;
        JPanel mainpanel = new JPanel();
        GridLayout layout = new GridLayout(3,1);
        mainpanel.setLayout(layout);
        JPanel playerNum = new JPanel();
        playerNum.setLayout(new BoxLayout(playerNum,BoxLayout.Y_AXIS));
        slider = new JSlider(minPlayers,maxPlayers,minPlayers);
        slider.setPaintLabels(true);
        Hashtable labelTable = new Hashtable<Integer, Component>();
        for (int i = minPlayers; i <= maxPlayers;i++){
            labelTable.put(i,new JLabel(Integer.toString(i)));
        }
        slider.setLabelTable(labelTable);
        JLabel label = new JLabel("Select the number of players: ");
        playerNum.add(label);
        playerNum.add(slider);
        JPanel mapsize = new JPanel();
        mapsize.setLayout(new FlowLayout());
        JButton SmallMapButton = new JButton("Small");
        SmallMapButton.addActionListener(new SmallMapButtonListener());
        JButton MediumMapButton = new JButton("Medium");
        MediumMapButton.addActionListener(new MediumMapButtonListener());
        JButton LargeMapButton = new JButton("Large");
        LargeMapButton.addActionListener(new LargeMapButtonListener());
        mapsize.add(SmallMapButton);
        mapsize.add(MediumMapButton);
        mapsize.add(LargeMapButton);
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        JButton startGame = new JButton("Start Game");
        startGame.addActionListener(new StartGameListener());
        JButton back = new JButton("Back");
        back.addActionListener(new BackListener());
        buttons.add(back);
        buttons.add(startGame);
        mainpanel.add(playerNum);
        mainpanel.add(mapsize);
        mainpanel.add(buttons);
        this.add(mainpanel);

    }

    private class SmallMapButtonListener implements ActionListener{
        int size = 10;
        @Override
        public void actionPerformed(ActionEvent e) {
            mapsize = size;
        }
    }
    private class MediumMapButtonListener implements ActionListener{
        int size = 20;
        @Override
        public void actionPerformed(ActionEvent e) {
            mapsize = 2;
        }
    }
    private class LargeMapButtonListener implements ActionListener{
        int size = 30;
        @Override
        public void actionPerformed(ActionEvent e) {
            mapsize = 3;
        }
    }
    private class StartGameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> names = new ArrayList<>();
            for (int i = 0; i<slider.getValue();i++){
                names.add(new String("Player "+(i+1)));
            }
            game = new Game(mapsize*10,names);
            MainFrame mf = new MainFrame();
            mf.switchMenus(parent,"game panel");
        }
    }
    private class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrame mf = new MainFrame();
            mf.switchMenus(parent,"main menu");
        }
    }
}
