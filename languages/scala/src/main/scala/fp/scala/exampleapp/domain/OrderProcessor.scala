package fp.scala.exampleapp.domain

import java.io.File
import scala.io.Source
import fp.scala.exampleapp.forms.OrderForm

class OrderProcessor {
  // There are three versions of the 'processOrders' method, each written
  // with slightly different characteristics.

  // Processes orders - reads, parses, validates, saves and reports results.
  // Has a slight defect in that it loads all lines into memory, which
  // you can overcome using the 'grouped' method on the iterator
  // returned by 'getLines()'.
  def processOrders1(orderFile: File):OrderSummary = {
    val lines = Source.fromFile(orderFile).getLines().toList
    val orderForms = lines.map(OrderForm.fromJson)
    val (validOrders, invalidOrders) = orderForms.partition(_.isValid())
    val savedOrders = validOrders.map(Order.fromForm(_).save())
    OrderSummary(savedOrders, invalidOrders)
  }

  // This version is written in a slightly different style.
  // We chain together list comprehensions, rather than storing
  // intermediate values.  The end result is the same.
  def processOrders2(orderFile: File):OrderSummary = {
    val (validOrders, invalidOrders) = Source
                                        .fromFile(orderFile)
                                        .getLines()
                                        .toList
                                        .map(OrderForm.fromJson)
                                        .partition(_.isValid())
    val savedOrders = validOrders.map(Order.fromForm(_).save())
    OrderSummary(savedOrders, invalidOrders)
  }

  def processOrders(orderFile: File):OrderSummary = {
    var savedOrders = List[Order]()
    var invalidOrders = List[OrderForm]()

    val iterator = Source.fromFile(orderFile).getLines().grouped(10)
      iterator.foreach(batch => {
        val (validForBatch, invalidForBatch) = batch
                                               .par
                                               .map(OrderForm.fromJson)
                                               .partition(_.isValid())
      val savedForBatch = validForBatch.map(Order.fromForm(_).save())
      savedOrders = savedForBatch.toList ::: savedOrders
      invalidOrders = invalidForBatch.toList ::: invalidOrders
    })
    OrderSummary(savedOrders, invalidOrders)

  }

  /*
  val iterator = Source.fromFile(orderFile).getLines().grouped(10)
    iterator.foreach(batch => {
      val (validOrders, invalidOrders) = batch.map(OrderForm.fromJson)
                                              .partition({case ValidOrderForm(_) => true
                                                       case InvalidOrderForm(_) => false})
      println(s"Saving ${validOrders.size} valid orders")
      validOrders.par.map(Order.fromForm).foreach(_.save())
    })
   */
}
