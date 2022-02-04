package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TextPlayerTest {

  private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes){
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
    return new TextPlayer("A", board, input, output, shipFactory);
  }
  
  @Test
  void test_read_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);
   
    String prompt = "Please enter a location for a ship:";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');

    for (int i = 0; i < expected.length; i++) {
      Placement p = player.readPlacement(prompt);
      assertEquals(p, expected[i]); // did we get the right Placement back
      bytes.reset(); // clear out bytes for next time around
    }
  }

  @Test
  void test_null() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = createTextPlayer(3, 3, "", bytes);
    V2ShipFactory sf = new V2ShipFactory();
    assertThrows(IOException.class, ()->p1.doOnePlacement("Carrier", (p)->sf.makeCarrier(p)));
  }
  
  @Test
  void test_doOnePlacement() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(3, 3, "C0H\n", bytes);

    String temp = "  0|1|2\n" +
      "A  | |  A\n" +
      "B  | |  B\n" +
      "C d|d|d C\n" +
      "  0|1|2";

    String expected = "Player A where do you want to place a Destroyer?\n" + temp + '\n';  
    V2ShipFactory sf = new V2ShipFactory(); 
    player.doOnePlacement("Destroyer", (p)->sf.makeDestroyer(p));
    assertEquals(expected,bytes.toString());
  }
}

