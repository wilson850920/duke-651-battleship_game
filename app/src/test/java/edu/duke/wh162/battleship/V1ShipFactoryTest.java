package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V1ShipFactoryTest {

  private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter, Coordinate... expectedLocs) {
    assertEquals(expectedName, testShip.getName());
    for (Coordinate c: expectedLocs) {
      assertTrue(testShip.occupiesCoordinates(c));
      assertEquals(expectedLetter, testShip.getDisplayInfoAt(c, true));
    }
  }

  @Test
  public void test_makeShip() {
    //test submarine
    V1ShipFactory sf1 = new V1ShipFactory();
    Placement p1_1 = new Placement(new Coordinate(3, 4), 'H');
    Placement p1_2 = new Placement(new Coordinate(3, 4), 'V');
    Ship<Character> s1_1 = sf1.makeSubmarine(p1_1);
    Ship<Character> s1_2 = sf1.makeSubmarine(p1_2);
    checkShip(s1_1, "Submarine", 's', new Coordinate(3, 4), new Coordinate(3, 5));
    checkShip(s1_2, "Submarine", 's', new Coordinate(3, 4), new Coordinate(4, 4));
    
    //test Destoryer
    V1ShipFactory sf2 = new V1ShipFactory();
    Placement p2_1 = new Placement(new Coordinate(1, 2), 'H');
    Placement p2_2 = new Placement(new Coordinate(1, 2), 'V');
    Ship<Character> s2_1 = sf2.makeDestroyer(p2_1);
    Ship<Character> s2_2 = sf2.makeDestroyer(p2_2);
    checkShip(s2_1, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(1, 3), new Coordinate(1, 4));
    checkShip(s2_2, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2));

    //test BattleShip
    V1ShipFactory sf3 = new V1ShipFactory();
    Placement p3_1 = new Placement(new Coordinate(2, 6), 'H');
    Placement p3_2 = new Placement(new Coordinate(2, 6), 'V');
    Ship<Character> s3_1 = sf3.makeBattleship(p3_1);
    Ship<Character> s3_2 = sf3.makeBattleship(p3_2);
    checkShip(s3_1, "BattleShip", 'b', new Coordinate(2, 6), new Coordinate(2, 7), new Coordinate(2, 8), new Coordinate(2, 9));
    checkShip(s3_2, "BattleShip", 'b', new Coordinate(2, 6), new Coordinate(3, 6), new Coordinate(4, 6), new Coordinate(5, 6));

    //test Carrier
    V1ShipFactory sf4 = new V1ShipFactory();
    Placement p4_1 = new Placement(new Coordinate(2, 4), 'H');
    Placement p4_2 = new Placement(new Coordinate(2, 4), 'V');
    Ship<Character> s4_1 = sf4.makeCarrier(p4_1);
    Ship<Character> s4_2 = sf4.makeCarrier(p4_2);
    checkShip(s4_1, "Carrier", 'c', new Coordinate(2, 4), new Coordinate(2, 5), new Coordinate(2, 6), new Coordinate(2, 7), new Coordinate(2, 8), new Coordinate(2, 9));
    checkShip(s4_2, "Carrier", 'c', new Coordinate(2, 4), new Coordinate(3, 4), new Coordinate(4, 4), new Coordinate(5, 4), new Coordinate(6, 4), new Coordinate(7, 4));
  }

    @Test
    void test_invalid_placement(){
      V1ShipFactory sf = new V1ShipFactory();
      Placement p = new Placement(new Coordinate(0, 0), 'G');
      assertThrows(IllegalArgumentException.class, () -> sf.makeDestroyer(p));
    }

  
}
