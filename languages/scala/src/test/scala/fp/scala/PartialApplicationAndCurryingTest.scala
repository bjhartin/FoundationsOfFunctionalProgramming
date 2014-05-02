package fp.scala

import org.junit.Test
import org.junit.Assert._

class PartialApplicationAndCurryingTest {
  def addEm(x: Int, y: Int) = {x + y}

  def addEm2(x: Int)(y: Int) = {x + y} // Defining with this syntax allows currying

  @Test
  def partiallyAppliedFunction(){
    assertEquals(3, addEm(1,2))

    def addFive = addEm(5, _: Int)   // We don't provide the first argument

    assertTrue(addFive.isInstanceOf[Int => Int])
    assertEquals(8, addFive(3))
  }

  @Test
  def curriedFunction(){
    assertEquals(3, addEm2(1)(2))

    def addFive = addEm2(5)(_)  // Slightly different syntax

    assertTrue(addFive.isInstanceOf[Int => Int])
    assertEquals(8, addFive(3))
  }

  @Test
  def curryAndHigherOrderFunctions(){
    // Reverses a list and filters it by the function f
    def reverseAndFilter(list: List[Int])(f: Int => Boolean) = {
      list.reverse.filter(f)
    }

    def reverseAndDropMultiplesOfTen = reverseAndFilter(_: List[Int])(
      (num:Int) => {num % 10 != 0}   // An anonymous function
    )

    val nums = List(1, 10, 2, 3, 5, 20, 30, 8)

    assertEquals(List(8, 5, 3, 2, 1), reverseAndDropMultiplesOfTen(nums))

  }


  /*

   Final note: It probably seems a little clumsy to have to either provide the '_' or define the method
   as curryable.  Both of these are Scala limitations, possibly because of the JVM.  In some languages,
   such as Haskell, every function is curryable and there is no special syntax needed, e.g.

   Of course, the syntax is a little different than what you're used to.

   add :: Int -> Int -> Int      -- Function declaration - takes two Ints and returns an Int
   add x y = x + y               -- Function body

   four = add 3 1    -- Invokes add.  Parens not used for function application in Haskell.

   addOne = add 1    -- Invokes add with one argument, returning a function whose type is Int -> Int.
   addTwo = add 2    -- Same

   */

}
