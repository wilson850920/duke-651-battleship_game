package edu.duke.wh162.battleship;

//import edu.duke.wh162.battleship.interfaces.ShipDisplayInfo;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {

  private final T myData;
  private final T onHit;
   
  public SimpleShipDisplayInfo(T myData, T onHit) {
    this.myData = myData;
    this.onHit = onHit;
  }
  
  @Override
  public T getInfo(Coordinate where, boolean hit) {
    if (hit) {
      return onHit;
    }
    return myData;
  }
}

