package fp.scala.exampleapp.domain

import java.io.File
import scala.io.Source
import fp.scala.exampleapp.forms.OrderForm

class OrderProcessor {
  def processOrders(orderFile: File):OrderSummary = {
    val lines = Source.fromFile(orderFile).getLines().toList
    val orderForms = lines.map(OrderForm.fromJson)
    val (validOrders, invalidOrders) = orderForms.partition(_.isValid())
    val savedOrders = validOrders.map(Order.fromForm(_).save())
    OrderSummary(savedOrders, invalidOrders)
  }

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

  def processOrders3(orderFile: File):OrderSummary = {
    val accumulator = (Seq[Order](), Seq[OrderForm]())
    val (savedOrders, invalidOrders) = Source
                                          .fromFile(orderFile)
                                          .getLines()
                                          .grouped(10)
                                          .foldLeft(accumulator)((progress, batch) => {
       val (validForBatch, invalidForBatch) = batch.map(OrderForm.fromJson).partition(_.isValid())
       val savedForBatch = validForBatch.map(Order.fromForm(_).save())
       (savedForBatch, invalidForBatch)
    })

    OrderSummary(savedOrders, invalidOrders)
  }
}
