package fp.scala.exampleapp.domain

import fp.scala.exampleapp.forms.OrderForm

case class OrderSummary(savedOrders: Seq[Order], invalidOrders: Seq[OrderForm]) {

}
