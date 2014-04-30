package fp.groovy

import org.junit.Test

class HigherOrderFunctionsTest {
  // We'll declare a function that we will later pass as an argument.
  boolean isEven(int number) {
    number % 2 == 0
  }

  // This function returns a function.
  Closure multiplyBy(int multiplier) {
    // We define a function, on the spot, which takes an Int
    // and multiplies it by the given multiplier.
    {number ->
      number * multiplier
    }
  }

  // This function takes a function as its second argument.
  // The 'Int => Boolean' syntax is shorthand for 'Function1[Int, Boolean]'.
  boolean checkNumber(int number, Closure check) {
    check(number)
  }

  @Test
  void functionsCanTakeFunctionsAsParameters() {
    // Passing the function as the second argument.
    // In order to treat the function as a value, we have to use 'this.&' to convert
    // it to a closure.  This is clumsy.
    assert checkNumber(2, this.&isEven)
    assert !checkNumber(3, this.&isEven)
  }

  @Test
  void functionsCanBeAnonymous() {
    // We pass a function that does not have a name.
    // Our function checks for odd numbers.
    assert checkNumber(3, {n -> n % 2 != 0})
  }

  @Test
  void functionsCanReturnFunctions() {
    Closure timesFive = multiplyBy(5)

    assert timesFive instanceof Closure // Yep, it returned a function

    assert 30 == timesFive(6)
  }
}
