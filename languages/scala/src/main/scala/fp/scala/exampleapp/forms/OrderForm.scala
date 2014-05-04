package fp.scala.exampleapp.forms

import scala.util.parsing.json.JSON
import fp.scala.exampleapp.domain.Order


case class OrderForm(email: String = "",
                      addressLine1: String = "",
                      city: String = "",
                      state: String = "",
                      zip: String = "",
                      date: String = "",
                      sku: String = "",
                      quantity: String = "",
                      validationErrors: List[String] = List())  {


  def values = List(email, addressLine1, city, state, zip, date, sku, quantity)

  def validate(): Either[OrderForm, Order] = {
    this match {
      case f if !values.filter(isBlank).isEmpty =>
        Left(f.copy(validationErrors = List("Some required fields missing")))
      case f => Left(f)
    }
  }

  def isBlank(s:String):Boolean = {s == null || s.trim == ""}
}



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

object InvalidOrderForm {
  def unapply(e: Left[OrderForm, Order]) = e.left.get
}
