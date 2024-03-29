package Game;

import java.io.Serializable;

public class Knight extends Unit implements Serializable {
    public static final int strength = 4;
    public static final int salary = 36;
    public static final int price = 40;

    public static final String imgPath = "imgs\\Knight.png";
    public Knight(Game game,Player owner, Tile tile) throws NotEnoughMoneyError {
        super(game,owner, tile, salary, strength, price,imgPath);
    }
}
