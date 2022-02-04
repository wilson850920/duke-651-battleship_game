package edu.duke.wh162.battleship;

/**
 * @program  battleship
 * @description This class is for checking collision in ship placement. This class have has-a and is-a relationship.
 */
public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {

  /**
   * Constructor for NoCollisionRuleChecker,using super to constructed by the passed PlacementRuleChecker<T>.
   * @Param       PlacementRuleChecker<T>
   */ 
  public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  /**
   * check if the implements ship will collide with the ship that's already exsist
   * @param theShip the ship to place and check
   * @param theBoard the board for ship to place
   * return true if the ship can be placed correctly
   */
  @Override
  public String checkMyRule (Ship<T> theShip, Board<T> theBoard) { 
    Iterable<Coordinate> iter = theShip.getCoordinates();
    for (Coordinate c: iter) {
      if (theBoard.whatIsAtForSelf(c) != null) {
        String r = "----------------------------------------------------------\n"
          + "Your input placement is invalid: the coordinate you typed in overlaps with one of the existing ships.\n"
          + "----------------------------------------------------------\n";
        // return "Your input placement is invalid: the coordinate you typed in overlaps with one of the existing ships.\n";
        return r;
      }
    }
    return null;
  }
}
