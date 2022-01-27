package edu.duke.wh162.battleship;

public class Placement {

  private final Coordinate where;
  private final char orientation;

  public Placement(Coordinate where, char orientation) {
    this.where = where;
    this.orientation = orientation;
  }

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
  
  public Coordinate getWhere() {
    return where;
  }

  public char getOrient() {
    return orientation;
  }

  @Override
  public String toString() {
    return where.toString() + ", " + orientation;
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Placement p = (Placement) o;
      return where.equals(p.getWhere()) && orientation == p.getOrient();
        }
    return false;
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
