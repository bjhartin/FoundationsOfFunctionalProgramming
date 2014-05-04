package fp.scala.exampleapp.forms

import org.junit.Test
import org.junit.Assert._

import scala.util.parsing.json.JSON
import fp.scala.exampleapp.domain.Order

class OrderFormTest {

  def orderFormJson() =
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


  @Test
  def fromJson() {
    val result: Option[Order] = OrderForm.fromJson(orderFormJson())

    result match {
      case Some(o) => assertEquals("johnsmith@domain.com", o.cust.email)
      case None => fail("Order parsing failed")
    }
  }
}
