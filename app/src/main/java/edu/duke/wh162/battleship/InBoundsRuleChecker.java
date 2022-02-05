package edu.duke.wh162.battleship;

/** 
 * this class will check whether the ship is in bound
 */
public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  /**
   * a constructor that takes in constructs base on its parant constructor
   */
  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  /** 
   * the string body of the out of bound error message
   */
  public String inboundmsg(String str) {
    String err = "----------------------------------------------------------/n"
      + "Your input placement is invalid: the ship exceeds the " + str + " of the board.\n"
      + "----------------------------------------------------------\n";
    return err;
  }
  
  /**
   * check whether the ship is in the bound of the board
   * @param theShip is the ship
   * @param theBoard is the desired board
   */
  @Override
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {

    int columnbound = theBoard.getWidth();
    int rowbound = theBoard.getHeight();

    for (Coordinate c : theShip.getCoordinates()){
      if (c.getRow() < 0) {
        return inboundmsg("top");
      }
      if (c.getColumn() < 0) {
        return inboundmsg("left");
      }
      if (c.getRow() >= rowbound) {
        return inboundmsg("bottom");
      }
      if (c.getColumn() >= columnbound) {
        return inboundmsg("right");
      }
    }
    return null;
  }
}
