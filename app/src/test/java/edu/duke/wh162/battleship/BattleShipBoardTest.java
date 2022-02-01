package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {

  @Test
  public void test_right_ship_coordinate() {
    Character[][] ch = new Character[5][5];
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(5, 5);

    //check emptiness of the board
    checkWhatIsAtBoard(b, ch);

    //check if the board is not expected as board t
    ch[4][4] = 's';
    assertThrows(IllegalArgumentException.class, () -> checkWhatIsAtBoard(b, ch));

    //check if addship is correct
    Coordinate c = new Coordinate(4, 4);
    //Ship<Character> s = new BasicShip(c);
    //Ship<Character> s = new RectangleShip<Character>("submarine", c, 's', '*');
    RectangleShip<Character> s = new RectangleShip<Character>("Submarine", c, 's', '*');
    assertEquals(null, b.tryAddShip(s));
    //assertEquals(true, b.tryAddShip(s)); 
    assertEquals('s', b.whatIsAt(c));
 
    //whatIsAt out of bound
    Coordinate out_b = new Coordinate(10, 10);
    assertThrows(IllegalArgumentException.class, () -> b.whatIsAt(out_b));
    
    //whatIsAt null
    Coordinate r = new Coordinate(2, 3);
    assertEquals(null, b.whatIsAt(r));
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
        if (expected[i][k] != b.whatIsAt(c)) {
          throw new IllegalArgumentException("The expected board is not as same as this");
        }
      }
    }    
  }
  
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }
  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(10, 0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(0, 20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(10, -5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(-8, 20));
  }

  @Test
  void test_new_addship(){
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(10, 10);
    V1ShipFactory sf = new V1ShipFactory();
    Placement v1 = new Placement(new Coordinate(2, 2), 'V');
    Ship<Character> s1 = sf.makeSubmarine(v1);
    //b.tryAddShip(s1);

    Placement h1 = new Placement(new Coordinate(2, 2), 'H');
    Ship<Character> s2 = sf.makeSubmarine(h1);
    assertEquals(null, b.tryAddShip(s1));
    //b.tryAddShip(s1);
    //System.out.print("D");
    assertEquals("Your input placement is invalid: the coordinate you typed in overlaps with one of the existing ships.", b.tryAddShip(s2));
    
    
  }
}
