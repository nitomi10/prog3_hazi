package Game;

import java.io.Serializable;

public class Castle extends Building implements Serializable {
    final static int defensivePower = 2;
    final static int price = 0;
    final static String imgPath = "imgs\\Castle.png";
    public Castle(Game game, Player owner, Tile tile) throws NotEnoughMoneyError {
        super(game, owner, tile, defensivePower, price, imgPath);

    }
}
