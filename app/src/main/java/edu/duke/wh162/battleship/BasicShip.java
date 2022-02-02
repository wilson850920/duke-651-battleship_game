package edu.duke.wh162.battleship;

import java.util.HashMap;
import java.util.Map;

/**
 * this class is for all the functions that ship would need
 */
public abstract class BasicShip<T> implements Ship<T> {

  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;
  protected ShipDisplayInfo<T> enemyDisplayInfo;

  /**
   * check if c is part of this ship (in myPieces)
   */
  protected void checkCoordinateInThisShip(Coordinate c) {
    int count = 0;
    if (myPieces.containsKey(c)) {
       count += 1;
    }                        
    if (count == 0) {
      throw new IllegalArgumentException("the given coordinate is not in the ship!" + c);
    }
  }

  /**
   * a constructot which initialize myPieces to have each 
   * Coordinate in where mapped to false. 
   */
  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    this.myPieces = new HashMap<Coordinate, Boolean>();
    this.myDisplayInfo = myDisplayInfo;
    this.enemyDisplayInfo = enemyDisplayInfo;
    for (Coordinate c: where){
      myPieces.put(c, false);
    }
  }

  /**
   * This represent where the ship is
   */
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
   if (myPieces.get(where) == null) {
      return false;
    }
    return true;
  }

  /**
   * check if all the coordinate of a ship was all been hit
   * if yes, then return true
   * if not, then return false
   */
  @Override
  public boolean isSunk() {
    for (Map.Entry<Coordinate, Boolean> temp: myPieces.entrySet()) {
      if (!temp.getValue()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Among being hit 
   * record the coordinate that was hitted
   */
  @Override
  public void recordHitAt(Coordinate where) {
    checkCoordinateInThisShip(where);
    myPieces.put(where, true);
  }

  /**
   * check where was this ship previously been hitted
   * return the hitted value
   */
  @Override
  public boolean wasHitAt(Coordinate where) {
    checkCoordinateInThisShip(where);
    return myPieces.get(where);
  }

  /** 
   * return a specific information of the ship
   * @param where is the coordinate to return information for
   * @throws IllegalArgumentException if where is not part of the Ship
   * @return The view-specific information at that coordinate.
   */
  @Override
  public T getDisplayInfoAt(Coordinate where, boolean myShip) {
    checkCoordinateInThisShip(where);
    if (myShip == true) {
      return myDisplayInfo.getInfo(where, myPieces.get(where));
    }
    return  enemyDisplayInfo.getInfo(where, myPieces.get(where));

  }

  /**
   * get evey coordinate that this ship occupies
   */
  @Override
  public Iterable<Coordinate> getCoordinates(){
    return myPieces.keySet();
  }
}
