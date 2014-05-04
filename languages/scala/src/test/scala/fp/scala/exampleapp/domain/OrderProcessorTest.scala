package fp.scala.exampleapp.domain

import org.junit.{Test, Rule}

import java.io.{File, PrintWriter}

class OrderProcessorTest {
  def tempFile() = {File.createTempFile("orders", "json")}

  def orderProcessor = new OrderProcessor()

  @Test
  def run() {
    val testFile = createFile(2)
    orderProcessor.processOrders(testFile)
  }

  def orderJson(i:Int) = {
    s"""{
      "email":         "customer$i@domain.com",
      "address":       "1 Main St",
      "city":          "Des Moines",
      "state":         "IA",
      "zip":           "50131",
      "date":          "2012-04-23T18:25Z",
      "sku":           "6897433574",
      "quantity":      "23"
      }
    """.replaceAll("[\n\r]", "").replaceAll("([\\{,:]) *", "$1 ")
  }

  def createFile(size:Int):File = {
    val file = tempFile()
    doWithWriter(file, writer => {
      (1 to size).foreach((i:Int) => writer.write(orderJson(i) + "\n"))
    })
    file
  }

  def doWithWriter(file: File, f: PrintWriter => Any) {
    val writer = new PrintWriter(file)
    try {
      f(writer)    //  f doesn’t open or close the file
    } finally {
      writer.close()
    }
  }


}
