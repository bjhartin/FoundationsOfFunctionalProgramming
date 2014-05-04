package fp.scala.exampleapp.forms

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
    val stringMap: Map[String, String] = map.mapValues(_.toString)
    
    OrderForm(stringMap("email"),
      stringMap("address"),
      stringMap("city"),
      stringMap("state"),
      stringMap("zip"),
      stringMap("sku"),
      stringMap("quantity"),
      stringMap("date"))
  }
}
