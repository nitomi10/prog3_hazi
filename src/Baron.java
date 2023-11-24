import java.util.ArrayList;

public class Baron extends Unit{
    public static final int strength = 3;
    public static final int salary = 18;
    public static final int price = 30;

    public static final String imgPath = "imgs\\Baron.png";
    public Baron(Game game,Player owner, Tile tile) throws NotEnoughMoneyError {
        super(game,owner, tile, salary, strength, price,imgPath);
    }

    public void upgrade(){
        owner.balance+=40;
        try {
            new Knight(game,owner,ItsTile);
        } catch (NotEnoughMoneyError e) {
            throw new RuntimeException(e);
        }
    }
}
