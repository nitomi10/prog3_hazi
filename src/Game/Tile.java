package Game;

import java.io.Serializable;
import java.util.ArrayList;

public class Tile implements Serializable {
    public Hexagon hex;
    public Player owner = null;
    public GameObject on_tile = null;
    Game game;
    public int defensiveValue;
    public Tile (Game game,Hexagon hex){
        this.game = game;
        this.hex = hex;
        defensiveValue = 0;
    }

    public void updateDefensiveValue(){
        ArrayList<Tile> nextTo = game.find_neighbours_in_map(this);
        int maxValue = 0;
        if (owner == null){
            this.defensiveValue = 0;
            return;
        }
        for(Tile t : nextTo){
            if (t.owner != null){
                if (t.owner.equals(owner)){
                    if (t.on_tile instanceof Building){
                        if (((Building) t.on_tile).defensivePower > maxValue)
                            maxValue = ((Building) t.on_tile).defensivePower;
                    }
                }
            }
        }
        if (on_tile != null) {
            if (on_tile instanceof Unit) {
                if (on_tile instanceof Knight) {
                    maxValue = 3;
                } else {
                    if (((Unit) on_tile).strength > maxValue) {
                        maxValue = ((Unit) on_tile).strength;
                    }
                }
            }
            if (on_tile instanceof Building){
                if (((Building)on_tile).defensivePower > maxValue){
                    maxValue = ((Building)on_tile).defensivePower;
                }
            }
        }

        this.defensiveValue = maxValue;
    }
}
