import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class Unit extends GameObject {
    int salary;
    int strength;
    int price;
    public static final int range = 5;

    boolean canMove;

    public Unit(Game game,Player owner, Tile tile,int salary,int strength, int price,String imgPath) throws NotEnoughMoneyError {
        super(game, owner, tile,imgPath);
        if (owner.balance < price){
            throw  new NotEnoughMoneyError();
        }else{
            owner.balance -= price;
            owner.army.add(this);
            if (tile.on_tile instanceof Castle){
                game.PlayerLost(tile.owner);
            }
        }
        tile.on_tile = this;
        tile.owner = owner;
        owner.owned_tiles.add(tile);
        this.salary = salary;
        this.strength = strength;
        this.price = price;
        this.canMove = false;
    }

    public boolean move(Tile moveTo){
        ArrayList<Tile> availableTiles = available_tiles();
        if (!canMove){
            return false;
        }
        if (!availableTiles.contains(moveTo)){
            return false;
        }else{
            if (moveTo.defensiveValue < strength || moveTo.owner.equals(owner)){
                if (moveTo.owner != null) {
                    if (moveTo.owner.equals(owner) && moveTo.on_tile instanceof Unit) {
                        if (this instanceof Peasant && moveTo.on_tile instanceof Peasant) {
                            ((Peasant) moveTo.on_tile).upgrade();
                            ((Spearman) moveTo.on_tile).canMove = true;
                            owner.army.remove(this);
                            ItsTile.on_tile = null;
                            return true;
                        }
                        if (this instanceof Peasant && !(moveTo.on_tile instanceof Peasant)) {
                            return false;
                        }
                        if (this instanceof Spearman && moveTo.on_tile instanceof Spearman) {
                            ((Spearman) moveTo.on_tile).upgrade();
                            ((Baron) moveTo.on_tile).canMove = true;
                            owner.army.remove(this);
                            ItsTile.on_tile = null;
                            return true;
                        }
                        if (this instanceof Spearman && !(moveTo.on_tile instanceof Spearman)) {
                            return false;
                        }
                        if (this instanceof Baron && moveTo.on_tile instanceof Baron) {
                            ((Baron) moveTo.on_tile).upgrade();
                            ((Knight) moveTo.on_tile).canMove = true;
                            owner.army.remove(this);
                            ItsTile.on_tile = null;
                            return true;
                        }
                        if (this instanceof Baron && !(moveTo.on_tile instanceof Baron)) {
                            return false;
                        }
                        if (this instanceof Knight) {
                            return false;
                        }

                    }
                }
                canMove = false;
                if (moveTo.owner != null){
                    if (!moveTo.owner.equals(owner)){
                        cutoffTest(moveTo);
                    }
                }
                moveTo.owner = owner;
                if (moveTo.on_tile instanceof Castle){
                    game.PlayerLost(moveTo.on_tile.owner);
                }
                moveTo.on_tile = this;
                ItsTile.on_tile = null;
                ItsTile = moveTo;
                if (ItsTile.owner != null){
                    ItsTile.owner.owned_tiles.remove(ItsTile);
                }
                ItsTile.updateDefensiveValue();
                owner.owned_tiles.add(ItsTile);
                game.updateDefenses();
                return true;

            }else{
                return  false;
            }
        }

    }

    public void cutoffTest(Tile tile){
        ArrayList<Tile> nextTo = game.find_neighbours_in_map(tile);
        ArrayList<Tile> remove = new ArrayList<>();
        for (int i = 0; i < nextTo.size();i++){
            if (nextTo.get(i).owner == null){
                remove.add(nextTo.get(i));
            }else if (!(nextTo.get(i).owner.equals(tile.owner))){
                remove.add(nextTo.get(i));
            }
        }
        nextTo.removeAll(remove);
        for (Tile t : nextTo){
            if (t.owner == null){
                continue;
            }
            ArrayList<Tile> canmoveTo = new ArrayList<>();
            canmoveTo.add(t);
            Tile moveTo = t;
            boolean found = false;
            int indx = 1;
            while (!(moveTo== null || found)){
                ArrayList<Tile>near = game.find_neighbours_in_map(moveTo);
                for (Tile t2 : near){
                    if (!t2.equals(tile)){
                        if (t2.owner != null){
                            if (t2.owner.equals(tile.owner)){
                                if (t2.on_tile !=null){
                                    if (t2.on_tile instanceof Castle){
                                        found = true;
                                    }
                                }
                                if (!canmoveTo.contains(t2)){
                                    canmoveTo.add(t2);
                                }
                            }
                        }

                    }
                }
                if (near.isEmpty()){
                    canmoveTo.add(moveTo);
                }

                if (indx < canmoveTo.size()){
                    moveTo = canmoveTo.get(indx);
                    indx++;
                }else{
                    moveTo = null;
                }
            }

            if (!found){
                for (Tile toRemove : canmoveTo){
                    if (toRemove.on_tile != null){
                        if (toRemove.on_tile instanceof Unit){
                            toRemove.owner.army.remove(toRemove.on_tile);
                        }
                        toRemove.on_tile = null;
                    }
                    toRemove.owner = null;
                }
                owner.owned_tiles.removeAll(canmoveTo);
            }
        }
    }

    //maybe works probably not
    public ArrayList<Tile> available_tiles(){
        ArrayList<Tile> available = new ArrayList<>();
        ArrayList<Tile> toVisit = new ArrayList<>();
        HashMap<Tile,Integer> viseted = new HashMap<>();
        Tile visit = ItsTile;
        Tile start = ItsTile;
        viseted.put(visit,0);

        boolean first = true;
        while (!toVisit.isEmpty() || visit != null || first) {
            first = false;
            if (viseted.get(visit) <= range) {
                for (Tile t : game.find_neighbours_in_map(visit)) {
                    if (t.owner != null) {
                        if (t.owner == owner) {
                            toVisit.add(t);
                            if (!t.equals(start)) {
                                available.add(t);
                            }
                        } else {
                            if (t.defensiveValue < strength) {
                                if (!available.contains(t)) {
                                    if (!t.equals(start)) {
                                        available.add(t);
                                    }
                                }
                            }
                        }
                    } else {
                        if (!available.contains(t)) {
                            if (!t.equals(start)) {
                                available.add(t);
                            }
                        }
                    }
                }
            }
            boolean found = false;
            while (!found) {
                if (toVisit.isEmpty()){
                    break;
                }
                if (viseted.containsKey(toVisit.get(0))) {
                    if (viseted.get(toVisit.get(0)) > viseted.get(visit) + 1) {
                        viseted.remove(toVisit.get(0));
                        viseted.put(toVisit.get(0), viseted.get(visit) + 1);
                        found = true;
                    }else{
                        toVisit.remove(0);
                    }
                } else {
                    viseted.put(toVisit.get(0), viseted.get(visit) + 1);
                    found = true;
                }

            }
            if (!toVisit.isEmpty()) {
                visit = toVisit.get(0);
                toVisit.remove(0);
            }else{
                visit = null;
            }
        }
        return available;
    }
}
