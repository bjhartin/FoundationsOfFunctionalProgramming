package fp.java;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.function.Function;

public class FirstClassFunctionsTest {
    String regularGreeter(String name) {
        return "Hello " + name + "!";
    }

    Function<String, String> greeter1 = (String name) -> {
        return "Hello " + name + "!";
    };

    // Alternate, more concise, syntax
    Function<String, String> greeter2 = (String name) -> "Hello " + name + "!";  // Hmm...smells like an expression

    @Test
    public void firstClassFunctionsAreValues() {
        assertTrue(greeter1 instanceof Function);
        assertTrue(greeter2 instanceof Function);
    }

    @Test
    public void firstClassFunctionsCanBeInvoked(){
        assertEquals("Hello you!", greeter1.apply("you"));  // Note the 'apply' syntax
        assertEquals("Hello you!", greeter2.apply("you"));
    }

    @Test
    public void firstClassFunctionsCanBeAssigned(){
        Function g = greeter1;
        assertTrue(g instanceof Function);
        assertEquals(g, greeter1);
        assertEquals(g.apply("you"), "Hello you!");
    }

    @Test
    public void regularFunctionsCanBeConvertedToValues(){
        // Uses Java 8's new 'method references'
        Function<String, String> g = this::regularGreeter;
        assertTrue(g instanceof Function);
        assertEquals(g.apply("you"), "Hello you!");
    }

    @Test
    public void functionObjectsDoNotPossessUniqueIdentity(){
        Function<String, String> f = this::regularGreeter;
        Function<String, String> g = this::regularGreeter;

        // In Java 8, these are first-class values, but
        // they do not _fully_ behave like other objects.
        // In particular, they do not have unique identities.

        assertNotSame(f, g);
        assertNotEquals(f, g);
    }
}
