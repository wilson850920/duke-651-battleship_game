package edu.duke.wh162.battleship;

public interface Board<T> {
  public int getWidth();
  public int getHeight();
  public String tryAddShip(Ship<T> toAdd);
  public T whatIsAtForSelf(Coordinate where);
  public Ship<T> fireAt(Coordinate c);
  public T whatIsAtForEnemy(Coordinate where);
  public void scan_area(Coordinate c, Board<Character> enemyBoard);
  public String getShipName(Coordinate c);
  public boolean checkshipshinkall();
}
