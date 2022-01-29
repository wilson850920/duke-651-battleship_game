package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {

  @Test
  void test_construct_singleship() {
    ShipDisplayInfo<Character> s = new SimpleShipDisplayInfo<Character>('a','b');
    Coordinate c = new Coordinate(2, 2);
    assertEquals('b',s.getInfo(c, true));
    assertEquals('a',s.getInfo(c, false));
  }
}
