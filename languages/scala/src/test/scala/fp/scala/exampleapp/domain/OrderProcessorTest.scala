package fp.scala.exampleapp.domain

import org.junit.{Before, After, Test, Rule}
import org.junit.Assert._

import java.io.{File, PrintWriter}

class OrderProcessorTest {
//  var file:File = null
//
//  @Before
//  def setup() {
//    file = new File(System.getProperty("java.io.tmpdir") +
//                    File.separator + "orders.json")
//
//  }

//  @After
//  def teardown() {    
//    assertTrue("Could not delete file!", file.delete())
//  }

  def file() = {File.createTempFile("orders", "json")}

  def orderProcessor = new OrderProcessor()

  @Test
  def processAllValidOrders() {
    val testFile = createTestOrdersFile(file, 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }

  @Test
  def processInvalidOrders() {
    val testFile = createTestOrdersFile(file, 5, 3)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(3, orderSummary.invalidOrders.size)
  }

//  @Test
//  def processAllValidOrders2() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processAllValidOrders3() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processAllValidOrders5() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processAllValidOrders6() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processAllValidOrders7() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processAllValidOrders8() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processInvalidOrders9() {
//    val testFile = createTestOrdersFile(file, 5, 3)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(3, orderSummary.invalidOrders.size)
//  }
//
//  @Test
//  def processAllValidOrders10() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processAllValidOrders11() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processAllValidOrders12() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processAllValidOrders13() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processAllValidOrders14() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }
//
//  @Test
//  def processAllValidOrders15() {
//    val testFile = createTestOrdersFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(Nil, orderSummary.invalidOrders)
//  }

    //  @Test
//  def testIsolationProblem() {
//    val testFile = createFile(file, 5, 0)
//    val orderSummary = orderProcessor.processOrders(testFile)
//    assertEquals(5, orderSummary.savedOrders.size)
//    assertEquals(0, orderSummary.invalidOrders.size)
//    file.delete()
//  }

  
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

  def invalidOrderJson(i:Int) = {
    orderJson(i).replace("IA","")
  }

  def createTestOrdersFile(file:File, validOrders:Int, invalidOrders:Int):File = {
    doWithWriter(file, writer => {
      (1 to validOrders).foreach((i:Int) => writer.write(orderJson(i) + "\n"))
      (1 to invalidOrders).foreach((i:Int) => writer.write(invalidOrderJson(i) + "\n"))
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
