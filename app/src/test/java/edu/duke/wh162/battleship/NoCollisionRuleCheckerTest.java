package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {

  @Test
  public void test_collide() {
    V1ShipFactory sf = new V1ShipFactory();
    NoCollisionRuleChecker<Character> coll = new NoCollisionRuleChecker<>(null);
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(10, 20, 'X');

    Placement v1 = new Placement(new Coordinate(2, 2), 'V');
    Ship<Character> s1 = sf.makeBattleship(v1);
    assertEquals(null, coll.checkMyRule(s1, b));
    b.tryAddShip(s1);
    
    Placement h1 = new Placement(new Coordinate(2, 2), 'H');
    Ship<Character> s2 = sf.makeBattleship(h1);
    assertEquals("Your input placement is invalid: the coordinate you typed in overlaps with one of the existing ships.", coll.checkMyRule(s2, b));
  }

  @Test
  void test_inbound_collide() {
    V1ShipFactory sf = new V1ShipFactory();
    NoCollisionRuleChecker<Character> coll = new NoCollisionRuleChecker<>(new InBoundsRuleChecker<Character>(null));
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(10, 20, 'X');

    Placement v1 = new Placement(new Coordinate(2, 2), 'V');
    Ship<Character> s1 = sf.makeBattleship(v1);

    Placement v2 = new Placement(new Coordinate(6, 6), 'V');
    Ship<Character> s2 = sf.makeBattleship(v2);

    Placement v3 = new Placement(new Coordinate(0, 0), 'H');
    Ship<Character> s3 = sf.makeBattleship(v3);

    Placement v4 = new Placement(new Coordinate(0, 0), 'H');
    Ship<Character> s4 = sf.makeBattleship(v4);

    Placement v5 = new Placement(new Coordinate(-1, -10), 'H');
    Ship<Character> s5 = sf.makeBattleship(v5);
    
    assertEquals(null, coll.checkMyRule(s1, b));
    b.tryAddShip(s1);
    assertEquals(null, coll.checkMyRule(s2, b));
    b.tryAddShip(s2);
    assertEquals(null, coll.checkMyRule(s3, b));
    b.tryAddShip(s3);
    assertEquals("Your input placement is invalid: the coordinate you typed in overlaps with one of the existing ships.", coll.checkMyRule(s4, b));
    assertEquals("Your input placement is invalid: the ship exceeds the top of the board.", coll.checkPlacement(s5, b));
  }
}
