package fp.groovy

import org.junit.Test
import static org.junit.Assert.*

class PartialApplicationAndCurryingTest {
    Closure addEm =  {x, y -> x + y}

    @Test
    void partiallyAppliedFunction() {
        // Groovy's 'curry' method(s) do not actually
        // create a chain


        assertEquals(3, addEm(1, 2))

        Closure addFive = addEm.curry(5)  // Fixed the rightmost argument (Change y to 2*y above to see)

        assertEquals(8, addFive(3))
    }
}
