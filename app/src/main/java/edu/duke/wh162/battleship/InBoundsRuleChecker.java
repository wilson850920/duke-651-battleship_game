package edu.duke.wh162.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  @Override
  //protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    // TODO Auto-generated method stub

    int columnbound = theBoard.getWidth();
    int rowbound = theBoard.getHeight();

    for (Coordinate c : theShip.getCoordinates()){
      //if (c.getRow() < 0 || c.getRow() >= rowbound || c.getColumn() < 0 || c.getColumn() >= columnbound) {
      //  return false;
      //}
      if (c.getRow() < 0) {
	return "Your input placement is invalid: the ship exceeds the top of the board.";
      }
      if (c.getColumn() < 0) {
	return "Your input placement is invalid: the ship exceeds the left of the board.";
      }
      if (c.getRow() >= rowbound) {
	return "Your input placement is invalid: the ship exceeds the bottom of the board.";
      }
      if (c.getColumn() >= columnbound) {
	return "Your input placement is invalid: the ship exceeds the right of the board.";
      }

    }
    //return true;
    return null;
  }

}
