import javax.swing.*;
import java.awt.*;

public class HexButton extends JButton {
    Tile tile;
    int x;
    int y;

    int size = 35;
    public HexButton(Tile tile,int startingX,int startingY){
        this.tile = tile;
        if (tile.hex.q == 0 && tile.hex.r == 0){
            this.x = startingX;
            this.y = startingY;
        }else{
            this.x =(size * (3/2) * tile.hex.q) +startingX;
            this.y =(int)(size * ((Math.sqrt(3)/2) * tile.hex.q+Math.sqrt(3) * tile.hex.r))+startingY;
        }

        setContentAreaFilled(false);
        setFocusPainted(true);
        setBorderPainted(false);
        setBounds(x,y,size*2,size*2+10);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon hex = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle_deg = 60 * i;
            double angle_rad = 3.14 / 180 * angle_deg;
            double corner_x = x + size * Math.cos(angle_rad);
            double corner_y = y + size * Math.sin(angle_rad);
            hex.addPoint((int)corner_x,(int)corner_y);
        }
        g.drawPolygon(hex);
        g.setColor(new Color(0,255,0));
        g.fillPolygon(hex);
    }
}
