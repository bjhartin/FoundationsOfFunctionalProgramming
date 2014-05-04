package fp.java;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.IntStream.range;

public class ListComprehensionTest {

    @Test
    public void filter(){
        Stream<Integer> numbers = Stream.of(1, 34, 63, 2, 81, 19, 45, 51, 11, 9, 10, 27);

        assertArrayEquals(Stream.of(63, 81, 45, 9, 27).toArray(),
                numbers.filter(n -> n % 9 == 0).toArray());
    }

    @Test
    public void map(){
        Stream<Integer> numbers = Stream.of(2, 3, 4, 5);

        assertArrayEquals(Stream.of(4, 6, 8, 10).toArray(),
                numbers.map(n -> n * 2).toArray());
    }

    @Test
    public void foldLeft(){
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertEquals((Integer) 55, numbers.reduce(0, (n, acc) -> n + acc));
    }

    @Test
    public void sort(){
        Stream<String> names = Stream.of("Kim W Green", "Brenda M Brown", "Tom K Smith",  "Paul A Jones");

        Comparator<String> middleInitial = (s1, s2) -> s1.split(" ")[1].compareTo(s2.split(" ")[1]);

        assertArrayEquals(Stream.of("Paul A Jones", "Tom K Smith", "Brenda M Brown", "Kim W Green").toArray(),
                names.sorted(middleInitial).toArray());
    }

    @Test
    public void javaForIterationReplacement() {
        List<Integer> numbers = Arrays.asList(2, 9, 10, 3, 49, 1);

        numbers.forEach(n -> System.out.println(n));

        // Better yet
        numbers.forEach(System.out::println);

        // No assertion
    }

    @Test
    public void javaForLoopRangeReplacement() {
        // Range statically imported from IntStream
        range(0, 9).forEach(System.out::println);

        // No assertion
    }


    // These methods don't take higher order functions,
    // but are still common to FP languages

//    @Test
//    public void flatten(){
//        // No flatten (or similar) method on Stream
//        // Could do it manually with reduce
//    }

//    @Test
//    public void partition(){
//        // No partition (or similar) method on Stream
//    }

    @Test
    public void unique(){
        Stream<Integer> numbers = Stream.of(3, 10, 3, 4, 5, 4);

        assertArrayEquals(Stream.of(3, 10, 4, 5).toArray(),
                numbers.distinct().toArray());
    }



}
