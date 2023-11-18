import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    JButton newGameButton;
    JButton continueGameButton;
    JPanel parent;
    public MainMenu(JPanel parent){
        this.parent = parent;
        JPanel[][] panels = new JPanel[3][3];
        for (int i = 0; i<3;i++) {
            for (int j = 0; j < 3; j++) {
                panels[i][j] = new JPanel();
                this.add(panels[i][j]);
            }
        }
        this.setLayout(new GridLayout(3,3));
        continueGameButton = new JButton("Continue Game");
        continueGameButton.addActionListener(new ContinueGameListener());
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new NewGameListener());
        JLabel text = new JLabel("Slay");
        panels[1][1].setLayout(new GridLayout(3,1));
        JPanel[] contents = new JPanel[3];
        for (int i = 0; i < 3; i++) {
            contents[i] = new JPanel();
            panels[1][1].add(contents[i]);
        }
        contents[0].add(text,BorderLayout.CENTER);
        contents[1].add(continueGameButton,BorderLayout.CENTER);
        contents[2].add(newGameButton,BorderLayout.CENTER);
        this.setSize(new Dimension(700,500));
    }
    private class ContinueGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private class NewGameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrame mf = new MainFrame();
            mf.switchMenus(parent,"new game");
        }
    }
}
