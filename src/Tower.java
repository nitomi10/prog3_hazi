public class Tower extends Building{
    final static int defensivePower = 2;
    final static int price = 15;
    final static String imgPath = "imgs\\Tower.png";
    public Tower(Game game,Player owner, Tile tile) throws NotEnoughMoneyError {
        super(game,owner, tile,defensivePower,price,imgPath);
    }
}

