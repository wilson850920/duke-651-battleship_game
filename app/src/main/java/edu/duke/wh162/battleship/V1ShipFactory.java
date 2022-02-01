package edu.duke.wh162.battleship;

public class V1ShipFactory implements AbstractShipFactory<Character>{

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
  
  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    // TODO Auto-generated method stub
    //return null;
    return createShip(where, 2, 1, 's', "Submarine");
  }

  @Override
  public Ship<Character> makeBattleship(Placement where) {
    // TODO Auto-generated method stub
    //return null;
    return createShip(where, 4, 1, 'b', "BattleShip");
  }

  @Override
  public Ship<Character> makeCarrier(Placement where) {
    // TODO Auto-generated method stub
    //return null;
    return createShip(where, 6, 1, 'c', "Carrier");
  }

  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    // TODO Auto-generated method stub
    //return null;
    return createShip(where, 3, 1, 'd', "Destroyer");
  }

}
