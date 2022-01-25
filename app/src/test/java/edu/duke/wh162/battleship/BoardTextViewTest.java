package edu.duke.wh162.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    Board b1 = new BattleShipBoard(2, 2);
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
    Board b1 = new BattleShipBoard(w, h);
    BoardTextView view = new BoardTextView(b1);
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader + expectedBody + expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }
  
  @Test
  public void test_display_empty_3by2() {
    Board b2 = new BattleShipBoard(3, 2);
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
    Board wideBoard = new BattleShipBoard(11, 20);
    Board tallBoard = new BattleShipBoard(10, 27);
    assertThrows(IllegalArgumentException.class, ()->new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, ()->new BoardTextView(tallBoard));
  }
}
