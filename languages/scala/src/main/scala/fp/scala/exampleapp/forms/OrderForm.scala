package fp.scala.exampleapp.forms

import fp.scala.exampleapp.domain.Order
import scala.util.parsing.json.JSON


case class OrderForm(customerEmail: String = "",
                      addressLine1: String = "",
                      city: String = "",
                      state: String = "",
                      zip: String = "",
                      orderDate: String = "",
                      inventoryItemSku: String = "",
                      quantity: String = "")

object OrderForm {
  def fromJson(json: String): OrderForm = {
    JSON.parseFull(json) match {
      case Some(parsed:Map[String, Any]) => fromMap(parsed)
      case _ => OrderForm()
    }
  }

  def fromMap(map: Map[String, Any]): OrderForm = {
    OrderForm(to_s(map("email")),
      to_s(map("address")),
      to_s(map("city")),
      to_s(map("state")),
      to_s(map("zip")),
      to_s(map("sku")),
      to_s(map("quantity")),
      to_s(map("date")))
  }

  def to_s(a: Any) = a.asInstanceOf[String]
}
