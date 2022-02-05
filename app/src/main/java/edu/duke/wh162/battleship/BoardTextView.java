package edu.duke.wh162.battleship;

import java.util.function.Function;

/**
 * This class handles textual display of a Board (i.e., converting it to a string to show to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the enemy's board.
 */

public class BoardTextView {
  
  private final Board<Character> toDisplay;

  /** Constructs a BoardView, given the board it will display.
   * @param toDisplay is the Board to display
   * @throws IllegalArgumentException if the board is larger than 10x26
   */
  public BoardTextView(Board<Character> toDisplay){
    this.toDisplay = toDisplay;
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException("Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
  }

  /**
   * Display the board base on the width and height
   * for now it's just printing a blank board
   */
  private String displayAnyBoard(Function<Coordinate, Character> getSquareFn) {
    StringBuilder res = new StringBuilder("");
    res.append(makeHeader());

    for (int row = 0; row < toDisplay.getHeight(); row ++) {
      String sep = "";
      char letter = (char)(row + 'A');
      res.append(letter + " ");
      for (int column = 0; column < toDisplay.getWidth(); column ++) {
        res.append(sep);
        Coordinate c = new Coordinate(row, column);
        if(getSquareFn.apply(c) == null) {
          res.append(" ");
        }
        else{
          res.append(getSquareFn.apply(c));
       }
        sep = "|";
      }
      res.append(" " + letter + '\n');
    }
    res.append(makeHeader());
    return res.toString(); 
  }

  /**
   * by all the points on the board
   * display your board with all the information
   */
  public String displayMyOwnBoard() {
    return displayAnyBoard((c)->toDisplay.whatIsAtForSelf(c));
  }

  /**
   * by all the points on the board
   * display the enemy's board with all the information
   */
  public String displayEnemyBoard() {
    return displayAnyBoard((c)->toDisplay.whatIsAtForEnemy(c));
  }
  
  /** 
   * This makes the header line, e.g. 0|1|2|3|4\n
   * 
   * @return the String that is the header line for the given board
   */
  String makeHeader() {
    StringBuilder ans = new StringBuilder("  "); 
    String sep = ""; 
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }

  /**
   * this function helps us to create all the spaces between the two boards
   */
  protected String createspace(int space_num) {
    if (space_num == 0) {
      return null;
    }
    
    StringBuilder sb = new StringBuilder();
    for (int k = 0; k < space_num; k++) {
      sb.append(" ");
    }
    
    String str = sb.toString();
    return str;
  }
  
  /**
   * Put your board on left and the enemy's board on the left
   * we want to keep the distance between the boards in a fix size
   * your board starts at column 5
   * the enemy's board starts at column 2 * W + 19
   * for more information, please refer to task20.txt
   */
  public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {
    int boardwidth = toDisplay.getWidth();
    int selfhead = 5; 
    int selfhead_to_enemyhead = 22 + 2 * boardwidth - myHeader.length() - selfhead; 
    int spacefor_width = 18; 
    int spacefor_body = 16; 
    StringBuilder sb = new StringBuilder();
    
    String begin = createspace(selfhead);
    String betweenhaeds = createspace(selfhead_to_enemyhead);
    String widthspace = createspace(spacefor_width);
    String bodyspace = createspace(spacefor_body);
    String final_head = String.join("", "\n", begin, myHeader, betweenhaeds, enemyHeader, "\n");
    sb.append(final_head);
    
    String[] myboard = displayMyOwnBoard().split("\n");
    String[] enemyboard = enemyView.displayEnemyBoard().split("\n");
    String bodytitle = String.join("", myboard[myboard.length - 1], widthspace, enemyboard[enemyboard.length - 1], "\n");
    sb.append(bodytitle);
    
    for (int k = 1; k < myboard.length - 1; k ++) {
      String bodyperline = String.join("", myboard[k], bodyspace, enemyboard[k], "\n");
      sb.append(bodyperline);
    }
    sb.append(bodytitle);
    return sb.toString(); 
  }
}
