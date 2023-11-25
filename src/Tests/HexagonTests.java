package Tests;
import org.junit.*;
import Game.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class HexagonTests {
    Hexagon h1;
    Hexagon h2;
    @Before
    public void setUp(){
        h1 = new Hexagon(1,-1,0);
        h2 = new Hexagon(-1,1,0);
    }

    @Test
    public void HexArithmeticTests(){
        Hexagon addres = h1.hex_add(h1,h2);
        assertEquals(0,addres.q);
        assertEquals(0,addres.r);
        assertEquals(0,addres.s);
        Hexagon subres = h1.hex_sub(h1,h2);
        assertEquals(2,subres.q);
        assertEquals(-2,subres.r);
        assertEquals(0,subres.s);
    }

    @Test
    public void HexNeighbourTest(){
        assertEquals(new Hexagon(2,-1,-1),h1.hex_neighbor(h1,0));
        assertEquals(new Hexagon(2,-2,0),h1.hex_neighbor(h1,1));
        assertEquals(new Hexagon(1,-2,1),h1.hex_neighbor(h1,2));
        assertEquals(new Hexagon(0,-1,1),h1.hex_neighbor(h1,3));
        assertEquals(new Hexagon(0,0,0),h1.hex_neighbor(h1,4));
        assertEquals(new Hexagon(1,0,-1),h1.hex_neighbor(h1,5));
    }
}
