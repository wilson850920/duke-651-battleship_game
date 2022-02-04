package edu.duke.wh162.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

/**
 * This class will state every function
 * that a single player should do
 */
public class TextPlayer {
  public final Board<Character> theBoard;
  private final BoardTextView view;
  private final BufferedReader inputReader;
  private final PrintStream out;
  private final AbstractShipFactory<Character> shipFactory;
  private final String name;
  final  ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
  private final HashMap<Character, String> shipContainer;

  /**
   * This constrcutor will initialize all the variables
   */
  public TextPlayer(String name, Board<Character> theBoard, BufferedReader input, PrintStream out, V2ShipFactory sf) {
    this.name = name;
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = input;
    this.out = out;
    this.shipFactory = sf;
    this.shipsToPlace = new ArrayList<String>();
    this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
    this.shipContainer = new HashMap<Character, String>();
  }

  /**
   * using lambda to create a ship function
   */
  protected void setupShipCreationMap(){
    shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
    shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
    shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
    shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p)); 
  }

  /**
   * create a list of ship that controls how many ship can a player make
   */
  protected void setupShipCreationList(){
    shipsToPlace.addAll(Collections.nCopies(1, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(1, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(1, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(1, "Carrier"));
  }
  
  /**
   * read the input from the player 
   * return the placement rule for it
   */
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    if (s == null) {
      throw new IOException("Input something!");
    }
    return new Placement(s);
  }

  /**
   * This does one placement of ship on board and print board to screen
   * @throws IOExceptionif readLine() returns null
   */
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    Boolean check_status = false;
    Placement p = null;
    while (check_status == false){
      try {
        String tmp;
        p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
        Ship<Character> s = createFn.apply(p);
        tmp = theBoard.tryAddShip(s);
        if (tmp != null) {
          throw new IllegalArgumentException(tmp);
        }         
        if (p != null && tmp == null) {
          check_status = true;
        }
      }
      catch (IllegalArgumentException illArg) {
	out.print(illArg.getMessage());
      }
    }
    out.print(view.displayMyOwnBoard());
  }

  /**
   * do the placement phase for the player
   */
  public void doPlacementPhase() throws IOException{
    out.println(view.displayMyOwnBoard());
    String prompt = "Player " + name + ":" + " you are going to place the following ships (which are all rectangular). For each ship, type the coordinate of the upper left side of the ship, followed by either H (for horizontal) or V (for vertical).  For example M4H would place a ship horizontally starting at M4 and going to the right.  You have\n\n"
    + "2 \"Submarines\" ships that are 1x2\n" + "3 \"Destroyers\" that are 1x3\n"
     + "3 \"Battleships\" that are 1x4\n" + "2 \"Carriers\" that are 1x6\n";

    out.println(prompt);
    setupShipCreationMap();
    setupShipCreationList();
    for (String s : shipsToPlace) {
      doOnePlacement(s, shipCreationFns.get(s));
    }
  }

  /**
   * check if all the players ship were sinked
   */
  public boolean checkLose() {
    return theBoard.checkshipshinkall();
  }

  /**
   * return the name of the player
   */
  public String getName() {
    return name;
  }

  /**
   * create a set of ships for further actions
   */
  protected void setupShipSet() {
    shipContainer.put('s', "submarine");
    shipContainer.put('d', "destroyer");
    shipContainer.put('b', "battleship");
    shipContainer.put('c', "carrier");
  }

  /**
   * check if the attack coordinate has any information to form the player
   */
  public void checkAttachShipInfo(Board<Character> enemyBoard, Coordinate where) {
    Character which = enemyBoard.whatIsAtForSelf(where);
    Ship<Character> fireship = enemyBoard.fireAt(where);
    if (fireship == null) {
      out.println("Haha, you didn't hit anything~");
    }
    else{
      if (which == '*') {
        out.println("You already hit this ship already!");
      }
      else {
        String shipName = shipContainer.get(which);
        out.println("You hit a " + shipName + "!");
      }
    }
  }

  /**
   * fire at the a giving coordinate
   */
  public void fireaction(Board<Character> enemyBoard, String enemyName) throws IOException {
    setupShipSet();
    String str = null;
    Boolean status = false;
    Coordinate firewhere = null;
    
    //String startgame = "\nPlayer " + name + "! Your turn to attack.";
    //out.println(startgame);

    //String enemyHead = "Player " + enemyName + "'s ocean";
    //BoardTextView viewenemyBoard = new BoardTextView(enemyBoard);
    //out.println(view.displayMyBoardWithEnemyNextToIt(viewenemyBoard, "Your ocean", enemyHead));
    while (status == false) {
      out.println("Where do you want to attack?");
      str = inputReader.readLine();
      try {
      	firewhere = new Coordinate(str);
      	status = firewhere.validBoundary(theBoard.getWidth(), theBoard.getHeight());
      	if (firewhere.validBoundary(theBoard.getWidth(), theBoard.getHeight()) == false) {
          out.println("The coordinate fells out of the board");
      	}
      }	
      catch (IllegalArgumentException illArg) {
      	out.print(illArg.getMessage());
      }	 
    }
    checkAttachShipInfo(enemyBoard, firewhere);
  }

  /**
   * ask the player to input a coordinate
   * for where to scan
   */
  public void scanHere(Board<Character> enemyBoard) throws IOException{
    String str = null;
    Boolean status = false;
    Coordinate scanwhere = null;
    while (status == false) {
      out.println("Where do you want to scan?");
      str = inputReader.readLine();
      try {
        scanwhere = new Coordinate(str);
        status = scanwhere.validBoundary(theBoard.getWidth(), theBoard.getHeight());
        if (scanwhere.validBoundary(theBoard.getWidth(), theBoard.getHeight()) == false) {
          out.println("The coordinate fells out of the board");
        }
      }
      catch (IllegalArgumentException illArg) {
        out.print(illArg.getMessage());
      }
    }
    enemyBoard.scan_area(scanwhere, enemyBoard);
  }

  /**
   * pick a coordinate
   * move the correspnding ship to 
   * a new giving coordinate
  public void movewhere() {
    String str = null;
    Boolean status = false;
    Coordinate pickship = null;
    Coordinate movewhere = null;
    while (status = false) {
  */      

    

  
  
  /**
   * switch between each player to attack the enemy's board
   */
  public void playOneTurn(Board<Character> enemyBoard, String enemyName) throws IOException {
    String action0 = null;
    char action = ' ';
    String startaction = "----------------------------------------------------------/n" + "Player " + name + ", it's your turn, please choose an action!\n" +
      "\n F Fire at a square" + "\n M Move a ship tp another square (2 remaining)" +
      "\n S Sonar scan (1 remaining)\n" + "\nPlayer " + name + ", what would you like to do?\n" + "----------------------------------------------------------/n";
    out.println(startaction);

    //String startgame = "\nPlayer " + name + "! Your turn to attack.";
    //out.println(startgame);

    String enemyHead = "Player " + enemyName + "'s ocean";
    BoardTextView viewenemyBoard = new BoardTextView(enemyBoard);
    out.println(view.displayMyBoardWithEnemyNextToIt(viewenemyBoard, "Your ocean", enemyHead));
    
    boolean action_status = false;
    while (action_status == false) {
      //Scanner scanner = new Scanner(System.in);
      //action = scanner.next().charAt(0); 

      action0 = inputReader.readLine();
      action = action0.charAt(0);
      action = Character.toUpperCase(action);
      if (action == 'F' || action == 'M' || action == 'S') {
        action_status = true;
      }
      else {
        out.println("Please enter letter F, M, or S.\n");
      }
    }
   
    if (action == 'F') {
      fireaction(enemyBoard, enemyName);
    }
    if (action == 'S') {
      scanHere(enemyBoard);
    }
    if (action == 'M') {
      out.println("Move action is coming up, please wait\n");
      // movewhere();
    }
      //enemyBoard.scan_area(null, enemyBoard);
      




    //setupShipSet();
    //String str = null;
    //Boolean status = false;
    //Coordinate firewhere = null;
    //
    //String startgame = "\nPlayer " + name + "! Your turn to attack.";
    //out.println(startgame);

    //String enemyHead = "Player " + enemyName + "'s ocean";
    //BoardTextView viewenemyBoard = new BoardTextView(enemyBoard);
    //out.println(view.displayMyBoardWithEnemyNextToIt(viewenemyBoard, "Your ocean", enemyHead));
    //while (status == false) {
    //  out.println("Where do you want to attack?");
    //  str = inputReader.readLine();
    //  try {
    //  	firewhere = new Coordinate(str);
    //  	status = firewhere.validBoundary(theBoard.getWidth(), theBoard.getHeight());
    //  	if (firewhere.validBoundary(theBoard.getWidth(), theBoard.getHeight()) == false) {
    //      out.println("The coordinate fells out of the board");
    //  	}
    //  }	
    //  catch (IllegalArgumentException illArg) {
    //  	out.print(illArg.getMessage());
    //  }	 
    //}
    //checkAttachShipInfo(enemyBoard, firewhere);
  }
}
