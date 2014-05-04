package fp.scala.exampleapp.forms

import fp.scala.exampleapp.domain.Order
import scala.util.parsing.json.JSON


case class OrderForm(customerEmail: String,
                      addressLine1: String,
                      city: String,
                      state: String,
                      zip: String,
                      orderDate: String,
                      inventoryItemSku: String,
                      quantity: String)

object OrderForm {
  def fromJson(json: String): Option[Order] = {
    JSON.parseFull(json) match {
      case Some(parsed:Map[String, Any]) => Some(Order.fromMap(parsed))
      case s => None
    }
  }
}
