package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NewBattleShipTest {
  @Test
  public void test_battleconstruct() {
    NewBattleShip ns_u = new NewBattleShip("BattleShip", new Coordinate(2, 2), 'u', 'b', '*');
    assertTrue(ns_u.occupiesCoordinates(new Coordinate(2, 3)));
    assertTrue(ns_u.occupiesCoordinates(new Coordinate(3, 4)));
    assertFalse(ns_u.occupiesCoordinates(new Coordinate(2, 2)));
    assertFalse(ns_u.occupiesCoordinates(new Coordinate(4, 2)));

    NewBattleShip ns_r = new NewBattleShip("BattleShip", new Coordinate(3, 3), 'r', 'b', '*');
    assertTrue(ns_r.occupiesCoordinates(new Coordinate(4, 4)));
    assertTrue(ns_r.occupiesCoordinates(new Coordinate(5, 3)));
    assertFalse(ns_r.occupiesCoordinates(new Coordinate(3, 4)));
    assertFalse(ns_r.occupiesCoordinates(new Coordinate(4, 5)));

    NewBattleShip ns_l = new NewBattleShip("BattleShip", new Coordinate(5, 5), 'l', 'b', '*');
    assertTrue(ns_l.occupiesCoordinates(new Coordinate(6, 5)));
    assertTrue(ns_l.occupiesCoordinates(new Coordinate(5, 6)));
    assertFalse(ns_l.occupiesCoordinates(new Coordinate(7, 5)));
    assertFalse(ns_l.occupiesCoordinates(new Coordinate(6, 7)));

    NewBattleShip ns_d = new NewBattleShip("BattleShip", new Coordinate(1, 3), 'd', 'b', '*');
    assertTrue(ns_d.occupiesCoordinates(new Coordinate(2, 4)));
    assertTrue(ns_d.occupiesCoordinates(new Coordinate(1, 5)));
    assertFalse(ns_d.occupiesCoordinates(new Coordinate(2, 5)));
    assertFalse(ns_d.occupiesCoordinates(new Coordinate(2, 3)));
    assertEquals("BattleShip", ns_d.getName());
  }

}
