package edu.duke.wh162.battleship;

/**
 * this is the class where we state out all the ships
 */
public class V1ShipFactory implements AbstractShipFactory<Character>{

  /** 
   * create the ship according to the orientation
   * swap the width and height for vertical
   */
  protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name){
    Character char_ori = where.getOrient();
    if (char_ori != 'H' && char_ori != 'V' && char_ori != 'v' && char_ori != 'h'){
      throw new IllegalArgumentException("Please enter a valid orientation");
    }
    
    if (where.getOrient() == 'V' || where.getOrient() == 'v') {
      w = w + h;
      h = w - h;
      w = w - h;
    }
    RectangleShip<Character> rs = new RectangleShip<Character>(name, where.getWhere(), w, h, letter, '*');
    return rs;
  }
  
  /**
   * creat submarine ship category
   */
  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    return createShip(where, 2, 1, 's', "Submarine");
  }

  /** 
   * create battleship ship category
   */
  @Override
  public Ship<Character> makeBattleship(Placement where) {
    return createShip(where, 4, 1, 'b', "BattleShip");
  }

  /**
   * create Carrier ship category
   */
  @Override
  public Ship<Character> makeCarrier(Placement where) {
    return createShip(where, 6, 1, 'c', "Carrier");
  }

  /** 
   * create destroyer ship category
   */
  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    return createShip(where, 3, 1, 'd', "Destroyer");
  }
}
