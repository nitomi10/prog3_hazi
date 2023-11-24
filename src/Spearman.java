import java.util.ArrayList;

public class Spearman extends Unit {
    public static final int strength = 2;
    public static final int salary = 6;
    public static final int price = 20;

    public static final String imgPath = "imgs\\Spearman.png";
    public Spearman(Game game,Player owner, Tile tile) throws NotEnoughMoneyError {
        super(game,owner, tile, salary, strength, price,imgPath);
    }

    public void upgrade(){
        owner.balance+=30;
        try {
            new Baron(game,owner,ItsTile);
        } catch (NotEnoughMoneyError e) {
            throw new RuntimeException(e);
        }
    }
}
