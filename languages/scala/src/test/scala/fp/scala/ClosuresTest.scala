package fp.scala

import org.junit.Test
import org.junit.Assert._
import scala.collection.mutable.ListBuffer

class ClosuresTest {
  def addIt(): Int => Int = {
    val x = 3

    // We return an anonymous function that takes one variable, y.
    // We say y is 'bound', because it will be provided when the function is called.
    // x is unbound, or 'free', but since Groovy's anonymous functions are closures,
    // it will get bound to 3 because there is an x in the lexical scope in which the
    // anonymous function was defined.
    (y:Int) => {y + x}
  }

  @Test
  def closuresCanReferenceVariablesInTheirDefiningScope() {
    // x is not in scope here, but the closure retains its binding.
    assertEquals(8, addIt()(5))  // Two parens, because addIt() returns a zero-arg function
  }

  @Test
  def closuresCanIntroduceSideEffects () {
    var myList = ListBuffer("a", "b", "c")
    assertEquals(ListBuffer("b", "c"), removeFirst(() => {myList}))
//    assertEquals(ListBuffer("a","b","c"), myList)                                // Oops...will fail!
  }

  def removeFirst(computeList: () => ListBuffer[String]) : ListBuffer[String] = {
    val theList = computeList()
    theList.remove(0)
    theList
  }
}
