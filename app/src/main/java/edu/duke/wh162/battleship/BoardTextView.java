package edu.duke.wh162.battleship;

import java.util.function.Function;

/**
 * This class handles textual display of a Board (i.e., converting it to a string to show to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the enemy's board.
 */

public class BoardTextView {
  
  /** 
   * The Board to display
   */

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
  
  //public String displayMyOwnBoard() {
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
        //if(toDisplay.whatIsAtForSelf(c) == null){
        if(getSquareFn.apply(c) == null) {
          res.append(" ");
          //System.out.println("ho: " + toDisplay.whatIsAt(c));
        }
        else{
          //res.append(toDisplay.whatIsAtForSelf(c));
          res.append(getSquareFn.apply(c));
          //System.out.println("hi: " + toDisplay.whatIsAt(c));
        }
        sep = "|";
      }
      res.append(" " + letter + '\n');
      //letter++;
    }
    res.append(makeHeader());
    return res.toString(); 
  }

  public String displayMyOwnBoard() {
    return displayAnyBoard((c)->toDisplay.whatIsAtForSelf(c));
  }

  public String displayEnemyBoard() {
    return displayAnyBoard((c)->toDisplay.whatIsAtForEnemy(c));
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
   */
  public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {
    //create all the spaces
    int boardwidth = toDisplay.getWidth();
    int selfhead = 5; //space in front of the header
    int selfhead_to_enemyhead = 22 + 2 * boardwidth - myHeader.length() - selfhead; //space between the header
    //System.out.print(selfhead_to_enemyhead);
    int spacefor_width = 18; // the space bewteen the display widht
    int spacefor_body = 16; // the space between the height infor

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
