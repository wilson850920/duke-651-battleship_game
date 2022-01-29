package edu.duke.wh162.battleship;

import java.util.HashMap;

//public class BasicShip implements Ship<Character> {
public abstract class BasicShip<T> implements Ship<T> {

  //private final Coordinate myLocation;
  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;

  /**
  public BasicShip(Coordinate c) {
    //public BasicShip(Coordinate where) {
    //this.myLocation = where;

    myPieces = new HashMap<Coordinate, Boolean>();
    myPieces.put(c, false);
  }
  */
  
  /**
   * a constructot which initialize myPieces to have each 
   * Coordinate in where mapped to false. 
   */
  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo) {
    this.myPieces = new HashMap<Coordinate, Boolean>();
    this.myDisplayInfo = myDisplayInfo;
    for (Coordinate c: where){
      myPieces.put(c, false);
    }
    //this.myDisplayInfo = myDisplayInfo;
  }

  /**
   * This represent where the ship is
   */
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    // TODO Auto-generated method stub
    
    //return where.equals(myLocation);
    
    if (myPieces.get(where) == null) {
      return false;
    }
    return true;
  }

  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    // TODO Auto-generated method stub
    // look up the hit status of this coordinate
    return myDisplayInfo.getInfo(where, false);
    //return null;
  }
  

}
