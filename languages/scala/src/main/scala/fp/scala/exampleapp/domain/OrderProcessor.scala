package fp.scala.exampleapp.domain

import java.io.File
import scala.io.Source
import fp.scala.exampleapp.forms.OrderForm

class OrderProcessor {
  def processOrders(orderFile: File):OrderSummary = {
    val (validOrders, invalidOrders) = Source
                                        .fromFile(orderFile)
                                        .getLines()
                                        .toList
                                        .map(OrderForm.fromJson)
                                        .partition(f => f.isValid())
    println("saving orders")
    val savedOrders = validOrders.map(Order.fromForm(_).save()).toList // try validOrders.par
    OrderSummary(savedOrders, invalidOrders)
  }
}






































    /* Alternate form - stores more intermediate values - might seem more familiar
       if just learning FP.

    def processOrders(orderFile: File):OrderSummary = {
      val lines = Source.fromFile(orderFile).getLines().toList
      val orderForms = lines.map(OrderForm.fromJson)
      val (validOrders, invalidOrders) = orderForms.partition(_.isValid())
      val savedOrders = validOrders.map(Order.fromForm(_).save()).toList  // try validOrders.par
      OrderSummary(savedOrders, invalidOrders)
    }

    */


  /* Alternate form - does not load all lines into memory, but instead uses
     'grouped' iterator to read them in batches of ten.


     This version overcomes the memory problem from loading all lines at
     once, using the 'grouped' method, but at the cost of requiring vars
     so that we can re-assign the running totals.  Folds are the usual
     way of keeping an accumulation while iterating, but they are not
     parallelized.  There is an aggregate() function which might work.

  def processOrders(orderFile: File):OrderSummary = {
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

  */
