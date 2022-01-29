package edu.duke.wh162.battleship;

/**
 * @program : battleship
 * @description: Interface of shipDisplay
 **/
public interface ShipDisplayInfo<T> {
  public T getInfo(Coordinate where, boolean hit);
}
