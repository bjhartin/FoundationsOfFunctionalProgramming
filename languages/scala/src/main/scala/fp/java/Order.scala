package fp.java

import java.util.Date

case class Order(cust: Customer, lines: List[OrderLine], date: Date)
