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
  protected abstract boolean checkMyRule(Ship<T> theShip, Board<T> theBoard);

  public boolean checkPlacement (Ship<T> theShip, Board<T> theBoard) {
    //if we fail our own rule: stop the placement is not legal
    if (!checkMyRule(theShip, theBoard)) {
      return false;
    }
    //other wise, ask the rest of the chain.
    if (next != null) {
      return next.checkPlacement(theShip, theBoard);
    }
    //if there are no more rules, then the placement is legal
    return true;
  }
  
}
