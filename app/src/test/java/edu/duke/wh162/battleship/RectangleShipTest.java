package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    RectangleShip rec_sh = new RectangleShip("Submarine", new Coordinate(4, 6), 2, 3, 's', '*');
    assertTrue(rec_sh.occupiesCoordinates(new Coordinate(4, 6)));
    assertTrue(rec_sh.occupiesCoordinates(new Coordinate(5, 7)));
    assertFalse(rec_sh.occupiesCoordinates(new Coordinate(0, 0)));
    assertFalse(rec_sh.occupiesCoordinates(new Coordinate(3, 7)));
  }

  @Test
  void test_recordHit_wasHit() {
    RectangleShip rs = new RectangleShip("Submarine", new Coordinate(4, 6), 2, 3, 's', '*');
    Coordinate c1 = new Coordinate(4, 6);
    Coordinate c2 = new Coordinate(4, 7);
    Coordinate c3 = new Coordinate(5, 6);
    Coordinate c4 = new Coordinate(5, 7);
    rs.recordHitAt(c1);
    rs.recordHitAt(c2);
    assertTrue(rs.wasHitAt(c1));
    assertTrue(rs.wasHitAt(c2));
    assertFalse(rs.wasHitAt(c3));
    assertFalse(rs.wasHitAt(c4));
  }

  @Test
  void test_issunk() {
    RectangleShip rs = new RectangleShip("Submarine", new Coordinate(4, 6), 1, 3, 's', '*');
    Coordinate c1 = new Coordinate(4, 6);
    Coordinate c2 = new Coordinate(5, 6);
    Coordinate c3 = new Coordinate(6, 6);
    rs.recordHitAt(c1);
    rs.recordHitAt(c2);
    assertFalse(rs.isSunk());
    rs.recordHitAt(c3);
    assertTrue(rs.isSunk());
  }

  @Test
  void test_all() {
    RectangleShip rs = new RectangleShip("Submarine", new Coordinate(4, 6), 1, 3, 's', '*');
    Coordinate c1 = new Coordinate(0, 0);
    Coordinate c2 = new Coordinate(6, 7);
    assertThrows(IllegalArgumentException.class, ()->rs.recordHitAt(c1));
    assertThrows(IllegalArgumentException.class, ()->rs.wasHitAt(c1));
    assertThrows(IllegalArgumentException.class, ()->rs.getDisplayInfoAt(c1));

    assertThrows(IllegalArgumentException.class, ()->rs.recordHitAt(c2));
    assertThrows(IllegalArgumentException.class, ()->rs.wasHitAt(c2));
    assertThrows(IllegalArgumentException.class, ()->rs.getDisplayInfoAt(c1));
    assertEquals("Submarine", rs.getName());
  }
}
