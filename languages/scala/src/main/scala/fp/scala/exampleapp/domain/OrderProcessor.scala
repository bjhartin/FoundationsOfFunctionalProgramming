package fp.scala.exampleapp.domain

import java.io.File
import scala.io.Source
import fp.scala.exampleapp.forms.{ValidOrderForm, InvalidOrderForm, OrderForm}

class OrderProcessor {

  def processOrders(orderFile: File) {
    Source.fromFile(orderFile).getLines().foreach(line => {
      OrderForm.fromJson(line).validate() match {
        case InvalidOrderForm(f) => println("invalid")
        case ValidOrderForm(f) => println("valid")
      }
    })

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
