/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.wh162.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;

public class App {

  private  TextPlayer p1;
  private  TextPlayer p2;

  /**
   * Constructor for the two players
   */
  public App(TextPlayer p1, TextPlayer p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  public void doPlacementPhase() throws IOException{
    p1.doPlacementPhase();
    //System.out.print("fuc k");
    p2.doPlacementPhase();
    //p2.doPlacementPhase();
  }
  //private final Board<Character> theBoard;
  //private final BoardTextView view;
  //private final BufferedReader inputReader;
  //private final PrintStream out;
  //private final AbstractShipFactory<Character> shipFactory;
  //
  ///**
  // * A Constructor for the APP
  // */
  //public App(Board<Character> theBoard, Reader inputSource, PrintStream out) {
  //  this.theBoard = theBoard;
  //  this.view = new BoardTextView(theBoard);
  //  this.inputReader = new BufferedReader(inputSource);
  //  this.out = out;
  //  this.shipFactory = new V1ShipFactory();
  //}

  //public Placement readPlacement(String prompt) throws IOException {
  //  out.println(prompt);
  //  String s = inputReader.readLine();
  //  return new Placement(s);
  //}

  ///**
  // * create a doOnePlacement method which does
  // * read a PlXoacement
  // * Create a basic ship based on the location in that Placement
  // * Add that ship to the Board
  // * Print out the board (to out, not to System.out)
  // */
  //public void doOnePlacement() throws IOException{
  //  Placement p = readPlacement("Where would you like to put your ship?");
  //  //Ship<Character> s = new BasicShip(p.getWhere());
  //  //RectangleShip<Character> s = new RectangleShip<Character>("Submarine", p.getWhere(), 's', '*');
  //  Ship<Character> s  = shipFactory.makeDestroyer(p); 
  //  theBoard.tryAddShip(s);
  //  out.println(view.displayMyOwnBoard());
  //}

  public static void main(String[] args) throws IOException {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b2 = new BattleShipBoard<Character>(10, 20, 'X');
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(input);
    PrintStream out = System.out;
    V1ShipFactory sf = new V1ShipFactory();

    //if(args.length != 0){
    //  br = new BufferedReader(new StringReader(args[0]));
    // }

    //TextPlayer p2 = new TextPlayer("B", b2, br, out, sf);
    TextPlayer p1 = new TextPlayer("A", b1, br, out, sf);
    TextPlayer p2 = new TextPlayer("B", b2, br, out, sf);
    App a = new App(p1, p2);
    a.doPlacementPhase();
    /**
    try {
      a.doPlacementPhase();
    }
    catch(IOException e) {
      out.println("Error input, please input correct format.");
      a.doPlacementPhase();
    }
    */
    //Reader r  = new InputStreamReader(System.in);
    //App app = new App(b, r, System.out);
    //app.doOnePlacement();
  }
}
