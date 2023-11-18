import java.util.*;

public  class Player {
    String name;
    ArrayList<Tile> owned_tiles = new ArrayList<Tile>();
    Tile captial = null;
    int balance = 0;
    public Player(String name){
        this.name = name;
    }
    public void set_capital (Tile captial){
        this.captial = captial;
    }
    public void add_to_tiles(Tile new_tile){
        owned_tiles.add(new_tile);
        new_tile.owner = this;
    }

}
