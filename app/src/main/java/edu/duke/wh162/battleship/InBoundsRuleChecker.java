package edu.duke.wh162.battleship;

/** 
 * this class will check whether the ship is in bound
 */
public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  /**
   * check whether the ship is in the bound of the board
   * @param theShip is the ship
   * @param theBoard is the desired board
   */

  public String inboundmsg(String str) {
    String err = "----------------------------------------------------------/n"
      + "Your input placement is invalid: the ship exceeds the " + str + " of the board.\n"
      + "----------------------------------------------------------\n";
    return err;
  }
  
  @Override
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {

    int columnbound = theBoard.getWidth();
    int rowbound = theBoard.getHeight();

    for (Coordinate c : theShip.getCoordinates()){
      if (c.getRow() < 0) {
        
        //return "Your input placement is invalid: the ship exceeds the top of the board.\n";
        return inboundmsg("top");
      }
      if (c.getColumn() < 0) {
        //return "Your input placement is invalid: the ship exceeds the left of the board.\n";
        return inboundmsg("left");
      }
      if (c.getRow() >= rowbound) {
        //return "Your input placement is invalid: the ship exceeds the bottom of the board.\n";
        return inboundmsg("bottom");
      }
      if (c.getColumn() >= columnbound) {
        //return "Your input placement is invalid: the ship exceeds the right of the board.\n";
        return inboundmsg("right");
      }
    }
    return null;
  }
}
