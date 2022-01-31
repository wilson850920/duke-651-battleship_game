package edu.duke.wh162.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class TextPlayer {
  private final Board<Character> theBoard;
  private final BoardTextView view;
  private final BufferedReader inputReader;
  private final PrintStream out;
  private final AbstractShipFactory<Character> shipFactory;
  private final String name;


  /**
   * This constrcutor will initialize all the variables
   */
  public TextPlayer(String name, Board<Character> theBoard, BufferedReader input, PrintStream out, V1ShipFactory sf) {
    this.name = name;
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = input;
    this.out = out;
    this.shipFactory = sf;
  }
  
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    return new Placement(s);
  }

  public void doOnePlacement() throws IOException {
    //Placement p = readPlacement("Player"  + name + " where would you like to put your ship");
    Placement p = readPlacement("Player " + name + " where do you want to place a Destroyer?");
    Ship<Character> s = shipFactory.makeDestroyer(p);
    theBoard.tryAddShip(s);
    out.println(view.displayMyOwnBoard());
  }

  public void doPlacementPhase() throws IOException{
    out.println(view.displayMyOwnBoard());
    String prompt = "Player " + name + ":" + " you are going to place the following ships (which are all rectangular). For each ship, type the coordinate of the upper left side of the ship, followed by either H (for horizontal) or V (for vertical).  For example M4H would place a ship horizontally starting at M4 and going to the right.  You have\n\n"
      + "2 \"Submarines\" ships that are 1x2\n" + "3 \"Destroyers\" that are 1x3\n"
      + "3 \"Battleships\" that are 1x4\n" + "2 \"Carriers\" that are 1x6\n";

    out.println(prompt);

    doOnePlacement();
    
  }
    
}
