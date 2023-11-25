package Game;

import java.io.Serializable;

public class Peasant extends Unit implements Serializable {
    public static final int strength = 1;
    public static final int salary = 2;
    public static final int price = 10;

    public static final String imgPath = "imgs\\Peasant.png";
    public Peasant(Game game,Player owner, Tile tile) throws NotEnoughMoneyError {
        super(game,owner, tile, salary, strength, price,imgPath);
    }

    public void upgrade(){
        owner.balance+=20;
        try {
            new Spearman(game,owner,ItsTile);
        } catch (NotEnoughMoneyError e) {
            throw new RuntimeException(e);
        }
    }
}
