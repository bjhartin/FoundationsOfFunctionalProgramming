package fp.scala.exampleapp.domain

import java.util.Date

case class Order(cust: Customer, lines: List[OrderLine], date: Date)
