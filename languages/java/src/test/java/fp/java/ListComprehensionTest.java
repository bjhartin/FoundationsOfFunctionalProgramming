package fp.java;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.stream.Stream;

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
//
//    @Test
//    void partition(){
//        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//
//        assertArrayEquals(Stream.of([2, 4, 6, 8, 10], [1, 3, 5, 7, 9]) == numbers.split({it % 2 == 0})
//    }
//
//    // These methods don't take higher order functions,
//    // but are still common to FP languages
//
//    @Test
//    void flatten(){
//        Stream<Integer> numbers = Stream.of([2, 3], [4, 5, 6], [1, 10])
//
//        assertArrayEquals(Stream.of(2, 3, 4, 5, 6, 1, 10) == numbers.flatten()
//    }
//
//    @Test
//    void unique(){
//        Stream<Integer> numbers = Stream.of(3, 10, 3, 4, 5, 4)
//
//        assertArrayEquals(Stream.of(3, 10, 4, 5) == numbers.unique()
//    }
}
