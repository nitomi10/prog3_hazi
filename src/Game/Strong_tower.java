package Game;

import java.io.Serializable;

public class Strong_tower extends Building implements Serializable {
    final static int defensivePower = 3;
    final static int price = 35;
    final static String imgPath = "imgs\\Strong_tower.png";
    public Strong_tower(Game game,Player owner, Tile tile) throws NotEnoughMoneyError {
        super(game,owner, tile,defensivePower,price,imgPath);
    }
}

