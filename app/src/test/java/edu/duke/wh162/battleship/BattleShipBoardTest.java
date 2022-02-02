package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {

  @Test
  public void test_right_ship_coordinate() {
    Character[][] ch = new Character[5][5];
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(5, 5, 'X');

    checkWhatIsAtBoard(b, ch);
    ch[4][4] = 's';
    assertThrows(IllegalArgumentException.class, () -> checkWhatIsAtBoard(b, ch));

    Coordinate c = new Coordinate(4, 4);
    RectangleShip<Character> s = new RectangleShip<Character>("Submarine", c, 's', '*');
    assertEquals(null, b.tryAddShip(s));
    assertEquals('s', b.whatIsAtForSelf(c));
 
    Coordinate out_b = new Coordinate(10, 10);
    assertThrows(IllegalArgumentException.class, () -> b.whatIsAtForSelf(out_b));
    
    Coordinate r = new Coordinate(2, 3);
    assertEquals(null, b.whatIsAtForSelf(r));
  }

  /** 
   * A battleshipboard and T[][] to make sure ships are in the right position
   * @param b is the battleshipboard to check
   * @param expected is the expected placements of ships
   * @throws Illegalargumentexception if the board coordinate info is not equal to expected
   */
  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected) {
    for (int i = 0; i < expected.length; i++) {
      for (int k = 0; k < expected[i].length; k++) {
        Coordinate c = new Coordinate(i, k);
        if (expected[i][k] != b.whatIsAtForSelf(c)) {
          throw new IllegalArgumentException("The expected board is not as same as this");
        }
      }
    }    
  }
  
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard(10, 20, 'X');
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }
  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(10, 0, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(0, 20, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(10, -5, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(-8, 20, 'X'));
  }

  @Test
  void test_new_addship(){
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(10, 10, 'X');
    V1ShipFactory sf = new V1ShipFactory();
    Placement v1 = new Placement(new Coordinate(2, 2), 'V');
    Ship<Character> s1 = sf.makeSubmarine(v1);

    Placement h1 = new Placement(new Coordinate(2, 2), 'H');
    Ship<Character> s2 = sf.makeSubmarine(h1);
    assertEquals(null, b.tryAddShip(s1));
    assertEquals("Your input placement is invalid: the coordinate you typed in overlaps with one of the existing ships.", b.tryAddShip(s2));    
  }

  @Test
  void test_fireAt() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(5, 5, 'X');
    V1ShipFactory sf = new V1ShipFactory();
    Coordinate c1 = new Coordinate(2, 3);
    Coordinate c2 = new Coordinate(2, 4);
    Coordinate c3 = new Coordinate(2, 5);
    Placement h1 = new Placement(c1, 'H');
    Ship<Character> s1 = sf.makeSubmarine(h1);
    b.tryAddShip(s1);
    assertSame(s1, b.fireAt(c1));
    assertEquals(false, s1.isSunk());
    assertSame(s1, b.fireAt(c2));
    assertEquals(true, s1.isSunk());
    assertNotSame(s1, b.fireAt(c3));
    assertEquals(null, b.fireAt(c3));
  }

  @Test
  void test_whatIsAtForEnemy() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(5, 5, 'X');
    V1ShipFactory sf = new V1ShipFactory();
    Coordinate c1 = new Coordinate(0, 0);
    Coordinate c2 = new Coordinate(0, 2);
    Coordinate c3 = new Coordinate(4, 2);
    Placement v1 = new Placement(c1, 'H');
    Ship<Character> s = sf.makeSubmarine(v1);
    b.tryAddShip(s);
    b.fireAt(c2);
    assertEquals('X', b.whatIsAtForEnemy(c2));
    assertEquals(null, b.whatIsAtForEnemy(c3));
  }

  @Test
  void test_allshink() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(6, 6, 'X');
    V1ShipFactory sf = new V1ShipFactory();
    Placement p1 = new Placement("A0H");
    Ship<Character> s1 = sf.makeSubmarine(p1);
    b.tryAddShip(s1);
    b.fireAt(new Coordinate(0, 0));

    assertEquals(false, b.checkshipshinkall());
    b.fireAt(new Coordinate(0, 1));
    assertEquals(true, b.checkshipshinkall());
  }
}
