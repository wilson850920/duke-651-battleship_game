package edu.duke.wh162.battleship;

/**
 * this is the class where we state out all the ships
 */
public class V2ShipFactory implements AbstractShipFactory<Character>{

  /** 
   * create the ship according to the orientation
   * swap the width and height for vertical
   */
  protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name){
    Character char_ori = where.getOrient();
          
    if (char_ori != 'H' && char_ori != 'V' && char_ori != 'v' && char_ori != 'h'){
      throw new IllegalArgumentException("Please enter a valid orientation");
    }
    //if (name == "Submarine" || name == "Destroyer") { 
    if (where.getOrient() == 'V' || where.getOrient() == 'v') {
      w = w + h;
      h = w - h;
      w = w - h;
    }
    RectangleShip<Character> rs = new RectangleShip<Character>(name, where.getWhere(), w, h, letter, '*');
    return rs;
    
   //else if (name == "Battleship" || name == "Carrier") {
   //if (where.getOrient() == 'U' || where.getOrient() == 'u'|| where.getOrient() == 'R' || where.getOrient() == 'r' || where.getOrient() == 'L' || where.getOrient() == 'l' || where.getOrient() == 'D' || where.getOrient() == 'd') {
  //if (name == "BattleShip") {
  //   NewBattleShip nb = new NewBattleShip("BattleShip", where.getWhere(), where.getOrient(), "b", "*");
  //   return nb;
  // }
  // else {
  //   NewCarrier nc = new NewCarrier("Carrier", where.getWhere(), where.getOrient(), "c", "*");
  //   return nc;
  // }
     //  }
  //}
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
    return new NewBattleShip("Battleship", where.getWhere(), where.getOrient(), 'b', 'c');
  }

  /**
   * create Carrier ship category
   */
  @Override
  public Ship<Character> makeCarrier(Placement where) {
    return new NewCarrier("Carrier", where.getWhere(), where.getOrient(), 'c', '*');
  }

  /** 
   * create destroyer ship category
   */
  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    return createShip(where, 3, 1, 'd', "Destroyer");
  }
}
