public class Tile {
    public Hexagon hex;
    public Player owner = null;
    GameObject on_tile = null;
    public Tile (Hexagon hex){
        this.hex = hex;
    }
}
