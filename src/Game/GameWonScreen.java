package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWonScreen extends JPanel {
    Game game;
    JPanel parent;
    public GameWonScreen(Game game,JPanel parent){
        this.parent = parent;
        GridLayout layout = new GridLayout(2,1);
        this.setLayout(layout);
        JLabel label = new JLabel(game.players.get(0).name+" won the game");
        label.setFont(new Font("Arial", Font.BOLD,24));
        this.add(label, BorderLayout.CENTER);
        JButton backButton = new JButton("Back to Main");
        backButton.setSize(new Dimension(50,50));
        backButton.addActionListener(new BackButtonListener());
        this.add(backButton,BorderLayout.SOUTH);
        this.game = game;

    }

    private class BackButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrame mf = new MainFrame(null);
            mf.switchMenus(parent,"main menu");
        }
    }
}
