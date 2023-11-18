import java.util.ArrayList;
import java.util.Random;

public class Game {
    private final Random rand = new Random();
    public Tile[][] map ;
    public ArrayList<Player> players = new ArrayList<Player>();
    Player current_turn;

    public Game(int tile_num, ArrayList<String> player_names){
        gen_map(tile_num);
        for(String name : player_names){
            players.add(new Player(name));
        }
        gen_starting_tiles();
        current_turn = players.get(0);

    }
    private void gen_map(int tile_num){
        //temp solution
        map = new Tile[6][6];
        for (int i = 0; i < 6 ; i++){
            for (int j = 0; j < 6 ; j++){
                map[i][j] = new Tile(new Hexagon(j,i));
            }
        }
    }

    private void gen_starting_tiles(){
        //TO DO: prevent too close spawns
        for (Player player: players) {
            boolean found = false;
            while (!found){
                Tile target = map[rand.nextInt(map.length-1)][rand.nextInt(map[0].length-1)];
                if (target.owner == null){
                    player.add_to_tiles(target);
                    player.captial = target;
                    found = true;

                    boolean gen_done = false;
                    while (!gen_done){
                        ArrayList<Tile> neighbours = find_neighbours_in_map(target);
                        neighbours.removeIf((x)->x.owner!=null);
                        if (neighbours.size() >=2){
                            int add_indx = rand.nextInt(neighbours.size()-1);
                            player.add_to_tiles(neighbours.get(add_indx));
                            neighbours.remove(add_indx);
                            player.add_to_tiles(neighbours.get(rand.nextInt(neighbours.size()-1)));
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

    public ArrayList<Tile> find_neighbours_in_map(Tile starting_point){
        ArrayList<Tile> neighbours = new ArrayList<Tile>();
        for (int i= 0; i < 6 ; i++){
           Hexagon neighbour_hex = starting_point.hex.hex_neighbor(starting_point.hex,i);
           if (0 <= neighbour_hex.q && neighbour_hex.q < map.length && 0<= neighbour_hex.r && neighbour_hex.r <= map[0].length ){
                if (map[neighbour_hex.r][neighbour_hex.q] != null){
                    neighbours.add(map[neighbour_hex.r][neighbour_hex.q]);
                }
           }
        }
        return neighbours;
    }
}
