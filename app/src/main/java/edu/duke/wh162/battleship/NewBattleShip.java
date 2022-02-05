package edu.duke.wh162.battleship;

import java.util.HashSet;

public class NewBattleShip<T> extends BasicShip<T> {
  private final String name;

  /**
   * This function returns the name of the new battle ship
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Constructs a new battleship
   * @param upperLeft the upperLeft corner of the ship
   * @param orient: the orient of a giving coordinate
   * @param ShipDisplayInfo is the ship info(hit or not)
   */
  public NewBattleShip(String name, Coordinate upperLeft, Character orient, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoords(upperLeft, orient), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
  }

  /**
   * Constructs a battleship ship.
   * @param upperLeft is the upperLeft coordinate of the battleship
   * @param orient: the orient of a giving coordinate
   * @param data      is one of the ship info
   * @param onHit     is one of the ship info
   */
  public NewBattleShip(String name, Coordinate upperLeft, Character orient, T data, T onHit) {
    this(name, upperLeft, orient, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }

  /**
   *This functions will create a hashset of coordinates representing a rectangle.
   * 
   * @param upperLeft: upperLeft coordinate of the rectangle
   * @param orient: the orient of a giving coordinate
   * @return added result hash set
   */
  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, Character orient) {
    HashSet<Coordinate> coordinate_hash = new HashSet<Coordinate>();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    
    if (orient == 'U' || orient == 'u') {
      coordinate_hash.add(new Coordinate(row, column + 1));
      for (int i = 0; i < 3; i ++) {
        coordinate_hash.add(new Coordinate(row + 1, column + i));
      }
    }
    else if (orient == 'R' || orient == 'r') {
      coordinate_hash.add(new Coordinate(row + 1, column + 1));
      for (int i = 0; i < 3; i ++) {
        coordinate_hash.add(new Coordinate(row + i, column));
      }
     }
    else if (orient == 'L' || orient == 'l') {
      coordinate_hash.add(new Coordinate(row + 1, column));
      for (int i = 0; i < 3; i ++) {
        coordinate_hash.add(new Coordinate(row + i, column + 1));
      }
    }
    else {
      coordinate_hash.add(new Coordinate(row + 1, column + 1));
      for (int i = 0; i < 3; i ++) {
        coordinate_hash.add(new Coordinate(row, column + i));
      }
    }
    return coordinate_hash;
  }
}
