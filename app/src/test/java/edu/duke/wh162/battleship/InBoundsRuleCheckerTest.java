package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_inbound() {
    V1ShipFactory sf = new V1ShipFactory();
    InBoundsRuleChecker<Character> check = new InBoundsRuleChecker<>(null);
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(10, 20);

    //inbound
    Placement v1_1 = new Placement(new Coordinate(5, 5), 'V');
    Ship<Character> sub1 = sf.makeSubmarine(v1_1);
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'H');
    Ship<Character> sub2 = sf.makeSubmarine(v1_2);
    assertEquals(null, check.checkMyRule(sub1, b));
    assertEquals(null, check.checkMyRule(sub2, b));
    
    //outbound
    Placement v1_3 = new Placement(new Coordinate(20, 6), 'V');
    Ship<Character> sub3 = sf.makeSubmarine(v1_3);
    assertEquals("Your input placement is invalid: the ship exceeds the bottom of the board.", check.checkMyRule(sub3, b));

    Placement v1_4 = new Placement(new Coordinate(4, 10), 'H');
    Ship<Character> sub4 = sf.makeSubmarine(v1_4);
    assertEquals("Your input placement is invalid: the ship exceeds the right of the board.", check.checkMyRule(sub4, b));

    Placement v1_5 = new Placement(new Coordinate(-1, 4), 'V');
    Ship<Character> sub5 = sf.makeSubmarine(v1_5);
    assertEquals("Your input placement is invalid: the ship exceeds the top of the board.", check.checkMyRule(sub5, b));

    Placement v1_6 = new Placement(new Coordinate(2, -11), 'H');
    Ship<Character> sub6 = sf.makeSubmarine(v1_6);
    assertEquals("Your input placement is invalid: the ship exceeds the left of the board.", check.checkMyRule(sub6, b));

    //check placement
    PlacementRuleChecker<Character> p_check = new InBoundsRuleChecker<Character>(null);
    Placement v1_7 = new Placement(new Coordinate(1, 1), 'H');
    Ship<Character> sub7 = sf.makeSubmarine(v1_7);
    //Ship<Character> sub8 = sf.makeCarrier(v1_7);
    assertEquals(null, p_check.checkPlacement(sub7, b));
    assertEquals("Your input placement is invalid: the ship exceeds the right of the board.", p_check.checkPlacement(sub4, b));
  }

}
