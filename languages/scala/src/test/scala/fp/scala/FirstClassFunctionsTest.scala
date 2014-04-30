package fp.scala

import org.junit.Test
import org.junit.Assert._

class FirstClassFunctionsTest {
  /*
    Scala can infer the return type, so it is not necessary to declare it,
    but it is considered good style.
   */

  // This function is declared as a value.
  val greeter1 = (name: String) => "Hello " + name + "!"

  // This function is not declared as a value.
  def greeter2(name: String): String = {"Hello " + name + "!"}

  @Test
  def firstClassFunctionsAreValues() {
    assertTrue(greeter1.isInstanceOf[Function1[String, String]])

    // Scala knows greeter2 can be converted to a value.  , If we
    // provide '_' as its first argument, it can be assigned to a value.
    assertTrue((greeter2 _).isInstanceOf[Function1[String, String]])
  }

  @Test
  def firstClassFunctionsCanBeInvoked() {
    assertEquals("Hello you!", greeter1("you"))
    assertEquals("Hello you!", greeter2("you"))
  }

  @Test
  def firstClassFunctionsCanBeAssigned() {
    val g1 = greeter1
    val g2 = greeter2 _
    assertEquals("Hello you!", g1("you"))
    assertEquals("Hello you!", g2("you"))
  }


}
