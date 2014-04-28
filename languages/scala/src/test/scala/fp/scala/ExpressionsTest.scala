package fp.scala

import org.junit.Test

class ExpressionsTest {
  @Test
  def ifStatementIsAnExpression(): Unit = {
    val x = if(true) {
      1
    } else {
      0
    }

    assert(x == 1)
  }
}
