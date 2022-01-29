package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  /** 
  @Test
  void test_makeCoords() {
    RectangleShip rectangleShip = new RectangleShip();
    HashSet<Coordinate> add_hash = new HashSet<Coordinate>();
    add_hash.add(new Coordinate(2, 5));
    add_hash.add(new Coordinate(2, 6));
    add_hash.add(new Coordinate(3, 5));
    add_hash.add(new Coordinate(3, 6));

    assertEquals(add_hash, rectangleShip.makeCoords(new Coordinate(2, 5), 2, 2));
  }
  
  */
  @Test
  void rectangleship_construct() {
    RectangleShip rec_sh = new RectangleShip(new Coordinate(4, 6), 2, 3, 's', '*');
    assertTrue(rec_sh.occupiesCoordinates(new Coordinate(4, 6)));
    assertTrue(rec_sh.occupiesCoordinates(new Coordinate(5, 7)));
    assertFalse(rec_sh.occupiesCoordinates(new Coordinate(0, 0)));
    assertFalse(rec_sh.occupiesCoordinates(new Coordinate(3, 7)));
  }
  
}
