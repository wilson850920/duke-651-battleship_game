package edu.duke.wh162.battleship;

public class Coordinate {
  private final int row;
  private final int column;

  public Coordinate(int row, int column){
    this.row = row;
    this.column = column;
  }

  public Coordinate(String descr) {
    if (descr.length() != 2) {
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
  
  public int getRow(){
    return row;
  }
  public int getColumn(){
    return column;
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())){
        Coordinate c = (Coordinate) o;
        return row == c.row && column == c.column;
      }
      return false;
  }

  @Override
  public String toString() {
    return "(" + row + ", " + column + ")";
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
