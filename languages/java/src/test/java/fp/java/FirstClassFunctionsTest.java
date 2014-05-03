package fp.java;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.function.Function;

public class FirstClassFunctionsTest {
    String regularGreeter(String name) {
        return "Hello " + name + "!";
    }

    /*
        In Java 8, higher order functions / lambdas are enabled through 'functional interfaces', i.e.
        interfaces with a single method.  Runnable is an example.  Any single method interface can
        be used as a functional interface, although the annotation @FunctionalInterface will cause
        the compiler to provide errors if the interface does not adhere to the basic requirements.

        Function<T, R> is a functional interface for an object with a single method, apply, that
        takes one argument and returns a result.

        For any functional interface, we can provide an anonymous function (lambda) or convert
        an existing method if it has the right signature.

        Java 8's also introduces the idea of 'method references', in which we can reference
        regular methods as Function objects using the '::' operator.  They can be bound to variables,
        although there seem to be some restrictions on using them inline.  See the tests below.
     */


    // We define this function using a lambda expression
    Function<String, String> greeter1 = (String name) -> {
        return "Hello " + name + "!";
    };

    // Alternate, more concise, syntax
    Function<String, String> greeter2 = (String name) -> "Hello " + name + "!";

    // Here we create a Runnable function - although 'function' is a loose
    // usage of the term since it has no arguments and returns nothing.
    Runnable runnable = () -> System.out.println("I'm running!");

    @Test
    public void firstClassFunctionsAreValues() {
        // assertTrue(this::regularGreeter instanceof Function);  // Cannot write this.
        // assertTrue(regularGreeter instanceof Function);  Or this.
        //
        // However, we may write this if the function is bound to a variable.
        // Also see 'regularFunctionsCanBeConvertedToValues'
        assertTrue(greeter1 instanceof Function);
        assertTrue(greeter2 instanceof Function);
    }

    @Test
    public void firstClassFunctionsCanBeInvoked(){
        assertEquals("Hello you!", greeter1.apply("you"));  // Note the 'apply' syntax
        assertEquals("Hello you!", greeter2.apply("you"));

        // assertEquals("Hello you!", this::regularGreeter.apply("a"));  // No
        // assertEquals("Hello you!", this::regularGreeter("a"));        // Nope
    }

    @Test
    public void firstClassFunctionsCanBeAssigned(){
        Function g = greeter1;
        assertEquals(g, greeter1);
        assertEquals(g.apply("you"), "Hello you!");
    }

    @Test
    public void regularFunctionsCanBeConvertedToValues(){
        Function<String, String> g = this::regularGreeter;
        assertEquals("Hello you!", g.apply("you"));
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
