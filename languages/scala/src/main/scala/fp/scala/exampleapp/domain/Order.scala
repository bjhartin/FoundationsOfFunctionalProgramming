package fp.scala.exampleapp.domain

import java.util.Date
import java.text.SimpleDateFormat

case class Order(cust: Customer, item: String, quantity: Int, date: Date)

object Order {
  def dateFormat = {
    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
  }

  def fromMap(map: Map[String, Any]): Order = {
    Order(
      Customer(to_s(map("email")),
        Address(to_s(map("address")), to_s(map("city")), to_s(map("state")), to_s(map("zip")))),
      to_s(map("sku")),
      to_int(map("quantity")),
      to_date(map("date")))
  }

  def to_s(a: Any) = a.asInstanceOf[String]
  def to_int(a: Any) = a.asInstanceOf[String].toInt
  def to_date(a: Any) = dateFormat.parse(to_s(a))
}
