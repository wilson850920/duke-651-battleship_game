package edu.duke.wh162.battleship;

/**
 * This class handles textual display of a Board (i.e., converting it to a string to show to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the enemy's board.
 */

public class BoardTextView {
  
  /** 
   * The Board to display
   */

  private final Board toDisplay;

  /** Constructs a BoardView, given the board it will display.
   * @param toDisplay is the Board to display
   * @throws IllegalArgumentException if the board is larger than 10x26
   */
  
  public BoardTextView(Board toDisplay){
    this.toDisplay = toDisplay;
    
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException("Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
  }

  /**
   * Display the board base on the width and height
   * for now it's just printing a blank board
   */
  
  public String displayMyOwnBoard() {
    
    StringBuilder res = new StringBuilder("");
    res.append(makeHeader());
    
    for (int r = 0; r <toDisplay.getHeight(); r ++) {
      char row = (char)(r + 'A');
      res.append(row + " ");
      for (int c = 0; c < toDisplay.getWidth(); c++) {
        if (c == toDisplay.getWidth() - 1) {
          res.append(" ");
        }
        else {
          res.append(" |");
        }
      }
      res.append(" " + row + '\n');
    }
    res.append(makeHeader());
    return res.toString(); 
  }
  
  /** 
   * This makes the header line, e.g. 0|1|2|3|4\n
   * 
   * @return the String that is the header line for the given board
   */

  String makeHeader() {
    StringBuilder ans = new StringBuilder("  "); //README shows two spaces at
    String sep = ""; //start with nothing to seperate, then switch to | to seperate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }
}
