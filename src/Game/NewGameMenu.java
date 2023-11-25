package Game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

public class NewGameMenu extends JPanel
{
    static int minPlayers = 2;
    static int maxPlayers = 4;
    int playernum = 2;
    static String mapsize = "small";
    JToggleButton SmallMapButton;
    JToggleButton MediumMapButton;
    JToggleButton LargeMapButton;
    JSlider slider;
    JPanel parent;
    Game game;
    MainFrame mainFrame;
    public NewGameMenu(JPanel parent,Game game,MainFrame mainFrame){
        this.mainFrame = mainFrame;
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
        slider.addChangeListener(new SliderListener());
        JLabel label = new JLabel("Select the number of players: ");
        playerNum.add(label);
        playerNum.add(slider);
        JPanel mapsize = new JPanel();
        mapsize.setLayout(new FlowLayout());
        SmallMapButton = new JToggleButton("Small");
        SmallMapButton.addActionListener(new SmallMapButtonListener());
        SmallMapButton.setSelected(true);
        MediumMapButton = new JToggleButton("Medium");
        MediumMapButton.addActionListener(new MediumMapButtonListener());
        LargeMapButton = new JToggleButton("Large");
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

    private class SliderListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
           JSlider input = (JSlider)e.getSource();
           playernum = input.getValue();
        }
    }

    private class SmallMapButtonListener implements ActionListener{
        String size ="small";
        @Override
        public void actionPerformed(ActionEvent e) {
            MediumMapButton.setSelected(false);
            LargeMapButton.setSelected(false);
            mapsize = size;
        }
    }
    private class MediumMapButtonListener implements ActionListener{
        String size ="medium";
        @Override
        public void actionPerformed(ActionEvent e) {
            SmallMapButton.setSelected(false);
            LargeMapButton.setSelected(false);
            mapsize = size;
        }
    }
    private class LargeMapButtonListener implements ActionListener{
        String size ="large";
        @Override
        public void actionPerformed(ActionEvent e) {

            MediumMapButton.setSelected(false);
            SmallMapButton.setSelected(false);

            mapsize = size;
        }
    }
    private class StartGameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> names = new ArrayList<>();
            for (int i = 0; i<playernum;i++){
                names.add(new String("Player "+(i+1)));
            }
            game = new Game(mapsize,names);
            MainFrame mf = new MainFrame(game);
            mainFrame.addGame(game);
            mainFrame.saveGame();
            mainFrame.switchMenus(parent,"game panel");
        }
    }
    private class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrame mf = new MainFrame(null);
            mf.switchMenus(parent,"main menu");
        }
    }
}
