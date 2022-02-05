/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.wh162.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

class AppTest {
  //@Disabled
  //@Test
  //@ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
  //void test_main_argAwin() throws IOException{
  //  ByteArrayOutputStream bytes = new ByteArrayOutputStream();
  //  PrintStream out = new PrintStream(bytes, true);

  //  InputStream input = getClass().getClassLoader().getResourceAsStream("input3.txt");
  //  //assertNotNull(input);

  //  InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output3.txt");
  //  //assertNotNull(expectedStream);

  //  InputStream oldIn = System.in;
  //  PrintStream oldOut = System.out;

  //  try {
  //    System.setIn(input);
  //    System.setOut(out);
  //    App.main(new String[0]);
  //  }
  //  finally {
  //    System.setIn(oldIn);
  //    System.setOut(oldOut);
  //  }
  //  
  //  String expected = new String(expectedStream.readAllBytes());
  //  String actual = bytes.toString();
  //  //assertEquals(expected, actual);
  //}

  //@Disabled
  //@Test
  //@ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
  //void test_main_argBwin() throws IOException{
  //  ByteArrayOutputStream bytes = new ByteArrayOutputStream();
  //  PrintStream out = new PrintStream(bytes, true);

  //  InputStream input = getClass().getClassLoader().getResourceAsStream("input2.txt");
  //  assertNotNull(input);

  //  InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output2.txt");
  //  assertNotNull(expectedStream);

  //  InputStream oldIn = System.in;
  //  PrintStream oldOut = System.out;

  //  try {
  //    System.setIn(input);
  //    System.setOut(out);
  //    App.main(new String[0]);
  //  }
  //  finally {
  //    System.setIn(oldIn);
  //    System.setOut(oldOut);
  //  }
  //  
  //  String expected = new String(expectedStream.readAllBytes());
  //  String actual = bytes.toString();
  //  assertEquals(expected, actual);
  //}

  //@Disabled
  @Test
  @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
  void test_main_argCwin() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes, true);

    InputStream input = getClass().getClassLoader().getResourceAsStream("input1.txt");
    assertNotNull(input);

    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output1.txt");
    assertNotNull(expectedStream);

    InputStream oldIn = System.in;
    PrintStream oldOut = System.out;

    try {
      System.setIn(input);
      System.setOut(out);
      App.main(new String[0]);
    }
    finally {
      System.setIn(oldIn);
      System.setOut(oldOut);
    }
    
    String expected = new String(expectedStream.readAllBytes());
    String actual = bytes.toString();
  }
}
