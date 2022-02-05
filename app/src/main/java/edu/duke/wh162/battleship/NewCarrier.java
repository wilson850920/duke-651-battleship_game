package edu.duke.wh162.battleship;

import java.util.HashSet;

public class NewCarrier<T> extends BasicShip<T> {
  private final String name;

  /**
   * This function returns the name of the new carrier ship
   */
  public String getName() {
    return name;
  }

  /**
   * Constructs a new Carrier
   * @param upperLeft the upperLeft corner of the ship
   * @param orient the orientation of the Carrier
   * @param ShipDisplayInfo is the ship info(hit or not)
   */
  public NewCarrier(String name, Coordinate upperLeft, Character orient, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoords(upperLeft, orient), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
  }

  /**
   * Constructs a rectangle ship.
   * @param upperLeft is the upperLeft coordinate of the rectangle
   * @param orient the orientation of the Carrier
   * @param data      is one of the ship info
   * @param onHit     is one of the ship info
   */
  public NewCarrier(String name, Coordinate upperLeft, Character orient, T data, T onHit) {
    this(name, upperLeft, orient, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }

 /**
   *This functions will create a hashset of coordinates representing a rectangle.
   * @param orient the orientation of the Carrier
   * @param upperLeft: upperLeft coordinate of the rectangle
   * @return added result hash set
   */
  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, Character orient) {
    HashSet<Coordinate> coordinate_hash = new HashSet<Coordinate>();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    
    if (orient == 'U' || orient == 'u') {
      for (int i = 0; i < 4; i ++) {
        coordinate_hash.add(new Coordinate(row + i, column));
      }
      for (int k = 0; k < 3; k ++){
        coordinate_hash.add(new Coordinate(row + 2 + k, column + 1));
      }
    }
    else if (orient == 'R' || orient == 'r') {
      for (int i = 0; i < 4; i ++) {
        coordinate_hash.add(new Coordinate(row, column + 1 + i));
      }
      for (int k = 0; k < 3; k ++) {
        coordinate_hash.add(new Coordinate(row + 1, column + k));
      }
     }
    else if (orient == 'L' || orient == 'l') {
      for (int i = 0; i < 4; i ++) {
        coordinate_hash.add(new Coordinate(row + 1, column + i));
      }
      for (int k = 0; k < 3; k ++) {
        coordinate_hash.add(new Coordinate(row, column + 2 + k));
      }
    }
    else{
      for (int i = 0; i < 3; i ++) {
        coordinate_hash.add(new Coordinate(row + i, column));
      }
      for (int k = 0; k < 4; k ++) {
        coordinate_hash.add(new Coordinate(row + 1 + k, column + 1));
      }
    }
    return coordinate_hash;
  }
}
