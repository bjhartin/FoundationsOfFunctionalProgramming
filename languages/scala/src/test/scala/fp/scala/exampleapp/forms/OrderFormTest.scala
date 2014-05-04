package fp.scala.exampleapp.forms

import org.junit.Test
import org.junit.Assert._

import scala.util.parsing.json.JSON
import fp.scala.exampleapp.domain.{Address, Customer, Order}
import java.util.{GregorianCalendar, Calendar, Date}

class OrderFormTest {

  def orderFormJson() = {
    """{
      |"email":         "johnsmith@domain.com",
      |"address":       "1 Main St",
      |"city":          "Des Moines",
      |"state":         "IA",
      |"zip":           "50131",
      |"date":          "2012-04-23T18:25Z",
      |"sku":           "6897433574",
      |"quantity":      "23"
      |}
    """.stripMargin
  }

  def malformedJson() = {
    "Not legal json"
  }

//  def order() = Order(Customer("johnsmith@domain.com",
//                       Address("1 Main St", "Des Moines", "IA", "50131")),
//                       "6897433574",
//                       23,
//                       new GregorianCalendar(2012, 3, 23, 18, 25).getTime) // April is 3 in GregorianCalendar

    def orderForm() = OrderForm("johnsmith@domain.com",
                         "1 Main St", "Des Moines", "IA", "50131",
                         "6897433574",
                         "23",
                         "2012-04-23T18:25Z")


  @Test
  def fromJson() {
    assertEquals(orderForm(), OrderForm.fromJson(orderFormJson()))
  }

  @Test
  def fromUnparseableJson() {
    assertEquals(malformedJson(), OrderForm())
  }
}
