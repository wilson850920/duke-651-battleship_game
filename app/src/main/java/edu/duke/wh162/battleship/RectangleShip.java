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
   * this is the constructor for rectangleship
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoords(upperLeft, width, height), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
    //this(upperLeft, 1, 1, new SimpleShipDisplayInfo<T>(data, onHit));
  }

  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }

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
    //coordinate_hash.push_back(upperLeft);
    //coordinate_hash.add(upperLeft);
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    for (int k = row; k < row + height; k ++) {
      for (int i = column; i < column + width; i ++) {
        Coordinate add_coor = new Coordinate(k, i);
        coordinate_hash.add(add_coor);
        //System.out.print(coordinate_hash);
      }
    }
    return coordinate_hash;
  }
  
}
