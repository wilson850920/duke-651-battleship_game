package edu.duke.wh162.battleship;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Constructs a BattleShipBoard with the specified width and height
 * @param w is the width of the newly constructed board.
 * @param h is the height of the newly constructed board.
 * @throws IllegalArgumentException if the width or height are less than or equal to zero.
 */
public class BattleShipBoard<T> implements Board<T> {
  private final int width;
  private final int height;
  final ArrayList<Ship<T>> myShips;
  private final PlacementRuleChecker<T> placementChecker;
  private HashSet<Coordinate> enemyMisses;
  final T missInfo;

  /**
   * by a giving coordinate, return the corresonding ship name
   * if nothing were found, return null
   */
  public String getShipName(Coordinate c) {
    for (Ship<T> s: myShips) {
      if (s.occupiesCoordinates(c)) {
        return s.getName();
      }
    }
    return null;
  }

  /**
   * return the width of the board
   */
  public int getWidth(){
    return width;
   }
  
  /**
   * return the height of the board
   */
  public int getHeight(){
    return height;
  }

  /**
   * make the board with the desire input and output
   * @param w is the width
   * @param h in the height
   * throw exception when the input coordinates are wrong or valiate the rules
   */
  public BattleShipBoard(int w, int h, T missInfo){
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.missInfo = missInfo;
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
    this.placementChecker = new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<T>(null));
    this.enemyMisses = new HashSet<Coordinate>();
  }

  /**
   * Try add ship to check if valid
   * @param toAdd is the ship ready to be added to the board
   * before adding the ship, we do some checking first to make sure this adding works properly
   */
  public String tryAddShip(Ship<T> toAdd){
    if (placementChecker.checkPlacement(toAdd, this) != null){
      return placementChecker.checkPlacement(toAdd, this);
    }
    this.myShips.add(toAdd);  
    return null;
  }

  /**
   * check if there's something at the coordinate want to add
   */
  public T whatIsAtForSelf(Coordinate where) {
    return whatIsAt(where, true);
  }

  /**
   * by any giving coordinate
   * return the ship occupied at this point
   * in this game, it could be 's', 'd', 'b' 'c', 'X', '*', or null
   */
  protected T whatIsAt(Coordinate where, boolean isSelf) {
    if (where.getColumn() > width - 1 || where.getRow() > height - 1) {
      throw new IllegalArgumentException("the coordinate of this ship exceeds the board size");
    }
    
    for (Ship<T> s: myShips) {
      if (s.occupiesCoordinates(where)) {
        return s.getDisplayInfoAt(where, isSelf);
      }
    }
    if(isSelf == false && enemyMisses.contains(where)){
      return missInfo;
    }
    return null;
  }

  /**
   * return the information of a giving coordinate
   */
  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }
  
  /**
   * attack at the given coodinate and record it
   */
  public Ship<T> fireAt(Coordinate c) {
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(c)) {
        s.recordHitAt(c);
        return s;
      }
    }
    enemyMisses.add(c);
    return null;
  }

  /**
   * by centering the giving coordinate
   * scan the board surrounding it
   * return the numbers of each ship
   */
  public void scan_area(Coordinate c, Board<Character> enemyBoard) {
    int row = c.getRow();
    int column = c.getColumn();
    int[] arr = {0, 0, 0, 0};
    PrintStream out = System.out;
    for (int i = row - 3; i < row + 4; i ++) {
      for (int k = column - 3; k < column + 4; k ++) {
        int tot = Math.abs(row - i) + Math.abs(column - k); 
        if (tot <= 3) {
          Coordinate check = new Coordinate(i, k);
          if (enemyBoard.getShipName(check) == "Submarine"){ 
            arr[0] ++;
          }
          else if (enemyBoard.getShipName(check) == "Destroyer"){
            arr[1] ++;
          }
          else if (enemyBoard.getShipName(check) == "Battleship"){
            arr[2] ++;
          }
          else if (enemyBoard.getShipName(check) == "Carrier"){
            arr[3] ++;
          }
          else {
          }
        }
      }
    }
    String scanresult = "----------------------------------------------------------\n"
      + "Submarines occupy " + arr[0] + "squares\n"
      + "Destroyers occupy " + arr[1] + "squares\n"
      + "BattleShips occupy " + arr[2] + "squares\n"
      + "Carriers occupy " + arr[3] + "squares\n"
      + "----------------------------------------------------------\n";
    out.println(scanresult);
  }
  
  /**
   * check if all the ship for a player was all been hitted
   */
  public boolean checkshipshinkall() {
    for (Ship<T> s: myShips) {
      if (s.isSunk() == false) {
        return false;
      }
    }
    return true;
  }
}
