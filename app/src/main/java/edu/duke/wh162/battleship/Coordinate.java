package edu.duke.wh162.battleship;

/**
 * This class is the coordinate to place ship. It supports two ways to construct
 * the coordinate one is by int input, the other one is by string input. It has
 * two functions: one is to generate hash code for coordinate, one is to compare
 * two objects are equal coordinate.
 */
public class Coordinate {
  private final int row;
  private final int column;

  public Coordinate(int row, int column){
    this.row = row;
    this.column = column;
  }

  /**
   * constuct a coordinate by a giving string
   */
  public Coordinate(String descr) {
    if (descr.length() == 0) {
      throw new IllegalArgumentException("Please enter something\n");
    }
    
    if (descr.length() != 2 && descr.length() != 0) {
      throw new IllegalArgumentException("The input coordinate must have at least two letters\n");
    }

    char alph = descr.charAt(0);
    if (alph >= 'a' && alph <= 'z') {
      alph = Character.toUpperCase(alph);
    }
    else if (alph < 'A' || alph > 'Z') {
      throw new IllegalArgumentException("Please enter a char between A to Z, you entered " + alph);
    }

    this.row = (int)(alph - 'A');
    char num = descr.charAt(1);
    if (num < '0' || num > '9') {
      throw new IllegalArgumentException("Plese enteer a char between 0 to 9, you entered " + num);
    }
    else {
      int number = (int)(num - '0');
      this.column = number;
    }
  }

  /**
   * check if the input is in the boundary of the board
   */
  public boolean validBoundary(int w, int h){
    if (row>=h || column>=w) {
      return false;
    }
    return true;
  }
  
  /**
   * return the row
   */
  public int getRow(){
    return row;
  }

  /**
   * return the column
   */
  public int getColumn(){
    return column;
  }

  /**
   * check if the desired object has the same coordinate
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())){
        Coordinate c = (Coordinate) o;
        return row == c.row && column == c.column;
      }
      return false;
  }

  /**
   * concate and return the string of the row and the column
   */
  @Override
  public String toString() {
    return "(" + row + ", " + column + ")";
  }

  /**
   * convert the string into hashcode
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
