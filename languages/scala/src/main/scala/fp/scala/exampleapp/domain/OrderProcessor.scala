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
  def processOrders(orderFile: File):OrderSummary = {
    val lines = Source.fromFile(orderFile).getLines().toList
    val orderForms = lines.map(OrderForm.fromJson)
    val (validOrders, invalidOrders) = orderForms.partition(_.isValid())
    val savedOrders = validOrders.map(Order.fromForm(_).save()).toList  // try validOrders.par
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
    val savedOrders = validOrders.map(Order.fromForm(_).save()).toList // try validOrders.par
    OrderSummary(savedOrders, invalidOrders)
  }


  // This version overcomes the memory problem from loading all lines at
  // once, using the 'grouped' method, but at the cost of requiring vars
  // so that we can re-assign the running totals.  Folds are the usual
  // way of keeping an accumulation while iterating, but they are not
  // parallelized.  There is an aggregate() function which might work.
  def processOrders3(orderFile: File):OrderSummary = {
    var savedOrders = List[Order]()
    var invalidOrders = List[OrderForm]()

    Source.fromFile(orderFile).getLines().grouped(10).foreach(batch => {
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

}
