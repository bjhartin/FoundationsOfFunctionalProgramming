package fp.groovy

import org.junit.Test

class ExpressionsTest {
  @Test
  void testExpression() {
    assert 1 == returnsAnAssignment()
  }


  int returnsAnAssignment() {
    int x = 1
  }
}
