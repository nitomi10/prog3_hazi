package Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Serializable {
    private final Random rand = new Random();
    public Tile[][] map ;
    public ArrayList<Player> players = new ArrayList<Player>();
    public Player current_turn;
    public Player won;
    public Game(String size, ArrayList<String> player_names){
        gen_map(size);
        for(String name : player_names){
            players.add(new Player(name));
        }
        gen_starting_tiles();
        current_turn = players.get(0);
        current_turn.startNewTurn();
        won = null;
    }

    public void nextPlayersTurn (){
        players.remove(current_turn);
        Player next = players.get(0);
        current_turn.endTurn();
        players.add(players.size()-1,current_turn);
        current_turn = next;
        current_turn.startNewTurn();
    }

    private void gen_map(String size){
        //temp
        if (size.equals("medium")){
            map = new Tile[6][10];
        }else if( size.equals("large")){
            map = new Tile[9][12];
        }else{
            map = new Tile[6][6];
        }
        for (int i = 0; i < map.length ; i++){
            for (int j = 0; j < map[i].length; j++){
                map[i][j] = new Tile(this, new Hexagon(i,j,true));
            }
        }
    }

    public void PlayerLost(Player player){
        for (Tile t : player.owned_tiles){
            t.on_tile = null;
            t.owner = null;
        }
        players.remove(player);
        if (players.size()<= 1){
            won = players.get(0);
        }
    }

    public void updateDefenses(){
        for (int i = 0;i < map.length; i++){
            for (int j= 0; j < map[i].length;j++){
                map[i][j].updateDefensiveValue();
            }
        }
    }

    private void gen_starting_tiles(){
        //TO DO: prevent too close spawns
        for (Player player: players) {
            boolean found = false;
            while (!found){
                WeightedRandomList<Tile> weightedList = new WeightedRandomList<Tile>();
                initWeightedList(weightedList);
                Tile target = weightedList.getRandom();
                if (target.owner == null){
                    player.add_to_tiles(target);
                    player.capital = target;
                    try{new Castle(this,player,player.capital);}
                    catch (NotEnoughMoneyError e){}
                    found = true;

                    boolean gen_done = false;
                    while (!gen_done){
                        ArrayList<Tile> neighbours = find_neighbours_in_map(target);
                        neighbours.removeIf((x)->x.owner!=null);
                        if (neighbours.size() >=2){
                            int add_indx = rand.nextInt(neighbours.size()-1);
                            player.add_to_tiles(neighbours.get(add_indx));
                            neighbours.remove(add_indx);
                            if (neighbours.size() > 1){
                                player.add_to_tiles(neighbours.get(rand.nextInt(neighbours.size()-1)));
                            }else{
                                player.add_to_tiles(neighbours.get(0));
                            }
                            gen_done = true;
                        }else if (neighbours.size() == 1){
                            Tile add_tile = neighbours.get(0);
                            player.add_to_tiles(add_tile);
                            target = add_tile;
                        }else{
                            System.out.println("no space for starting tiles gen");
                            gen_done = true;
                        }
                    }
                }
            }
        }
    }

    public void initWeightedList(WeightedRandomList list){
        for (int i = 0; i < map.length;i++){
            for (int j=0;j<map[i].length;j++){
                list.addEntry(map[i][j],calcWeight(map[i][j]));
            }
        }
    }

    public double calcWeight(Tile tile){
        double weight = 1;
        for (Player p : players){
            if (p.capital != null){
                weight+= 10*tile.hex.Hex_distance(tile.hex,p.capital.hex);
            }
        }
       return weight;
    }

    public ArrayList<Tile> find_neighbours_in_map(Tile starting_point){
        ArrayList<Tile> neighbours = new ArrayList<Tile>();
        if (starting_point != null) {
            for (int i = 0; i < 6; i++) {
                Hexagon neighbour_hex = starting_point.hex.hex_neighbor(starting_point.hex, i);
                for (int r = 0; r < map.length; r++) {
                    for (int q = 0; q < map[0].length; q++) {
                        if (map[r][q].hex.r == neighbour_hex.r && map[r][q].hex.q == neighbour_hex.q && map[r][q].hex.s == neighbour_hex.s) {
                            neighbours.add(map[r][q]);
                        }
                    }
                }
            }
        }
        return neighbours;
    }

    public void save(){
        try {
                FileOutputStream outputStream = new FileOutputStream("save.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(this);
        }catch (java.io.IOException exception){
            exception.printStackTrace();
        }
    }

}
