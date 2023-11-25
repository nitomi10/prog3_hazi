package Tests;

import Game.*;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class BuildingTests {
    Game testGame;
    ArrayList<String> testPlayerNames = new ArrayList<>();
    @Before
    public void setUp(){
        for (int i = 0; i < 2 ;i++){
            testPlayerNames.add("Player "+i);
            testGame = new Game("small",testPlayerNames);
        }
    }

    @Test
    public void FarmTest(){
        testGame.current_turn.balance = 24;
        Farm f1 = null;
        try {
            f1 = new Farm(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
        }catch (NotEnoughMoneyError e){}
        assertNotNull(f1);
        assertEquals(7,testGame.current_turn.calculateIncome());
    }
    @Test
    public void CastleTest(){
        Castle c1 = (Castle) testGame.current_turn.capital.on_tile;
        assertNotNull(c1);
        ArrayList<Tile> near = testGame.find_neighbours_in_map(c1.ItsTile);
        boolean correct = true;
        for (Tile t : near){
            if (t.owner != null){
                if (t.owner.equals(c1.ItsTile.owner)){
                    if (t.defensiveValue < t.defensiveValue){
                        correct = false;
                    }
                }
            }
        }
        assertTrue(correct);
    }

    @Test
    public void TowerTest(){
        testGame.current_turn.balance = 15;
        Tower t1 = null;
        try {
            t1 = new Tower(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
        }catch (NotEnoughMoneyError e){}

        ArrayList<Tile> near = testGame.find_neighbours_in_map(t1.ItsTile);
        boolean correct = true;
        for (Tile t : near){
            if (t.owner != null){
                if (t.owner.equals(t1.ItsTile.owner)){
                    if (t.defensiveValue < t.defensiveValue){
                        correct = false;
                    }
                }
            }
        }
        assertTrue(correct);
    }

    @Test
    public void Strong_towerTest(){
        testGame.current_turn.balance = 35;
        Strong_tower t1 = null;
        try {
            t1 = new Strong_tower(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
        }catch (NotEnoughMoneyError e){}

        ArrayList<Tile> near = testGame.find_neighbours_in_map(t1.ItsTile);
        boolean correct = true;
        for (Tile t : near){
            if (t.owner != null){
                if (t.owner.equals(t1.ItsTile.owner)){
                    if (t.defensiveValue < t.defensiveValue){
                        correct = false;
                    }
                }
            }
        }
        assertTrue(correct);
    }
}
