package edu.duke.wh162.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

public class TextPlayer {
  private final Board<Character> theBoard;
  private final BoardTextView view;
  private final BufferedReader inputReader;
  private final PrintStream out;
  private final AbstractShipFactory<Character> shipFactory;
  private final String name;
  final  ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;

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
    this.shipsToPlace = new ArrayList<String>();
    this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
    //setupShipCreationMap();
    //setupShipCreationList();
  }

  protected void setupShipCreationMap(){
    shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
    shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
    shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
    shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p)); 
  }

  protected void setupShipCreationList(){
    //System.out.print("peter");
    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
  }
  
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    if (s == null) {
      throw new IOException("Input something!");
    }
    /**
    try{
      while(s.length() != 3){
        out.println("\nThe input length should be 3, re-enter again:");
        s = null;
        s = inputReader.readLine();
      }
      HashSet<Character> orRec = new HashSet<Character>();
      orRec.add('h');
      orRec.add('H');
      orRec.add('V');
      orRec.add('v');
      while(orRec.contains(s.charAt(2)) == false){
        out.println("\nYour input orientation is valid, re-enter again:");
        s = null;
        s =  inputReader.readLine();
      }
      Coordinate c = new Coordinate(s.substring(0, 2));
    }catch(IllegalArgumentException e) {
      out.println("\nYour input orientation is not valid, re-enter again:");
      s = null;
      s =  inputReader.readLine();
    }
    Placement p = new Placement(new Coordinate(s.substring(0, 2)),s.charAt(2));
    return p;
    */
    //PlacementChecker pc = new PlacementChecker(s);
    //pc.formatCheck("Carriers", "V1");
    return new Placement(s);

    /**
    try {
      while (s.length() != 3) {
        out.println("\nDon't you know that you can only input a length of three??");
      x  s = null;
        s = inputReader.readLine();
      }
    }

    catch (IllegalArgumentException e){
      out.println("\nOMG! Don't you know that you can only input a length of three??");
      s = null;
      s = inputReader.readLine();
    }
    */
    //  System.out.print("hello");
    //}
    //return new Placement(s);
  }

  //public void doOnePlacement() throws IOException {
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    //Placement p = readPlacement("Player"  + name + " where would you like to put your ship");
    Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
    //Ship<Character> s = shipFactory.makeDestroyer(p);
    Ship<Character> s = createFn.apply(p);
    theBoard.tryAddShip(s);
    out.print(view.displayMyOwnBoard());
  }

  public void doPlacementPhase() throws IOException{
    out.println(view.displayMyOwnBoard());
    //System.out.print("A");
    String prompt = "Player " + name + ":" + " you are going to place the following ships (which are all rectangular). For each ship, type the coordinate of the upper left side of the ship, followed by either H (for horizontal) or V (for vertical).  For example M4H would place a ship horizontally starting at M4 and going to the right.  You have\n\n"
    + "2 \"Submarines\" ships that are 1x2\n" + "3 \"Destroyers\" that are 1x3\n"
     + "3 \"Battleships\" that are 1x4\n" + "2 \"Carriers\" that are 1x6\n";

    out.println(prompt);
    //doOnePlacement();
    setupShipCreationMap();
    //System.out.print("B");
    setupShipCreationList();
    //System.out.print("C");
    //System.out.print(shipsToPlace.size());
    for (String s : shipsToPlace) {
      //System.out.print(s);
      doOnePlacement(s, shipCreationFns.get(s));
    }
    //System.out.print("D");
  }
    
}
