package fp.scala.exampleapp.domain

import java.io.File
import scala.io.Source
import fp.scala.exampleapp.forms.{ValidOrderForm, InvalidOrderForm, OrderForm}

class OrderProcessor {

  def processOrders(orderFile: File) {
//    val (validOrders, invalidOrders) = Source.fromFile(orderFile)
//                                              .getLines()
//                                              .map(OrderForm.fromJson)
//                                              .partition({case ValidOrderForm(_) => true
//                                                         case InvalidOrderForm(_) => false})

    val lines = Source.fromFile(orderFile).getLines().grouped(1)

    lines.foreach(batch => {
      val (validOrders, invalidOrders) = batch.map(OrderForm.fromJson)
                                              .partition({case ValidOrderForm(_) => true
                                                       case InvalidOrderForm(_) => false})
      println(s"Saving ${validOrders.size} valid orders")
      validOrders.par.map(Order.fromForm).foreach(_.save())
    })
//
//      .map(OrderForm.fromJson)
//      .partition({case ValidOrderForm(_) => true
//    case InvalidOrderForm(_) => false})


//    invalidOrders.foreach(report)

//    println(s"${validOrders.size} valid orders")
//    println(s"${invalidOrders.size} invalid orders")


    /*
      Skeletal logic here:

      1. Open file using method that takes higher order function to cleanup

      2. Begin streaming in orders

      3. Validate and process using list comprehension methods that can be parallelized

      4. Summarize

      5. Report back the results.




     */
  }

}
