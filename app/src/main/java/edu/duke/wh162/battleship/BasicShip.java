package edu.duke.wh162.battleship;

import java.util.HashMap;
import java.util.Map;

//public class BasicShip implements Ship<Character> {
public abstract class BasicShip<T> implements Ship<T> {

  //private final Coordinate myLocation;
  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;

  /**
   * check if c is part of this ship (in myPieces)
   */
  protected void checkCoordinateInThisShip(Coordinate c) {
    int count = 0;
    //for (int i = 0; i < myPieces.keySet().size(); i ++) {
    if (myPieces.containsKey(c)) {
       count += 1;
    }                        
    //}
    if (count == 0) {
      throw new IllegalArgumentException("the given coordinate is not in the ship!" + c);
    }
  }
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
    for (Map.Entry<Coordinate, Boolean> temp: myPieces.entrySet()) {
      if (!temp.getValue()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    myPieces.put(where, true);
  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    return myPieces.get(where);
  }

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    // TODO Auto-generated method stub
    // look up the hit status of this coordinate
    checkCoordinateInThisShip(where);
    return myDisplayInfo.getInfo(where, wasHitAt(where));
    //return null;
  }
  

}
