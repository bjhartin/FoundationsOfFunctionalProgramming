package fp.scala.exampleapp.domain

import org.junit.Test
import org.junit.Assert._

class LotsOfTests extends OrderProcessorTest {
  @Test
  def slowTest1() {
    val testFile = createTestOrdersFile(file(), 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }

  @Test
  def slowTest2() {
    val testFile = createTestOrdersFile(file(), 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }

  @Test
  def slowTest3() {
    val testFile = createTestOrdersFile(file(), 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }

  @Test
  def slowTest4() {
    val testFile = createTestOrdersFile(file(), 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }

  @Test
  def slowTest5() {
    val testFile = createTestOrdersFile(file(), 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }

  @Test
  def slowTest6() {
    val testFile = createTestOrdersFile(file(), 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }

  @Test
  def slowTest7() {
    val testFile = createTestOrdersFile(file(), 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }

  @Test
  def slowTest8() {
    val testFile = createTestOrdersFile(file(), 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }

  @Test
  def slowTest9() {
    val testFile = createTestOrdersFile(file(), 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }

  @Test
  def slowTest10() {
    val testFile = createTestOrdersFile(file(), 5, 0)
    val orderSummary = orderProcessor.processOrders(testFile)
    assertEquals(5, orderSummary.savedOrders.size)
    assertEquals(Nil, orderSummary.invalidOrders)
  }


}
