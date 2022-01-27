package edu.duke.wh162.battleship;

 /**
 * This class is the ship placement infomation with Coordinate object where and char orientation
 * It supports two ways to construct placement: (Coordinate and char) or string
 * It has two functions: one is to generate hash code for coordinate, one is to compare
 * two objects are equal coordinate
 */

public class Placement {

   /**
   * The Coordinate where and orientation in coordinate.
   */  
  
  private final Coordinate where;
  private final char orientation;

  /**
  * @Description: Default constructor of Placement. Constructed by the passed coordinate and the passed orientation
  * @Param:       Coordinate coordinate, char orientation
  * @throws       IllegalArgumentException if the width or height is smaller than 0.
  * @return:
  */
  public Placement(Coordinate where, char orientation) {
    this.where = where;
    this.orientation = orientation;
  }

  /**
  * @Description: Constructor of Placement. Constructed by the string description like "A0V"
  * @Param:       String descr
  * @throws       IllegalArgumentException if the description's length is not 3 or orientation is not 'H'/'V'.
  * @return:
  */
  public Placement(String descr) {

    if (descr.length() != 3) {
      throw new IllegalArgumentException("The input string should have a length of three.");
    }

    char ori = descr.charAt(2);
    ori = Character.toUpperCase(ori);
    if (ori != 'V' && ori != 'H') {
      throw new IllegalArgumentException("The input orientation shoud be either 'v' or 'h'.");
    }

    this.orientation = ori;
    this.where = new Coordinate(descr.substring(0,2));

  }
  
  /**
   * Getter of where.
   */
  public Coordinate getWhere() {
    return where;
  }

  /**
   * Getter of orientation.
   */
  public char getOrient() {
    return orientation;
  }

  /**
  * @Description: Describe this Placement in String.
  * @Param:
  * @return:      String that describe this Placement
  */
  @Override
  public String toString() {
    return where.toString() + ", " + orientation;
  }

  /**
  * @Description: Check if the passed object is an Object of Placement, and check if it has same value with "this".
  * @Param:       Object o
  * @return:      Boolean that indicate if the passed Object is equal to "this".
  */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Placement p = (Placement) o;
      return where.equals(p.getWhere()) && orientation == p.getOrient();
        }
    return false;
  }

  /**
  * @Description: Calculate the hashcode of this.toString()
  * @Param:
  * @return:      Int of hashcode
  */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
