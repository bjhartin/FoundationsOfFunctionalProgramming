package fp.scala

import org.junit.Test
import org.junit.Assert._
import fp.scala.exampleapp.domain._
import java.util.Date

class PatternMatchingTest {

  @Test
  def simplePattern() {
    def matched(value: String) = {  // A function defined with in a function
      value match {
        case "abc" => true
        case "def" => false
      }
    }

    assertTrue(matched("abc"))
  }

  @Test
  def canMatchDifferentTypes(){
    def matched(value: Any) = {
      value match {
        case "one" => 1
        case 2 => "two"
      }
    }

    assertEquals(1, matched("one"))
    assertEquals("two", matched(2))
  }

  @Test
  def canMatchByType(){
    def matched(value: Any) = {
      value match {
        case s:String => "string"
        case i:Integer => "integer"
      }
    }

    assertEquals("string", matched("foo"))
    assertEquals("integer", matched(2))
  }

  @Test
  def canHandleFallThrough(){
    def matched(value: Any) = {
      value match {
        case s:String => "string"
        case i:Integer => "integer"
        case _ => "other"
      }
    }

    assertEquals("other", matched(3.0))
  }

  @Test
  def canUsePatternGuards(){
    def matched(value: Any) = {
      value match {
        case s:String if s.startsWith("f") => "it started with f"
        case s:String => "another string"
        case _ => "not a string"
      }
    }

    assertEquals("it started with f", matched("foo"))
    assertEquals("another string", matched("no"))
    assertEquals("not a string", matched(1))
  }

  @Test
  def canUseTheMatchedValue(){
    def matched(value: Any) = {
      value match {
        case s:String => "it was " + s
        case _ => "other"
      }
    }

    assertEquals("it was foo", matched("foo"))
    assertEquals("other", matched(2))
  }
//
//  val hawaiiCustomer = Customer("joe@gmail.com", Address("1 main st", "", "Honolulu", "HI", "12345"), 1)
//  val regularCustomer = Customer("john@gmail.com", Address("10 1st Ave", "", "Des Moines", "IA", "12345"), 29)
//  val goodCustomer = Customer("jane@gmail.com", Address("101 40th St", "", "Des Moines", "IA", "12345"), 100)
//
//  val hammer = InventoryItem("10001", "Hammer", 5.99)
//  val wrench = InventoryItem("10002", "Wrench", 3.99)
//
//  val hawaiiOrder = Order(hawaiiCustomer, List(OrderLine(hammer, 1)), new Date())
//  val regularOrder = Order(regularCustomer, List(OrderLine(hammer, 3)), new Date())
//  val goodCustomerOrder = Order(goodCustomer, List(OrderLine(wrench, 3)), new Date())
//  val bigOrder = Order(regularCustomer, List(OrderLine(hammer, 5), OrderLine(wrench, 9)), new Date())
//
//
//  def shippingUpcharge(order: Order): Double = {
//    order match {
//      case Order(Customer(_, Address(_, _, _, "HI", _), _), _, _) => 1.5
//      case Order(_, l1::l2::ls, _) => 0.9
//      case Order(Customer(_, _, totalOrders), _, _) if totalOrders > 50 => 0.8
//      case _ => 1.0
//    }
//  }
//
//  @Test
//  def complexExample(){
//    val delta = 0.001  // Needed when comparing floating point numbers
//
//    assertEquals(1.0, shippingUpcharge(regularOrder), delta)
//    assertEquals(1.5, shippingUpcharge(hawaiiOrder), delta)
//    assertEquals(0.9, shippingUpcharge(bigOrder), delta)
//    assertEquals(0.8, shippingUpcharge(goodCustomerOrder), delta)
//  }

}
