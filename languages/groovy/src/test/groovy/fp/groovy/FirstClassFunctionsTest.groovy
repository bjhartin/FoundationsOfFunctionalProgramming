package fp.groovy

import org.junit.Test

class FirstClassFunctionsTest {
  /*
    Groovy is dynamically typed, so function return types are optional.
    However, I use them here for clarity.  I also think they are good style
    if you are leveraging FP in Groovy.  The role of types is somewhat
    more powerful in functional programming, so it seems wrong to me to omit the type
    in a functional programming example.
   */

  // Groovy supports first class functions, but not for just any function.
  // This function cannot be treated a a value, yet (but keep reading).
  String regularGreeter(String name) {
    "Hello " + name + "!"
  }

  // This is a function that takes a single string argument.
  // In groovy, to treat a function as a value, it must be a closure.
  // We will discuss closures in more depth in a later example.
  Closure greeter1 = {String name -> "Hello " + name + "!"}

  @Test
  void firstClassFunctionsAreValues(){
    assert greeter1 instanceof Closure
  }

  @Test
  void firstClassFunctionsCanBeInvoked(){
    assert greeter1("you") == "Hello you!"
  }

  @Test
  void firstClassFunctionsCanBeAssigned(){
    Closure g = greeter1
    assert g instanceof Closure
    assert g == greeter1
    assert g("you") == "Hello you!"
  }

  @Test
  void regularFunctionsCanBeConvertedToValues(){
    Closure g = this.&regularGreeter  // Requires 'this'
    assert g instanceof Closure
    assert g("you") == "Hello you!"
  }
}
