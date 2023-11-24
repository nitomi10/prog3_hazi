import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel2 extends JPanel {
    private JButton PeasantB;
    private JButton SpearmanB;
    private JButton BaronB;
    private JButton KnightB;
    private JButton FarmB;
    private JButton TowerB;
    private JButton Strong_towerB;
    public JPanel GamePanel2;
    private JPanel GameField;
    private JLabel PeasantL;
    private JLabel SpearmanL;
    private JLabel BaronL;
    private JLabel KnightL;
    private JLabel FarmL;
    private JLabel TowerL;
    private JLabel Strong_towerL;
    private JButton endTurnButton;
    private JLabel IncomeL;
    private JLabel BalanceL;
    private JLabel PlayerNameL;
    private HexGrid grid;
    private Game game;
    JPanel parent;

    public GamePanel2(Game game, JPanel parent) {
        this.parent = parent;
        this.game = game;
        grid = new HexGrid(game, this);
        GameField.add(grid);
        buttonInit();
        endTurnButton.addActionListener(new EndTurnButtonListener(game));
        updateLabels();
    }

    public void updateLabels() {

        if (game.current_turn.calculateIncome() > 0) {
            IncomeL.setText("+" + Integer.toString(game.current_turn.calculateIncome()));
        } else {
            IncomeL.setText(Integer.toString(game.current_turn.calculateIncome()));
        }
        BalanceL.setText(Integer.toString(game.current_turn.balance));
        PeasantL.setText("10");
        if (game.current_turn.balance < 10) {
            PeasantL.setForeground(Color.RED);
        } else {
            PeasantL.setForeground(Color.GREEN);
        }
        SpearmanL.setText("20");
        if (game.current_turn.balance < 20) {
            SpearmanL.setForeground(Color.RED);
        } else {
            SpearmanL.setForeground(Color.GREEN);
        }
        BaronL.setText("30");
        if (game.current_turn.balance < 30) {
            BaronL.setForeground(Color.RED);
        } else {
            BaronL.setForeground(Color.GREEN);
        }
        KnightL.setText("40");
        if (game.current_turn.balance < 40) {
            KnightL.setForeground(Color.RED);
        } else {
            KnightL.setForeground(Color.GREEN);
        }

        int price = 12;
        for (Tile t : game.current_turn.owned_tiles) {
            if (t.on_tile instanceof Farm) {
                price += 2;
            }
        }
        FarmL.setText(Integer.toString(price));
        if (game.current_turn.balance < price) {
            FarmL.setForeground(Color.RED);
        } else {
            FarmL.setForeground(Color.GREEN);
        }

        TowerL.setText("15");
        if (game.current_turn.balance < 15) {
            TowerL.setForeground(Color.RED);
        } else {
            TowerL.setForeground(Color.GREEN);
        }
        Strong_towerL.setText("35");
        if (game.current_turn.balance < 35) {
            Strong_towerL.setForeground(Color.RED);
        } else {
            Strong_towerL.setForeground(Color.GREEN);
        }

        PlayerNameL.setText(game.current_turn.name + "'s Turn");
        PlayerNameL.setForeground(game.current_turn.color.darker());
    }

    public void buttonInit() {
        PeasantB.setIcon(new ImageIcon(new ImageIcon("imgs\\Peasant.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        PeasantB.setOpaque(false);
        PeasantB.setContentAreaFilled(false);
        PeasantB.setBorderPainted(false);
        PeasantB.addActionListener(new BuyPeasantListener());

        SpearmanB.setIcon(new ImageIcon(new ImageIcon("imgs\\Spearman.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        SpearmanB.setOpaque(false);
        SpearmanB.setContentAreaFilled(false);
        SpearmanB.setBorderPainted(false);
        SpearmanB.addActionListener(new BuySpearmanListener());

        BaronB.setIcon(new ImageIcon(new ImageIcon("imgs\\Baron.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        BaronB.setOpaque(false);
        BaronB.setContentAreaFilled(false);
        BaronB.setBorderPainted(false);
        BaronB.addActionListener(new BuyBaronListener());

        KnightB.setIcon(new ImageIcon(new ImageIcon("imgs\\Knight.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        KnightB.setOpaque(false);
        KnightB.setContentAreaFilled(false);
        KnightB.setBorderPainted(false);
        KnightB.addActionListener(new BuyKnightListener());

        FarmB.setIcon(new ImageIcon(new ImageIcon("imgs\\Farm.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        FarmB.setOpaque(false);
        FarmB.setContentAreaFilled(false);
        FarmB.setBorderPainted(false);
        int price = 12;
        for (Tile t : game.current_turn.owned_tiles) {
            if (t.on_tile != null) {
                if (t.on_tile instanceof Farm) {
                    price += 2;
                }
            }
        }
        FarmB.addActionListener(new BuyFarmListener(price));

        TowerB.setIcon(new ImageIcon(new ImageIcon("imgs\\Tower.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        TowerB.setOpaque(false);
        TowerB.setContentAreaFilled(false);
        TowerB.setBorderPainted(false);
        TowerB.addActionListener(new BuyTowerListener());

        Strong_towerB.setIcon(new ImageIcon(new ImageIcon("imgs\\Strong_tower.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        Strong_towerB.setOpaque(false);
        Strong_towerB.setContentAreaFilled(false);
        Strong_towerB.setBorderPainted(false);
        Strong_towerB.addActionListener(new BuyStrong_towerListener());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        GamePanel2 = new JPanel();
        GamePanel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        GamePanel2.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 7, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PeasantB = new JButton();
        PeasantB.setEnabled(true);
        PeasantB.setText("");
        panel2.add(PeasantB, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SpearmanB = new JButton();
        SpearmanB.setText("");
        panel2.add(SpearmanB, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BaronB = new JButton();
        BaronB.setText("");
        panel2.add(BaronB, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        KnightB = new JButton();
        KnightB.setText("");
        panel2.add(KnightB, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FarmB = new JButton();
        FarmB.setText("");
        panel2.add(FarmB, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TowerB = new JButton();
        TowerB.setText("");
        panel2.add(TowerB, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Strong_towerB = new JButton();
        Strong_towerB.setText("");
        panel2.add(Strong_towerB, new com.intellij.uiDesigner.core.GridConstraints(0, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 7, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PeasantL = new JLabel();
        PeasantL.setText("Label");
        panel3.add(PeasantL, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SpearmanL = new JLabel();
        SpearmanL.setText("Label");
        panel3.add(SpearmanL, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BaronL = new JLabel();
        BaronL.setText("Label");
        panel3.add(BaronL, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        KnightL = new JLabel();
        KnightL.setText("Label");
        panel3.add(KnightL, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FarmL = new JLabel();
        FarmL.setText("Label");
        panel3.add(FarmL, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TowerL = new JLabel();
        TowerL.setText("Label");
        panel3.add(TowerL, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Strong_towerL = new JLabel();
        Strong_towerL.setText("Label");
        panel3.add(Strong_towerL, new com.intellij.uiDesigner.core.GridConstraints(0, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        GamePanel2.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        BalanceL = new JLabel();
        BalanceL.setText("Label");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        panel4.add(BalanceL, gbc);
        IncomeL = new JLabel();
        IncomeL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        panel4.add(IncomeL, gbc);
        endTurnButton = new JButton();
        endTurnButton.setBackground(new Color(-15747025));
        endTurnButton.setForeground(new Color(-15263977));
        endTurnButton.setHorizontalAlignment(0);
        endTurnButton.setHorizontalTextPosition(0);
        endTurnButton.setText("End Turn");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        panel4.add(endTurnButton, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.weightx = 100.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        panel4.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 40;
        panel4.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        panel4.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 140;
        panel4.add(spacer4, gbc);
        PlayerNameL = new JLabel();
        PlayerNameL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel4.add(PlayerNameL, gbc);
        GameField = new JPanel();
        GameField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        GameField.setEnabled(true);
        GamePanel2.add(GameField, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        GamePanel2.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        GamePanel2.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return GamePanel2;
    }

    private class BuyPeasantListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buyUnit("Peasant", 1, 10);
        }
    }

    private class BuySpearmanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buyUnit("Spearman", 2, 20);
        }
    }

    private class BuyBaronListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buyUnit("Baron", 3, 30);
        }
    }

    private class BuyKnightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buyUnit("Knight", 4, 40);
        }
    }

    private class BuyFarmListener implements ActionListener {
        int price = 0;

        public BuyFarmListener(int price) {
            this.price = price;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            buyBuilding("Farm", price);
        }
    }

    private class BuyTowerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buyBuilding("Tower", 15);
        }
    }

    private class BuyStrong_towerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buyBuilding("Strong_tower", 35);
        }
    }

    public void buyUnit(String unitName, int power, int price) {
        Tile buyTo = (grid.tiles.get(grid.clicked));
        if (game.current_turn.balance >= price) {
            ArrayList<Tile> tilelist = game.find_neighbours_in_map(buyTo);

            if (buyTo.owner != null && buyTo.owner.equals(game.current_turn)) {
                switch (unitName) {
                    case "Peasant" -> {
                        if (buyTo.on_tile == null) {
                            try {
                                new Peasant(game, game.current_turn, buyTo);
                            } catch (NotEnoughMoneyError e) {
                            }
                        } else if (buyTo.on_tile instanceof Peasant) {
                            ((Peasant) buyTo.on_tile).upgrade();
                        }
                    }
                    case "Spearman" -> {
                        if (buyTo.on_tile == null) {
                            try {
                                new Spearman(game, game.current_turn, buyTo);
                            } catch (NotEnoughMoneyError e) {
                            }
                        } else if (buyTo.on_tile instanceof Spearman) {
                            ((Spearman) buyTo.on_tile).upgrade();
                        }
                    }
                    case "Baron" -> {
                        if (buyTo.on_tile == null) {
                            try {
                                new Baron(game, game.current_turn, buyTo);
                            } catch (NotEnoughMoneyError e) {
                            }
                        } else if (buyTo.on_tile instanceof Baron) {
                            ((Baron) buyTo.on_tile).upgrade();
                        }
                    }
                    case "Knight" -> {
                        try {
                            new Knight(game, game.current_turn, buyTo);
                        } catch (NotEnoughMoneyError e) {
                        }
                    }
                }
            } else {

                boolean nextTo = false;
                for (Tile t : tilelist) {
                    if (t.owner != null) {
                        if (t.owner.equals(game.current_turn)) {
                            nextTo = true;
                        }
                    }
                }
                if (nextTo) {
                    if (buyTo.owner == null) {
                        switch (unitName) {
                            case "Peasant" -> {
                                try {
                                    new Peasant(game, game.current_turn, buyTo);
                                } catch (NotEnoughMoneyError e) {
                                }
                            }
                            case "Spearman" -> {
                                try {
                                    new Spearman(game, game.current_turn, buyTo);
                                } catch (NotEnoughMoneyError e) {
                                }
                            }
                            case "Baron" -> {
                                try {
                                    new Baron(game, game.current_turn, buyTo);
                                } catch (NotEnoughMoneyError e) {
                                }
                            }
                            case "Knight" -> {
                                try {
                                    new Knight(game, game.current_turn, buyTo);
                                } catch (NotEnoughMoneyError e) {
                                }
                            }
                        }
                    } else if (buyTo.defensiveValue < power) {
                        switch (unitName) {
                            case "Peasant" -> {
                                try {
                                    new Peasant(game, game.current_turn, buyTo);
                                } catch (NotEnoughMoneyError e) {
                                }
                            }
                            case "Spearman" -> {
                                try {
                                    new Spearman(game, game.current_turn, buyTo);
                                } catch (NotEnoughMoneyError e) {
                                }
                            }
                            case "Baron" -> {
                                try {
                                    new Baron(game, game.current_turn, buyTo);
                                } catch (NotEnoughMoneyError e) {
                                }
                            }
                            case "Knight" -> {
                                try {
                                    new Knight(game, game.current_turn, buyTo);
                                } catch (NotEnoughMoneyError e) {
                                }
                            }
                        }
                    }
                }
            }
        }
        grid.repaint();
    }

    public void buyBuilding(String buildingName, int price) {
        Tile buyTo = grid.tiles.get(grid.clicked);
        if (buyTo.owner != null) {
            if (buyTo.owner == game.current_turn) {
                if (game.current_turn.balance >= price) {
                    switch (buildingName) {
                        case "Farm" -> {
                            try {
                                new Farm(game, game.current_turn, buyTo);
                            } catch (NotEnoughMoneyError e) {
                            }
                        }
                        case "Tower" -> {
                            try {
                                new Tower(game, game.current_turn, buyTo);
                            } catch (NotEnoughMoneyError e) {
                            }
                        }
                        case "Strong_tower" -> {
                            try {
                                new Strong_tower(game, game.current_turn, buyTo);
                            } catch (NotEnoughMoneyError e) {
                            }
                        }
                    }
                }
            }
        }
        grid.repaint();
    }

    public void endGame() {
        MainFrame mf = new MainFrame(null);
        mf.switchMenus(parent, "endgame");
    }

    private class EndTurnButtonListener implements ActionListener {
        Game game;

        public EndTurnButtonListener(Game game) {
            this.game = game;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            grid.clicked = null;
            grid.selectedUnit = null;
            game.nextPlayersTurn();
            updateLabels();
        }
    }

}
