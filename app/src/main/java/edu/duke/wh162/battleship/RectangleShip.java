package edu.duke.wh162.battleship;

import java.util.HashSet;

/**
 * This class represents any rectangle ships with upperleft coordinate
 */
public class RectangleShip<T> extends BasicShip<T>{

  private final String name;
  public String getName() {
    return name;
  }
  

  /**
   * Constructs a rectangle ship.
   * @param upperLeft       is the upperLeft coordinate of the rectangle
   * @param width           is the width(column) of rectangle
   * @param height          is the height(row) of rectangle
   * @param ShipDisplayInfo is the ship info(hit or not)
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoords(upperLeft, width, height), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
  }

  /**
   * Constructs a rectangle ship.
   * @param upperLeft is the upperLeft coordinate of the rectangle
   * @param width     is the width(column) of rectangle
   * @param height    is the height(row) of rectangle
   * @param data      is one of the ship info
   * @param onHit     is one of the ship info
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }

  /**
   * Constructs a rectangle ship.
   * @param upperLeft is the upperLeft coordinate of the rectangle
   * @param height    is the height(row) of rectangle
   * @param data      is one of the ship info
   * @param onHit     is one of the ship info
   */
  public RectangleShip(String name, Coordinate upperLeft, T data, T onHit) {
    this("testship", upperLeft, 1, 1, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }
  
  /**
   *This functions will create a hashset of coordinates representing a rectangle.
   * 
   * @param upperLeft: upperLeft coordinate of the rectangle
   * @param width: width(column) of rectangle
   * @param height: height(row) of rectangle
   * @return added result hash set
   */
  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
    HashSet<Coordinate> coordinate_hash = new HashSet<Coordinate>();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    for (int k = row; k < row + height; k ++) {
      for (int i = column; i < column + width; i ++) {
        Coordinate add_coor = new Coordinate(k, i);
        coordinate_hash.add(add_coor);
      }
    }
    return coordinate_hash;
  }
}
