
public class Hexagon  {

    int q;
    int r;
    int s;

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

    Hexagon hex_add(Hexagon a , Hexagon b){
        return  new Hexagon(a.q+ b.q,a.r+b.r,a.s + b.s);
    }

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


}
