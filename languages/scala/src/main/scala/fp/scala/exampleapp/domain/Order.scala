package fp.scala.exampleapp.domain

import java.util.Date
import java.text.SimpleDateFormat
import fp.scala.exampleapp.forms.OrderForm

case class Order(cust: Customer, item: String, quantity: Int, date: Date)

object Order {
  def dateFormat = {
    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
  }

  def fromForm(form: OrderForm): Order = {
    Order(
      Customer(form.email,Address(form.addressLine1, form.city, form.state, form.zip)),
      form.sku,
      form.quantity.toInt,
      dateFormat.parse(form.date))
  }
}
