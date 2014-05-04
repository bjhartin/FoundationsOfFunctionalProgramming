package fp.scala.exampleapp.domain

import org.junit.Test
import fp.scala.exampleapp.forms.OrderForm
import java.util.GregorianCalendar
import org.junit.Assert._

class OrderTest {
  def validOrderForm() = OrderForm("johnsmith@domain.com",
                          "1 Main St",
                          "Des Moines",
                          "IA",
                          "50131",
                          "2012-04-23T18:25Z",
                          "6897433574",
                          "23")

    def order() = Order(Customer("johnsmith@domain.com",
                         Address("1 Main St", "Des Moines", "IA", "50131")),
                         "6897433574",
                         23,
                         new GregorianCalendar(2012, 3, 23, 18, 25).getTime) // April is 3 in GregorianCalendar

  @Test
  def fromValidOrderForm() {
    assertEquals(order(), Order.fromForm(validOrderForm()))
  }
}

