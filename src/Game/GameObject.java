package Game;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    Player owner;
    public Tile ItsTile;
    Game game;
    String imgPath;
    public  GameObject(Game game,Player owner, Tile tile,String imgPath){
        this.owner = owner;
        this.ItsTile = tile;
        this.game = game;
        this.imgPath = imgPath;
    }


}
