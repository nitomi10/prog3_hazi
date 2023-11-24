import java.awt.*;
import java.util.*;

public  class Player {
    String name;
    ArrayList<Tile> owned_tiles = new ArrayList<Tile>();
    ArrayList<Unit> army = new ArrayList<>();
    Tile capital = null;
    int balance;
    Color color;
    boolean hasTurn;
    public Player(String name){
        this.name = name;
        balance = 10;
        hasTurn = false;
        Random random = new Random();
        float hue = random.nextFloat();
        float saturation = (random.nextInt(2000) + 1000) / 10000f;
        float luminance = 0.9f;
        this.color = Color.getHSBColor(hue, saturation, luminance);
    }

    public void startNewTurn(){
        for (Unit unit : army){
            unit.canMove = true;
        }
        int income = calculateIncome();
        if (balance == 0 && income <0){
            army.clear();
            for (Tile t : owned_tiles){
                if (t.on_tile != null){
                    if (t.on_tile instanceof Unit){
                        t.on_tile = null;
                    }
                }
            }
        }
        if (balance + income < 0){
            balance = 0;
        }else{
            balance = balance + income;
        }
        hasTurn = true;
    }
    public void endTurn(){

        hasTurn = false;
    }
    public int calculateIncome(){
        int income = 0;
        for(Tile tile : owned_tiles){
            if (tile.on_tile instanceof Farm){
                income = income + 4;
            }
            income = income +1;
        }
        for (Unit unit : army){
            income -= unit.salary;
        }
        return income;
    }
    public void set_capital (Tile captial){
        this.capital = captial;
    }
    public void add_to_tiles(Tile new_tile){
        owned_tiles.add(new_tile);
        new_tile.owner = this;
    }
    /*public boolean buyUnit(Unit unit,Tile tile){
        if (tile.on_tile != null){
            return false;
        }else{
            boolean canBuy = unit.buyUnit();
            if (canBuy){
                tile.on_tile = unit;
                return true;
            }else{
                return false;
            }
        }
    }*/
}
