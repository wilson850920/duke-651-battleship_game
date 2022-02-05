package edu.duke.wh162.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
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
  private final int flag;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
  private final HashMap<Character, String> shipContainer;

  /**
   * This constrcutor will initialize all the variables
   */
  public TextPlayer(String name, Board<Character> theBoard, BufferedReader input, PrintStream out, V2ShipFactory sf, int flag) {
    this.name = name;
    this.flag = flag;
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
    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
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
   * create a list of placement for computer's coordinate
   * add them to the board
   */
  public void computership() {
    V2ShipFactory sf = new V2ShipFactory();
    Placement p1 = new Placement(new Coordinate(0, 0), 'v');
    Placement p2 = new Placement(new Coordinate(0, 1), 'v');
    Placement p3 = new Placement(new Coordinate(0, 2), 'v');
    Placement p4 = new Placement(new Coordinate(0, 3), 'v');
    Placement p5 = new Placement(new Coordinate(0, 4), 'v');
    Placement p6 = new Placement(new Coordinate(0, 5), 'r');
    Placement p7 = new Placement(new Coordinate(0, 7), 'r');
    Placement p8 = new Placement(new Coordinate(3, 0), 'r');
    Placement p9 = new Placement(new Coordinate(3, 2), 'r');
    Placement p10 = new Placement(new Coordinate(4, 4), 'r');
    Ship<Character> s1 = sf.makeSubmarine(p1);
    Ship<Character> s2 = sf.makeSubmarine(p2);
    Ship<Character> s3 = sf.makeDestroyer(p3);
    Ship<Character> s4 = sf.makeDestroyer(p4);
    Ship<Character> s5 = sf.makeDestroyer(p5);
    Ship<Character> s6 = sf.makeBattleship(p6);
    Ship<Character> s7 = sf.makeBattleship(p7);
    Ship<Character> s8 = sf.makeBattleship(p8);
    Ship<Character> s9 = sf.makeCarrier(p9);
    Ship<Character> s10 = sf.makeCarrier(p10);
    
    theBoard.tryAddShip(s1);
    theBoard.tryAddShip(s2);
    theBoard.tryAddShip(s3);
    theBoard.tryAddShip(s4);
    theBoard.tryAddShip(s5);
    theBoard.tryAddShip(s6);
    theBoard.tryAddShip(s7);
    theBoard.tryAddShip(s8);
    theBoard.tryAddShip(s9);
    theBoard.tryAddShip(s10);
  }
  
  /**
   * This does one placement of ship on board and print board to screen
   * @throws IOExceptionif readLine() returns null
   */
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn, int flag) throws IOException {
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
  public void doPlacementPhase(int flag) throws IOException{
    if (flag == 0){
      out.println(view.displayMyOwnBoard());
      String prompt = "Player " + name + ":" + " you are going to place the following ships (which are all rectangular). For each ship, type the coordinate of the upper left side of the ship, followed by either H (for horizontal) or V (for vertical).  For example M4H would place a ship horizontally starting at M4 and going to the right.  You have\n\n"
        + "2 \"Submarines\" ships that are 1x2\n" + "3 \"Destroyers\" that are 1x3\n"
        + "3 \"Battleships\" that are 1x4\n" + "2 \"Carriers\" that are 1x6\n";

      out.println(prompt);
    }
    setupShipCreationMap();
    setupShipCreationList();
    if (flag == 0) {
      for (String s : shipsToPlace) {
        doOnePlacement(s, shipCreationFns.get(s), flag);
      }
    }
    else {
      computership();
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
  public void checkAttachShipInfo(Board<Character> enemyBoard, Coordinate where, int flag, String name) {
    Character which = enemyBoard.whatIsAtForSelf(where);
    Ship<Character> fireship = enemyBoard.fireAt(where);
    int row = where.getRow();
    int column = where.getColumn();
    if (flag == 0) {
      if (fireship == null) {
        out.println("Haha, you didn't hit anything~");
      }
      else{
        String shipName = shipContainer.get(which);
        out.println("You hit a " + shipName + "!");
      }
    }
    else {
      if (fireship != null) {
        String shipName = shipContainer.get(which);
        out.println(name + " attacked you at " + row + ":" + column + " !\n");
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
    checkAttachShipInfo(enemyBoard, firewhere, 0, name);
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
   * switch between each player to attack the enemy's board
   */
  public void playOneTurn(Board<Character> enemyBoard, String enemyName, int flag) throws IOException {
    if (flag == 0) {
      String action = null;
      //char action = ' ';
      String startaction = "----------------------------------------------------------\n" + "Player " + name + ", it's your turn, please choose an action!\n" +
        "\n F Fire at a square" + "\n M Move a ship tp another square (2 remaining)" +
        "\n S Sonar scan (1 remaining)\n" + "\nPlayer " + name + ", what would you like to do?\n" + "----------------------------------------------------------\n";
      out.println(startaction);
      
      String enemyHead = "Player " + enemyName + "'s ocean";
      BoardTextView viewenemyBoard = new BoardTextView(enemyBoard);
      out.println(view.displayMyBoardWithEnemyNextToIt(viewenemyBoard, "Your ocean", enemyHead));
      
      boolean action_status = false;
      while (action_status == false) {
        action = inputReader.readLine();
        //action = action0.charAt(0);
        //action = Character.toUpperCase(action);
        if (action.equals("F") || action.equals("M") || action.equals("S") || action.equals("f") || action.equals("s") || action.equals("m")) {
          action_status = true;
        }
        else {
          out.println("Please enter letter F, M, or S.\n");
        }
      }
   
      if (action.equals("F") || action.equals("f")) {
        fireaction(enemyBoard, enemyName);
      }
      if (action.equals("S") || action.equals("s")) {
        scanHere(enemyBoard);
      }
      if (action.equals("M") || action.equals("m")) {
        out.println("Move action is coming up, please wait\n");
      }
    }
    else {
      Random rand = new Random();
      int column = rand.nextInt(enemyBoard.getWidth());
      int row = rand.nextInt(enemyBoard.getHeight());
      Coordinate where = new Coordinate(row, column);
      checkAttachShipInfo(enemyBoard, where, 1, name);
    }
  }
}
