package fp.java;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ClosuresTest {
    Function<Integer, Integer> createAdder() {
        int x = 3;

        // Return a function that adds a number to 3
        return (Integer y) -> {
            return x + y;
        };
    }

    @Test
    public void closuresCanReferenceVariablesInTheirDefiningScope() {
        // x is not in scope here, but the closure retains its binding.
        Function<Integer, Integer> adder = createAdder();
        assertEquals((Integer) 8, adder.apply(5));
    }

    @Test
    public void closuresCanIntroduceSideEffects() {
        // We use ArrayList because it must be mutable
        ArrayList<String> myList = new ArrayList<>(Arrays.asList("a", "b", "c"));

        // We pass in a lambda/closure, which is really an implementation
        // of ListReturner, which takes no args and returns a list.
        assertEquals(Arrays.asList("b", "c"), removeFirst(() -> myList));
        assertEquals(Arrays.asList("b", "c"), myList);                                // Oops...it got modified!
    }

    List<String> removeFirst(ListReturner lr){
        List<String> theList = lr.returnList();
        theList.remove(0);
        return theList;
    }

    /*
        We need a functional interface because our lambda takes no arguments, so
        'Function' does not work.
     */

    @FunctionalInterface
    interface ListReturner {
        List<String> returnList();
    }
}
