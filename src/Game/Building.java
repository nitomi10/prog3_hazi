package Game;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Building extends GameObject implements Serializable {
    int defensivePower;
    final static int defensiveRange = 1;
    int price;
    public Building(Game game,Player owner, Tile tile, int defensivePower, int price ,String imgPath) throws NotEnoughMoneyError{
        super(game,owner, tile,imgPath);
        this.defensivePower = defensivePower;
        this.price = price;
        if (owner.balance < price){
            throw new NotEnoughMoneyError();
        }else{
            owner.balance -= price;
            tile.on_tile = this;
        }
        ArrayList<Tile> nextTo = game.find_neighbours_in_map(tile);
        applyDefense();
    }

    public void applyDefense(){
        ArrayList<Tile> nextTo = game.find_neighbours_in_map(ItsTile);
        for (Tile t : nextTo){
            if (t.owner != null){
                if (t.owner.equals(owner)){
                    if (t.defensiveValue < defensivePower){
                        t.defensiveValue = defensivePower;
                    }
                }
            }
        }
    }


}
