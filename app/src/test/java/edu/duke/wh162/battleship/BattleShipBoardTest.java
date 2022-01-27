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
    Ship<Character> s = new BasicShip(c);
    assertEquals(true, b.tryAddShip(s));
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
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }
  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20));
  }
}
