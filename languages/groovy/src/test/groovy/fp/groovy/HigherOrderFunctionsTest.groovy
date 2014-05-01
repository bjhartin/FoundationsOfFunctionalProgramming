package fp.groovy

import org.junit.Test

class HigherOrderFunctionsTest {
  boolean isEven(int number) {
    number % 2 == 0
  }

  Closure multiplyBy(int multiplier) {
    {number ->
      number * multiplier
    }
  }

  boolean checkNumber(int number, Closure check) {
    check(number)
  }

  @Test
  void functionsCanTakeFunctionsAsParameters() {
    // In order to treat the function as a value, we have to use 'this.&'.
    assert checkNumber(2, this.&isEven)
    assert !checkNumber(3, this.&isEven)
  }

  @Test
  void functionsCanBeAnonymous() {
    assert checkNumber(3, {n -> n % 2 != 0})  // Called lambda expressions in most languages
    assert checkNumber(3, {it % 2 != 0})  // Can use 'it' when just one arg.
  }

  @Test
  void functionsCanReturnFunctions() {
    Closure timesFive = multiplyBy(5)

    assert timesFive instanceof Closure
    assert 30 == timesFive(6)
  }
}
