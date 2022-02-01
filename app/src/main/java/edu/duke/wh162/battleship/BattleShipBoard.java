package edu.duke.wh162.battleship;

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
  
  public int getWidth(){
    return width;
   }
  
  public int getHeight(){
    return height;
  }

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
   */
  public String tryAddShip(Ship<T> toAdd){
    if (placementChecker.checkPlacement(toAdd, this) != null){
      //this.myShips.add(toAdd);
      return placementChecker.checkPlacement(toAdd, this);
      //return false;
    }
    this.myShips.add(toAdd);  
    return null;
  }

  /**
   * check if there's something at the coordinate want to add
   */
  //public T whatIsAtForSelf(Coordinate where) {
  public T whatIsAtForSelf(Coordinate where) {
    // check if the added ship exceeds the board limit
    //if (where.getColumn() > width - 1 || where.getRow() > height - 1) {
    //  throw new IllegalArgumentException("the coordinate of this ship exceeds the board size");
    //}
    //
    //for (Ship<T> s: myShips) {
    //  if (s.occupiesCoordinates(where)) {
    //    return s.getDisplayInfoAt(where);
    //  }
    //}
    //return null;
    return whatIsAt(where, true);
  }

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

  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }
  
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

  public boolean checkshipshinkall() {
    for (Ship<T> s: myShips) {
      if (s.isSunk() == false) {
        return false;
      }
    }
    return true;
  }
}
