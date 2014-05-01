package fp.scala

import org.junit.Test
import org.junit.Assert._
import java.io.{File, PrintWriter}

class HigherOrderFunctionsTest {
  def isEven(number: Int): Boolean = {number % 2 == 0}

  def multiplyBy(multiplier: Int): Int => Int = {
    (number:Int) => {
      number * multiplier
    }
  }
  // The 'Int => Boolean' syntax is shorthand for 'Function1[Int, Boolean]'.
  def checkNumber(number: Int, check: Int => Boolean):Boolean = {
    check(number)
  }

  // This is just an example of ensuring resources are cleaned up
  // with higher-order functions.
  def doWithWriter(filename: String, f: PrintWriter => Any) = {
    val writer = new PrintWriter(new File(filename))
    try {
      f(writer)    //  f doesn’t open or close the file
    } finally {
      writer.close
    }
  }


  @Test
  def functionsCanTakeFunctionsAsParameters() {
    assertTrue(checkNumber(2, isEven))
    assertFalse(checkNumber(3, isEven))
  }

  @Test
  def functionsCanBeAnonymous() {
    assertTrue(checkNumber(3, (number:Int) => {number % 2 != 0}))  // Called lambda expressions in most languages

    assertTrue(checkNumber(3, {_ % 2 != 0}))  // Can use a more concise syntax due to type inference
  }

  @Test
  def functionsCanReturnFunctions() {
    val timesFive = multiplyBy(5)

    assertTrue(timesFive.isInstanceOf[Int => Int])
    assertEquals(30, timesFive(6))
  }
}
