package edu.duke.wh162.battleship;

public interface Board<T> {
  public int getWidth();
  public int getHeight();
  //public boolean tryAddShip(Ship<T> toAdd);
  public String tryAddShip(Ship<T> toAdd);
  public T whatIsAtForSelf(Coordinate where);
  public Ship<T> fireAt(Coordinate c);
  public T whatIsAtForEnemy(Coordinate where);
}
