package fp.java;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.function.Function;

public class HigherOrderFunctionsTest {
    Boolean isEven(Integer num) {
        return num % 2 == 0;
    }

    Function<Integer, Integer> multiplyBy(Integer multiplier) {
        return (Integer number) -> number * multiplier;
    }

    Boolean checkNumber(Integer number, Function<Integer, Boolean> check) {
        return check.apply(number);
    }

    @Test
    public void functionsCanTakeFunctionsAsParameters() {
        assertTrue(checkNumber(2, this::isEven));
        assertFalse(checkNumber(3, this::isEven));
    }

    @Test
    public void functionsCanBeAnonymous() {
        assertTrue(checkNumber(3, (Integer n) -> n % 2 != 0));  // Called lambda expressions in most languages
    }

    @Test
    public void functionsCanReturnFunctions() {
        Function<Integer, Integer> timesFive = multiplyBy(5);

        assertTrue(timesFive instanceof Function);
        assertEquals((Integer) 30, timesFive.apply(6));
    }
}
