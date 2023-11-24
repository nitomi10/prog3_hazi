import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.geom.*;
import java.util.HashMap;

public class HexGrid extends JPanel {
    Tile[][] map;
    Game game;
    ArrayList<Shape> cells = new ArrayList<>();
    HashMap<Shape,Tile> tiles = new HashMap<>();
    Unit selectedUnit;

    GamePanel2 gamepanel;
    Shape clicked;
    public HexGrid(Game game,GamePanel2 gamepanel){
        this.gamepanel =gamepanel;
        this.game = game;
        this.map = game.map;
        this.addMouseListener(new MyMouseListener());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        gamepanel.updateLabels();
        if (game.won != null){
            gamepanel.endGame();
        }
        Graphics2D g2d = (Graphics2D) g.create();
        drawGameState(g2d);
        g2d.dispose();
        game.updateDefenses();
    }

    public void drawGameState(Graphics2D g2d){
        for(Shape cell : cells){
            g2d.setStroke(new BasicStroke(1));

            if (cell.equals(clicked)){
                g2d.setStroke(new BasicStroke(2));
            }
            if (tiles.get(cell).owner != null) {
                g2d.setColor(tiles.get(cell).owner.color);
                g2d.fill(cell);
                g2d.setColor(Color.black);
            }
            if (tiles.get(cell).on_tile != null){
                BufferedImage img = new BufferedImage(cell.getBounds().width,cell.getBounds().height,BufferedImage.TYPE_INT_ARGB);
                ImageIcon icon = new ImageIcon(new ImageIcon(tiles.get(cell).on_tile.imgPath).getImage().getScaledInstance(cell.getBounds().width,cell.getBounds().height,Image.SCALE_DEFAULT));
                Graphics2D gdd = img.createGraphics();
                icon.paintIcon(null,gdd,0,0);
                gdd.dispose();
                if (img != null){
                    g2d.drawImage(img,cell.getBounds().x,cell.getBounds().y,null);
                }
            }
            if (selectedUnit != null && selectedUnit.canMove){
                HighLightRange(g2d);
            }
            g2d.draw(cell);
        }
    }

    public void HighLightRange(Graphics2D g2d){
        if (selectedUnit != null){
            g2d.setColor(new Color(247, 30, 30));
           ArrayList<Tile> available = selectedUnit.available_tiles();
           for(Tile t : available){
               for (Shape cell :tiles.keySet()){
                   if (tiles.get(cell).equals(t)){
                       g2d.draw(cell);
                   }
               }
           }
           g2d.setColor(Color.black);
        }
    }
    @Override
    public Dimension getPreferredSize() {
        Dimension d = getParent().getParent().getSize();
        return new Dimension(d.width, d.height-d.height/8);
    }
    @Override
    public void invalidate() {
        super.invalidate();
        updateGrid();
    }
    public void updateGrid() {
        GeneralPath path = new GeneralPath();

        double rowHeight = ((getHeight()) / map.length);
        double colWidth = (getWidth() * 1.14f) / map[0].length;

        double size = Math.min(rowHeight, colWidth) / 2d;

        double centerX = size / 2d;
        double centerY = size / 2d;

        double height   = Math.sqrt(3d) * size;
        double width  = size * 2;
        for (float i = 0; i < 6; i++) {
            float angleDegrees = (60f * i);
            float angleRad = ((float) Math.PI / 180.0f) * angleDegrees;

            double x = centerX + (size * Math.cos(angleRad));
            double y = centerY + (size * Math.sin(angleRad));

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.closePath();

        cells.clear();
        tiles.clear();
        double yPos = size / 2d;
        for (int row = 0; row < map.length; row++) {
            int colCount = map[row].length;

            double xPos = 0;
            for (int col = 0; col < colCount; col++) {
                double offset = height/2;
                if (col % 2 == 0) {
                    offset = 0;
                }
                AffineTransform at = AffineTransform.getTranslateInstance(xPos + (size * 0.38), yPos + offset);
                Area area = new Area(path);
                area = area.createTransformedArea(at);
                cells.add(area);
                tiles.put(area,map[row][col]);
                xPos += (3/2)*size*1.5;
            }
            yPos += height;
        }
    }
    private class MyMouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){

            for (Shape cell : cells){
                if(cell.contains(e.getPoint())){
                    clicked = cell;
                    Tile currentTile = tiles.get(clicked);
                    GameObject onTile = currentTile.on_tile;

                    if(onTile != null){
                        if (selectedUnit == null) {
                            if (onTile instanceof Unit) {
                                selectedUnit = (Unit) onTile;
                            }
                        }else{
                            selectedUnit.move(currentTile);
                        }
                    }else{
                        if (selectedUnit !=null){
                            if (selectedUnit.available_tiles().contains(currentTile)){
                                selectedUnit.move(currentTile);
                            }else{
                                selectedUnit = null;
                            }
                        }else{
                            selectedUnit = null;
                        }
                    }
                    break;
                }
            }

            repaint();
        }

    }
}

