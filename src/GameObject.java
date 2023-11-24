public abstract class GameObject {
    Player owner;
    Tile ItsTile;
    Game game;
    String imgPath;
    public  GameObject(Game game,Player owner, Tile tile,String imgPath){
        this.owner = owner;
        this.ItsTile = tile;
        this.game = game;
        this.imgPath = imgPath;
    }


}
