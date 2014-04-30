package fp.scala

import org.junit.Test
import org.junit.Assert._

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

  @Test
  def functionsCanTakeFunctionsAsParameters() {
    assertTrue(checkNumber(2, isEven))
    assertFalse(checkNumber(3, isEven))
  }

  @Test
  def functionsCanBeAnonymous() {
    assertTrue(checkNumber(3, (number:Int) => {number % 2 != 0}))  // Called lambda expressions in most languages
  }

  @Test
  def functionsCanReturnFunctions() {
    val timesFive = multiplyBy(5)

    assertTrue(timesFive.isInstanceOf[Int => Int])
    assertEquals(30, timesFive(6))
  }
}
