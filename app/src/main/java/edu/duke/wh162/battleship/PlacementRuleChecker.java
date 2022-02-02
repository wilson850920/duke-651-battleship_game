package edu.duke.wh162.battleship;

/**
 * @program : battleship
 * @description: Abstract Class of Placement Rule Checker,contains a  has-A and is-A checker
 **/
public abstract class PlacementRuleChecker<T> {
  private final PlacementRuleChecker<T> next;

  /**
   * Construct of the class
   */
  public PlacementRuleChecker(PlacementRuleChecker<T> next) {
    this.next = next;
  }

  /**
   * Check whether the desire ship can be placed on the passed board
   */
  protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);

  /**
   * check all the rules of placing a ship
   * if one is wrong, return its error message
   */
  public String checkPlacement (Ship<T> theShip, Board<T> theBoard) {	  
    if (checkMyRule(theShip, theBoard) != null){
      return checkMyRule(theShip, theBoard);
    }
    if (next != null) {
      return next.checkPlacement(theShip, theBoard);
    }
    return null;
  }
}
