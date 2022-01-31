package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {

  private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes){
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h);
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("A", board, input, output, shipFactory);
  }
  
  @Test
  void test_read_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    //StringReader sr = new StringReader("B2V\nC8H\na4v\n");
    //PrintStream ps = new PrintStream(bytes, true);
    //Board<Character> b = new BattleShipBoard<Character>(10, 20);
    //ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);
    //TextPlayer player = new TextPlayer("A", b, new BufferedReader(sr), ps, new V1ShipFactory());
   
    String prompt = "Please enter a location for a ship:";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');

    for (int i = 0; i < expected.length; i++) {
      Placement p = player.readPlacement(prompt);
      assertEquals(p, expected[i]); // did we get the right Placement back
      // System.out.println(bytes.toString());
      // assertEquals(prompt + "\n", bytes.toString()); //should have printed prompt
      // and newline
      bytes.reset(); // clear out bytes for next time around
    }
  }


  
  @Test
  void test_doOnePlacement() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    //PrintStream ps = new PrintStream(bytes, true);
    //String prompt = "Where would you like to put your ship?\n";
    //StringReader sr = new StringReader("C0H\n");
    //Board<Character> b = new BattleShipBoard<Character>(3, 3);
    //App app = new App(b, sr, ps);
    TextPlayer player = createTextPlayer(3, 3, "C0H\n", bytes);

    String temp = "  0|1|2\n" +
      "A  | |  A\n" +
      "B  | |  B\n" +
      "C d|d|d C\n" +
      "  0|1|2\n";

    //String expected = "Player A where would you like to put your ship\n" + temp + '\n';
    String expected = "Player A where do you want to place a Destroyer?\n" + temp + '\n';  
    V1ShipFactory sf = new V1ShipFactory(); 
    player.doOnePlacement();//"Destroyer", (p)->sf.makeDestroyer(p));
    //app.doOnePlacement();
    //assertEquals(prompt + temp + "\n", bytes.toString());
    assertEquals(expected,bytes.toString());
  }

  @Test
  void test_doPlacementPhase() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = createTextPlayer(3, 3, "C0H\n", bytes);

    String expectedchoice = "Player A: you are going to place the following ships (which are all rectangular). For each ship, type the coordinate of the upper left side of the ship, followed by either H (for horizontal) or V (for vertical).  For example M4H would place a ship horizontally starting at M4 and going to the right.  You have\n\n"
    + "2 \"Submarines\" ships that are 1x2\n" + "3 \"Destroyers\" that are 1x3\n"
    + "3 \"Battleships\" that are 1x4\n" + "2 \"Carriers\" that are 1x6\n\n";

    String expectedHeader = "  0|1|2\n";
    String expectednothing = "A  | |  A\n" + "B  | |  B\n" + "C  | |  C\n";
    String expectedBody = "A  | |  A\n" + "B  | |  B\n" + "C d|d|d C\n";

    String expected = expectedHeader + expectednothing + expectedHeader + "\n" + expectedchoice + "Player A where do you want to place a Destroyer?\n" + expectedHeader + expectedBody + expectedHeader + "\n";
    p1.doPlacementPhase();
    assertEquals(expected, bytes.toString());
  }
}
