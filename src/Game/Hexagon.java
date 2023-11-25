package Game;

import java.io.Serializable;

public class Hexagon implements Serializable {

    public int q;
    public int r;
    public int s;

    int size = 200;

    public Hexagon(int q,int r, int s){
        this.q = q;
        this.r = r;
        this.s = s;
    }

    public Hexagon(int q,int r){
        this.q = q;
        this.r = r;
        this.s = -q-r;
    }

    public Hexagon(int row,int col,boolean offset){
        this.q = col;
        this.r = row - (col-(col&1)) / 2;
        this.s = -q-r;
    }

    public Hexagon hex_add(Hexagon a , Hexagon b){
        return  new Hexagon(a.q+ b.q,a.r+b.r,a.s + b.s);
    }
    public Hexagon hex_sub(Hexagon a , Hexagon b) { return  new Hexagon((a.q - b.q),a.r-b.r,a.s-b.s);}

    private static final Hexagon[] HexDirectionVectors = {
            new Hexagon(1,0,-1),new Hexagon(1,-1,0),new Hexagon(0,-1,1),
            new Hexagon(-1,0,1),new Hexagon(-1,1,0),new Hexagon(0,1,-1)
    };

    public Hexagon hex_direction(int direction){
        return HexDirectionVectors[direction];
    }

    public Hexagon hex_neighbor(Hexagon hex, int direction){
        return  hex_add(hex,hex_direction(direction));
    }

    public double Hex_distance(Hexagon a, Hexagon b){
        Hexagon vec = hex_sub(a,b);
        return (Math.abs(vec.q)+Math.abs(vec.r)+Math.abs(vec.s))/2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        Hexagon h = (Hexagon) o;
        boolean eq = true;
        if (q != h.q ){
            eq = false;
        }
        if (r != h.r ){
            eq = false;
        }
        if (s != h.s ){
            eq = false;
        }
        return eq;
    }
}
