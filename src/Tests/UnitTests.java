package Tests;

import Game.Game;
import Game.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;
public class UnitTests {
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
    public void PeasantTest(){
        testGame.current_turn.balance+=10;
        Peasant p1 = null;
        try {
            p1 = new Peasant(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
        }catch (NotEnoughMoneyError e){}
        assertNotNull(p1);
        Tile tile = p1.ItsTile;
        p1.upgrade();
        boolean upgraded = false;
        if (tile.on_tile instanceof Spearman){
            upgraded = true;
        }
        assertTrue(upgraded);
    }

    @Test(expected = NotEnoughMoneyError.class)
    public void PeastCantBuyTest() throws NotEnoughMoneyError {
        testGame.current_turn.balance=0;
        Peasant p1 = new Peasant(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
    }

    @Test
    public void SpearmanTest(){
        testGame.current_turn.balance+=20;
        Spearman p1 = null;
        try {
            p1 = new Spearman(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
        }catch (NotEnoughMoneyError e){}
        assertNotNull(p1);
        Tile tile = p1.ItsTile;
        p1.upgrade();
        boolean upgraded = false;
        if (tile.on_tile instanceof Baron){
            upgraded = true;
        }
        assertTrue(upgraded);
    }

    @Test(expected = NotEnoughMoneyError.class)
    public void SpearmanCantBuyTest() throws NotEnoughMoneyError {
        testGame.current_turn.balance=0;
        Spearman p1 = new Spearman(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
    }

    @Test
    public void BaronTest(){
        testGame.current_turn.balance+=30;
        Baron p1 = null;
        try {
            p1 = new Baron(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
        }catch (NotEnoughMoneyError e){}
        assertNotNull(p1);
        Tile tile = p1.ItsTile;
        p1.upgrade();
        boolean upgraded = false;
        if (tile.on_tile instanceof Knight){
            upgraded = true;
        }
        assertTrue(upgraded);
    }

    @Test(expected = NotEnoughMoneyError.class)
    public void BaronCantBuyTest() throws NotEnoughMoneyError {
        testGame.current_turn.balance=0;
        Baron p1 = new Baron(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
    }
    @Test
    public void KnightTest(){
        testGame.current_turn.balance+=40;
        Knight p1 = null;
        try {
            p1 = new Knight(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
        }catch (NotEnoughMoneyError e){}
        assertNotNull(p1);
    }

    @Test(expected = NotEnoughMoneyError.class)
    public void KnightCantBuyTest() throws NotEnoughMoneyError {
        testGame.current_turn.balance=0;
        Knight p1 = new Knight(testGame,testGame.current_turn,testGame.current_turn.owned_tiles.get(1));
    }

    @Test
    public void desertTest() {
        testGame.current_turn.balance = 40;
        Knight k1 = null;
        try {
            k1 = new Knight(testGame, testGame.current_turn, testGame.current_turn.owned_tiles.get(1));

        } catch (NotEnoughMoneyError e) {
        }
        Tile tile = k1.ItsTile;
        testGame.current_turn.endTurn();
        testGame.current_turn.startNewTurn();
        testGame.current_turn.endTurn();
        testGame.current_turn.startNewTurn();
        assertNull(tile.on_tile);
    }
}
