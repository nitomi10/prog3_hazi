package Game;

import java.io.Serializable;

public class Farm extends Building implements Serializable {
    final static int defensivePower = 0;
    final static int startingPrice = 12;
    final static String imgPath = "imgs\\Farm.png";
    public Farm(Game game,Player owner, Tile tile) throws NotEnoughMoneyError {
        super(game,owner, tile,defensivePower,startingPrice,imgPath);
        int price = startingPrice;
        for (Tile t : owner.owned_tiles){
            if(t.on_tile instanceof Farm){
                price += 2;
            }
        }
        super.price = price;
    }
}
