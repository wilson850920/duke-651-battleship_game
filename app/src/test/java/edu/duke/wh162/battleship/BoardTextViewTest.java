package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    Board<Character> b1 = new BattleShipBoard(2, 2, 'X');
    BoardTextView view = new BoardTextView(b1);
    String expectedHeader = "  0|1\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected =
      expectedHeader +
      "A  |  A\n" +
      "B  |  B\n" +
      expectedHeader;
      assertEquals(expected, view.displayMyOwnBoard());
  }
  
  private void emptyBoardHelper(int w, int h, String expectedHeader, String expectedBody) {
    Board<Character> b1 = new BattleShipBoard(w, h, 'X');
    BoardTextView view = new BoardTextView(b1);
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader + expectedBody + expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }
  
  @Test
  public void test_display_empty_3by2() {
    Board<Character> b2 = new BattleShipBoard(3, 2, 'X');
    BoardTextView view = new BoardTextView(b2);
    String expectedHeader = "  0|1|2\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected =
      expectedHeader +
      "A  | |  A\n" +
      "B  | |  B\n" +
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }

  @Test
  public void test_display_notempty_3by5() {
    String e_Header = "  0|1|2\n";
    String e_body =
      "A  | |s A\n" +
      "B  | |  B\n" +
      "C  |s|  C\n" +
      "D  | |  D\n" +
      "E  | |  E\n";
    Board<Character> b = new BattleShipBoard(3, 5, 'X');
    Coordinate c1 = new Coordinate(0, 2);
    Coordinate c2 = new Coordinate(2, 1);
    RectangleShip<Character> s1 = new RectangleShip<Character>("submarine", c1, 's', '*');
    RectangleShip<Character> s2 = new RectangleShip<Character>("submarine", c2, 's', '*');
    b.tryAddShip(s1);
    b.tryAddShip(s2);
    BoardTextView view = new BoardTextView(b);
    assertEquals(e_Header + e_body + e_Header, view.displayMyOwnBoard());
  }
  
  @Test
  public void test_display_empty_3by5() {
    String e_Header = "  0|1|2\n";
    String e_body =
      "A  | |  A\n" +
      "B  | |  B\n" +
      "C  | |  C\n" +
      "D  | |  D\n" +
      "E  | |  E\n";
    emptyBoardHelper(3, 5, e_Header, e_body);
  }
  
  @Test
  public void test_invalid_board_size() {
    Board<Character> wideBoard = new BattleShipBoard(11, 20, 'X');
    Board<Character> tallBoard = new BattleShipBoard(10, 27, 'X');
    assertThrows(IllegalArgumentException.class, ()->new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, ()->new BoardTextView(tallBoard));
  }

  @Test
  public void test_display_enemy_board() {
    String myView =
      "  0|1|2|3\n" +
      "A  | | |* A\n" +
      "B s|s| |d B\n" +
      "C  | | |d C\n" +
      "  0|1|2|3\n";

    String enemyView =
      "  0|1|2|3\n" +
      "A  | |X|d A\n" +
      "B  | | |  B\n" +
      "C  | | |  C\n" +
      "  0|1|2|3\n";
    Board<Character> b = new BattleShipBoard(4, 3, 'X');
    Placement p1 = new Placement("B0H");
    Placement p2 = new Placement("A3V");
    V1ShipFactory sf = new V1ShipFactory();
    Ship<Character> s1 = sf.makeSubmarine(p1);
    Ship<Character> s2 = sf.makeDestroyer(p2);
    b.tryAddShip(s1);
    b.tryAddShip(s2);
    
    BoardTextView view = new BoardTextView(b);
    Coordinate hit = new Coordinate(0, 3);
    Coordinate mis = new Coordinate(0, 2);
    b.fireAt(hit);
    b.fireAt(mis);
    assertEquals(myView, view.displayMyOwnBoard());
    assertEquals(enemyView, view.displayEnemyBoard());       
  }

  @Test
  public void test_spacecreate() {
    Board<Character> b = new BattleShipBoard(6, 6, 'X');
    BoardTextView view = new BoardTextView(b);
    String s1 = view.createspace(0);
    String s2 = view.createspace(1);

    assertEquals(null, s1);
    assertEquals(" ", s2);
  }

  @Test
  public void test_twoboarddisply() {
    String myView =
      "  0|1|2|3\n" +
      "A  | |s|s A\n" +
      "B  | | |  B\n" +
      "C  | | |  C\n" +
      "  0|1|2|3\n";
      String enemyView =
      "  0|1|2|3\n" +
      "A d| | |  A\n" +
      "B d| | |  B\n" +
      "C d| | |  C\n" +
        "  0|1|2|3\n";

      Board<Character> b1 = new BattleShipBoard(4, 3, 'X');
      Board<Character> b2 = new BattleShipBoard(4, 3, 'X');
      V1ShipFactory sf = new V1ShipFactory();
      Ship<Character> s1 = sf.makeSubmarine(new Placement("A2H"));
      Ship<Character> s2 = sf.makeDestroyer(new Placement("A0v"));

      b1.tryAddShip(s1);
      b2.tryAddShip(s2);
      BoardTextView v1 = new BoardTextView(b1);
      BoardTextView v2 = new BoardTextView(b2);

      String expected ="\n"+
        "     my board                 enemy board\n"+
   "  0|1|2|3                    0|1|2|3\n"+
   "A  | |s|s A                A  | | |  A\n"+
   "B  | | |  B                B  | | |  B\n"+
   "C  | | |  C                C  | | |  C\n"+
        "  0|1|2|3                    0|1|2|3\n";

      assertEquals(expected, v1.displayMyBoardWithEnemyNextToIt(v2, "my board", "enemy board"));
  }
}
