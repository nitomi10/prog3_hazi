package Tests;
import org.junit.*;
import Game.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTests {
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
    public void GameConstructorTest(){
        assertEquals(2,testGame.players.size());
        assertEquals(testGame.players.get(0),testGame.current_turn);
        assertNull(testGame.won);
    }
    @Test
    public void GameMapGenTest(){
        assertEquals(36,testGame.map.length * testGame.map[0].length);
        testGame = new Game("medium",testPlayerNames);
        assertEquals(60,testGame.map.length * testGame.map[0].length);
        testGame = new Game("large",testPlayerNames);
        assertEquals(108,testGame.map.length * testGame.map[0].length);
    }
    @Test
    public void GamePlayerLostTest(){
        testGame.PlayerLost(testGame.players.get(0));
        assertEquals(1,testGame.players.size());
        assertNotNull(testGame.won);
    }
    @Test
    public void GameTestNeighbours(){
        assertEquals(6,testGame.find_neighbours_in_map(testGame.map[2][2]).size());
    }
}
