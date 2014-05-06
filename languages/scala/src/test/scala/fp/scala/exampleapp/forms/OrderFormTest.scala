package fp.scala.exampleapp.forms

import org.junit.Test
import org.junit.Assert._

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


  def orderForm() = OrderForm("johnsmith@domain.com",
                       "1 Main St", "Des Moines", "IA", "50131",
                       "2012-04-23T18:25Z",
                       "6897433574",
                       "23")

  @Test
  def fromUnparseableJson() {
    assertEquals(OrderForm.fromJson(malformedJson()), OrderForm())
  }

  @Test
  def isValidForBadOrder() {
    val badEmailJson = orderFormJson().replace("johnsmith@domain.com", "")
    assertFalse(OrderForm.fromJson(badEmailJson).isValid())
  }

  @Test
  def isValidForGoodOrder() {
    val badEmailJson = orderFormJson().replace("johnsmith@domain.com", "")
    assertFalse(OrderForm.fromJson(badEmailJson).isValid())
  }
}
