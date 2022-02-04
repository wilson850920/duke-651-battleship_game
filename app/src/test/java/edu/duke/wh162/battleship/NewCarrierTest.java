package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NewCarrierTest {
  @Test
  public void test_newCarrier() {
    NewCarrier ns_u = new NewCarrier("Carrier", new Coordinate(2, 2), 'u', 'b', '*');
    assertTrue(ns_u.occupiesCoordinates(new Coordinate(5, 2)));
    assertTrue(ns_u.occupiesCoordinates(new Coordinate(6, 3)));
    assertTrue(ns_u.occupiesCoordinates(new Coordinate(4, 3)));
    assertFalse(ns_u.occupiesCoordinates(new Coordinate(6, 2)));
    assertFalse(ns_u.occupiesCoordinates(new Coordinate(3, 3)));

    NewCarrier ns_r = new NewCarrier("Carrier", new Coordinate(3, 3), 'r', 'b', '*');
    assertTrue(ns_r.occupiesCoordinates(new Coordinate(3, 7)));
    assertTrue(ns_r.occupiesCoordinates(new Coordinate(4, 5)));
    assertFalse(ns_r.occupiesCoordinates(new Coordinate(3, 3)));
    assertFalse(ns_r.occupiesCoordinates(new Coordinate(4, 6)));

    NewCarrier ns_l = new NewCarrier("Carrier", new Coordinate(5, 5), 'l', 'b', '*');
    assertTrue(ns_l.occupiesCoordinates(new Coordinate(5, 9)));
    assertTrue(ns_l.occupiesCoordinates(new Coordinate(6, 5)));
    assertTrue(ns_l.occupiesCoordinates(new Coordinate(6, 8)));
    assertFalse(ns_l.occupiesCoordinates(new Coordinate(5, 6)));
    assertFalse(ns_l.occupiesCoordinates(new Coordinate(6, 9)));

    NewCarrier ns_d = new NewCarrier("Carrier", new Coordinate(1, 3), 'd', 'b', '*');
    assertTrue(ns_d.occupiesCoordinates(new Coordinate(2, 4)));
    assertTrue(ns_d.occupiesCoordinates(new Coordinate(5, 4)));
    assertTrue(ns_d.occupiesCoordinates(new Coordinate(3, 3)));
    assertFalse(ns_d.occupiesCoordinates(new Coordinate(4, 3)));
    assertFalse(ns_d.occupiesCoordinates(new Coordinate(1, 4)));
    assertEquals("Carrier", ns_d.getName());


  }

}
