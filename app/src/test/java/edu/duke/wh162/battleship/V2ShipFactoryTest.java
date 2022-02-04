package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest {

  private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter, Coordinate... expectedLocs) {
    assertEquals(expectedName, testShip.getName());
    for (Coordinate c: expectedLocs) {
      assertTrue(testShip.occupiesCoordinates(c));
      assertEquals(expectedLetter, testShip.getDisplayInfoAt(c, true));
    }
  }
  
  @Test
  public void test_makeShip() {

    V2ShipFactory sf1 = new V2ShipFactory();
    Placement p1_1 = new Placement(new Coordinate(3, 4), 'H');
    Placement p1_2 = new Placement(new Coordinate(3, 4), 'V');
    Ship<Character> s1_1 = sf1.makeSubmarine(p1_1);
    Ship<Character> s1_2 = sf1.makeSubmarine(p1_2);
    checkShip(s1_1, "Submarine", 's', new Coordinate(3, 4), new Coordinate(3, 5));
    checkShip(s1_2, "Submarine", 's', new Coordinate(3, 4), new Coordinate(4, 4));

    V2ShipFactory sf2 = new V2ShipFactory();
    Placement p2_1 = new Placement(new Coordinate(1, 2), 'H');
    Placement p2_2 = new Placement(new Coordinate(1, 2), 'V');
    Ship<Character> s2_1 = sf2.makeDestroyer(p2_1);
    Ship<Character> s2_2 = sf2.makeDestroyer(p2_2);
    checkShip(s2_1, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(1, 3), new Coordinate(1, 4));
    checkShip(s2_2, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2));

    V2ShipFactory sf3 = new V2ShipFactory();
    Placement p3_1 = new Placement(new Coordinate(6, 6), 'U');
    Placement p3_2 = new Placement(new Coordinate(6, 6), 'R');
    Placement p3_3 = new Placement(new Coordinate(6, 6), 'L');
    Placement p3_4 = new Placement(new Coordinate(6, 6), 'D');
    Ship<Character> s3_1 = sf3.makeBattleship(p3_1);
    Ship<Character> s3_2 = sf3.makeBattleship(p3_2);
    Ship<Character> s3_3 = sf3.makeBattleship(p3_3);
    Ship<Character> s3_4 = sf3.makeBattleship(p3_4);
    checkShip(s3_1, "Battleship", 'b', new Coordinate(6, 7), new Coordinate(7, 6), new Coordinate(7, 7), new Coordinate(7, 8));
    checkShip(s3_2, "Battleship", 'b', new Coordinate(6, 6), new Coordinate(7, 6), new Coordinate(8, 6), new Coordinate(7, 7));
    checkShip(s3_3, "Battleship", 'b', new Coordinate(7, 6), new Coordinate(6, 7), new Coordinate(7, 7), new Coordinate(8, 7));
    checkShip(s3_4, "Battleship", 'b', new Coordinate(6, 6), new Coordinate(6, 7), new Coordinate(6, 8), new Coordinate(7, 7));

    V2ShipFactory sf4 = new V2ShipFactory();
    Placement p4_1 = new Placement(new Coordinate(0, 0), 'U');
    Placement p4_2 = new Placement(new Coordinate(0, 0), 'R');
    Placement p4_3 = new Placement(new Coordinate(0, 0), 'L');
    Placement p4_4 = new Placement(new Coordinate(0, 0), 'D');
    Ship<Character> s4_1 = sf4.makeCarrier(p4_1);
    Ship<Character> s4_2 = sf4.makeCarrier(p4_2);
    Ship<Character> s4_3 = sf4.makeCarrier(p4_3);
    Ship<Character> s4_4 = sf4.makeCarrier(p4_4);
    checkShip(s4_1, "Carrier", 'c', new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0), new Coordinate(3, 0), new Coordinate(2, 1), new Coordinate(3, 1), new Coordinate(4, 1));
    checkShip(s4_2, "Carrier", 'c', new Coordinate(0, 1), new Coordinate(0, 2), new Coordinate(0, 3), new Coordinate(0, 4), new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(1, 2));
    checkShip(s4_3, "Carrier", 'c', new Coordinate(0, 2), new Coordinate(0, 3), new Coordinate(0, 4), new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(1, 2), new Coordinate(1, 3));
    checkShip(s4_4, "Carrier", 'c', new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0), new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(3, 1), new Coordinate(4, 1));
   
  }

  @Test
  void test_invalid_placement() {
    V2ShipFactory sf = new V2ShipFactory();
    Placement p = new Placement(new Coordinate(0, 0), 'G');
    assertThrows(IllegalArgumentException.class, () -> sf.makeDestroyer(p));
  }
}
