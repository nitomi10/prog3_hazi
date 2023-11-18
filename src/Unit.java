import java.util.ArrayList;

public abstract class Unit extends GameObject {
    Player owner;
    public abstract void move();

    //public abstract ArrayList<Tile> available_tiles();
}
