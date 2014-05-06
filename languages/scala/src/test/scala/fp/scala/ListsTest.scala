package fp.scala

import org.junit.Test
import org.junit.Assert._

class ListsTest {
  @Test
  def listComprehension() {
    val doubledEvents = for(n  <-  (1 to 10) if n % 2 == 0) yield n * 2
    assertEquals(List(4, 8, 12, 16, 20), doubledEvents)
  }

  @Test
  def filter() {
    val numbers = List(1, 34, 63, 2, 81, 19, 45, 51, 11, 9, 10, 27)

    assertEquals(List(63, 81, 45, 9, 27), numbers.filter({_ % 9 == 0}))
  }

  @Test
  def map() {
    val numbers = List(2, 3, 4, 5)

    assertEquals(List(4, 6, 8, 10), numbers.map({_ * 2}))
  }

  @Test
  def foldLeft() {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    assertEquals(55, numbers.foldLeft(0)((accumulator, num) => {accumulator + num}))
  }

  @Test
  def sort() {
    val names = List("Kim W Green", "Brenda M Brown", "Tom K Smith",  "Paul A Jones")

    val middleInitial = (name: String) => {name.split(" ")(1)} // Scala array access uses ()

    assertEquals(List("Paul A Jones", "Tom K Smith", "Brenda M Brown", "Kim W Green"), names.sortBy(middleInitial))
  }

  @Test
  def partition() {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    assertEquals((List(2, 4, 6, 8, 10), List(1, 3, 5, 7, 9)), numbers.partition({_ % 2 == 0}))
  }

  // These methods don't take higher order functions,
  // but are still common to FP languages

  @Test
  def flatten() {
    val numbers = List(List(2, 3), List(4, 5, 6), List(1, 10))

    assertEquals(List(2, 3, 4, 5, 6, 1, 10), numbers.flatten)
  }

  @Test
  def unique() {
    val numbers = List(3, 10, 3, 4, 5, 4)

    assertEquals(List(3, 10, 4, 5), numbers.distinct)
  }
}
