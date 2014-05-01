package fp.groovy

import org.junit.Test

import java.awt.TexturePaintContext

class ListComprehensionTest {

  @Test
  void filterTest(){
    List<Integer> numbers = [1, 34, 63, 2, 81, 19, 45, 51, 11, 9, 10, 27]

    assert [63, 81, 45, 9, 27] == numbers.findAll({it % 9 == 0})
  }

  @Test
  void mapTest(){
    List<Integer> numbers = [2, 3, 4, 5]

    assert [4, 6, 8, 10] == numbers.collect({it * 2})
  }

  @Test
  void foldLeftTest(){
    List<Integer> numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    assert 55 == numbers.inject(0, {accumulator, num -> accumulator + num})
  }

  @Test
  void sortTest(){
    List<String> names = ["Kim W Green", "Brenda M Brown", "Tom K Smith",  "Paul A Jones"]

    Closure middleInitial = {it.split(" ")[1]}

    assert ["Paul A Jones", "Tom K Smith", "Brenda M Brown", "Kim W Green"] == names.sort(middleInitial)
  }

  @Test
  void partitionTest(){
    List<Integer> numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    assert [[2, 4, 6, 8, 10], [1, 3, 5, 7, 9]] == numbers.split({it % 2 == 0})
  }

  // These methods don't take higher order functions,
  // but are still common to FP languages

  @Test
  void flattenTest(){
    List<Integer> numbers = [[2, 3], [4, 5, 6], [1, 10]]

    assert [2, 3, 4, 5, 6, 1, 10] == numbers.flatten()
  }

  @Test
  void uniqueTest(){
    List<Integer> numbers = [3, 10, 3, 4, 5, 4]

    assert [3, 10, 4, 5] == numbers.unique()
  }
}
