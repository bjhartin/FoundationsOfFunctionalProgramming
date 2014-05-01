package fp.groovy

import org.junit.Test

class ClosuresTest {
  Closure addIt() {
    int x = 3

    // We return an anonymous function that takes one variable, y.
    // We say y is 'bound', because it will be provided when the function is called.
    // x is unbound, or 'free', but since Groovy's anonymous functions are closures,
    // it will get bound to 3 because there is an x in the lexical scope in which the
    // anonymous function was defined.
    return {y -> y + x}
  }

  @Test
  void closuresCanReferenceVariablesInTheirDefiningScope() {
    // x is not in scope here, but the closure retains its binding.
    assert 8 == addIt()(5)  // Two parens, because addIt() returns a zero-arg function
  }

  @Test
  void closuresCanIntroduceSideEffects() {
    List<String> myList = ["a","b","c"]
    assert ["b", "c"] == removeFirst({return myList})
//    assert  ["a","b","c"] == myList                                // Oops...will fail!
  }

  List<String> removeFirst(Closure computeList){
    List<String> theList = computeList()
    theList.remove(0)
    theList
  }
}
