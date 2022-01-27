package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {

  @Test
  public void test_toString() {
    Placement p1 = new Placement("Z2V");
    assertEquals("(25, 2), V", p1.toString());
  }
  
  @Test
  public void test_equal() {

    Placement p1 = new Placement("A1V");
    Placement p2 = new Placement("A1V");
    Placement p3 = new Placement("B5H");
    Placement p4 = new Placement("C9H");

    assertEquals(p1,p2);
    assertEquals(p1,p1);
    assertNotEquals(p1,p3);
    assertNotEquals(p3,p4);
    assertNotEquals(p4,"C9H");
  }

  @Test
  public void test_hashCode() {
    Placement p1 = new Placement("A0V");
    Placement p2 = new Placement("A0V");
    Placement p3 = new Placement("G5V");
    Placement p4 = new Placement("L9V");

    assertEquals(p1.hashCode(), p2.hashCode());
    assertNotEquals(p1.hashCode(), p3.hashCode());
    assertNotEquals(p1.hashCode(), p4.hashCode());
  }

  @Test
  public void test_valid_cases() {
    Placement p1 = new Placement("A1H");
    Coordinate c1 = new Coordinate(0, 1);
    Placement p2 = new Placement("B5V");
    Coordinate c2 = new Coordinate(1, 5);
    Placement p3 = new Placement("C2h");
    Coordinate c3 = new Coordinate(2, 2);
    Placement p4 = new Placement("D4v");
    Coordinate c4 = new Coordinate(3, 4);

    assertEquals('H', p1.getOrient());
    assertEquals(c1, p1.getWhere());
    assertEquals('V', p2.getOrient());
    assertEquals(c2, p2.getWhere());
    assertEquals('H', p3.getOrient());
    assertEquals(c3, p3.getWhere());
    assertEquals('V', p4.getOrient());
    assertEquals(c4, p4.getWhere());

    Coordinate c5 = new Coordinate(3, 4);
    Placement p5 = new Placement(c5, 'v');
    Coordinate c6 = new Coordinate(6, 6);
    Placement p6 = new Placement(c6, 'V');
    assertEquals(p6, p6);
  }

  @Test
  public void test_invalid_cases() {
    assertThrows(IllegalArgumentException.class, () -> new Placement("54H"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("GGH"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("@6V"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("[0V"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A0A"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A00"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("Z9}"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A1O"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A1"));
  }
}
