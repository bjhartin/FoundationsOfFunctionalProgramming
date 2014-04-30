package fp.scala

import org.junit.Test
import org.junit.Assert._

class HigherOrderFunctionsTest {
  // We'll declare a function that we will later pass as an argument.
  def isEven(number: Int): Boolean = {number % 2 == 0}

  // This function returns a function.
  def multiplyBy(multiplier: Int): Int => Int = {
    // We define a function, on the spot, which takes an Int
    // and multiplies it by the given multiplier.
    (number:Int) => {
      number * multiplier
    }
  }

  // This function takes a function as its second argument.
  // The 'Int => Boolean' syntax is shorthand for 'Function1[Int, Boolean]'.
  def checkNumber(number: Int, check: Int => Boolean):Boolean = {
    check(number)
  }

  @Test
  def functionsCanTakeFunctionsAsParameters() {
    // Passing the function as the second argument.
    assertTrue(checkNumber(2, isEven))
    assertFalse(checkNumber(3, isEven))
  }

  @Test
  def functionsCanBeAnonymous() {
    // We pass a function that does not have a name.
    // Our function checks for odd numbers.
    assertTrue(checkNumber(3, (number:Int) => {number % 2 != 0}))
  }

  @Test
  def functionsCanReturnFunctions() {
    val timesFive = multiplyBy(5) //

    assertTrue(timesFive.isInstanceOf[Int => Int]) // Yep, it returned a function

    assertEquals(30, timesFive(6))
  }
}
