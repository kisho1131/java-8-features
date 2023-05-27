package stream.reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReduceStream {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);

    // Double the even values and put it in the list
    // wrong way to do this
    List<Integer> doubleOfEvenBad = new ArrayList<>();
    numbers.stream()
        .filter(e -> e % 2 == 0)
        .map(e -> e * 2)
        .forEach(e -> doubleOfEvenBad.add(e));

    System.out.println(" Double List [Bad Way] : " + doubleOfEvenBad);
    /* mutability is OK, Sharing is OK, shared mutability is devils work */

    List<Integer> doubleOfEvenGood =
        numbers.stream()
            .filter(e -> e % 2 == 0)
            .map(e -> e * 2)
            .collect(Collectors.toList());
    System.out.println("Double List [Good Way] : " + doubleOfEvenGood);

    numbers.stream()
        .filter(ele -> ele % 2 == 0)
        .sorted()
        .distinct()
        .forEach(System.out::print);
    System.out.println();

    /* List<Integer> numbers = Arrays.asList(1,2,3,4,5,1,2,3,4,5); */
    /* Takes BinaryOperator<T> accumulator as argument and return optional<T> */

    Integer product = 1;
    for (Integer ele : numbers) {
      product *= ele;
    }
    System.out.println("Product From Declarative way: " + product);

    /* ================ Optional<T> reduce(<BinaryOperator<T> accumulate) ================== */
    Optional<Integer> reduce_1 = numbers.stream()
        .reduce((ele1, ele2) -> ele1 * ele2);

    /* do the multiplication of ele1 and ele2 and put it back to ele1
     * in the next iteration ele1 will be multiplied with the next value in the stream */

    System.out.println("Reduce: " + reduce_1.get());

    /* ================ T reduce(T identity, BinaryOperator<T> accumulate) ================= */
    /* It Takes T as identity and BinaryOperator<T> as argument, identity is the starting or the
     * initial value of the onto which the result will be applied */
    
    Integer reduce_2 = numbers.stream()
        .reduce(10, (ele1, ele2) -> ele1 * ele2);
    System.out.println("Overloaded Reduce Operation: " + reduce_2);

    List<String> courseName = Arrays.asList("Java", "Core Java", "Hibernate", "Spring Boot", "Spring", "DesignPatterns");

    Optional<String> longestCourseName = courseName.stream()
        .reduce((str1, str2) -> str1.length() > str2.length() ? str1 : str2);

    System.out.println("Longest Course Name: " + longestCourseName.get());

    /* ================ <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner) ================= */
    /* combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)  */

  }

  public static int computeSum(int k, int n) {
    /* Imperative way of computing the value */
    int result = 0;
    int index = k;
    int count = 0;
    while (count < n) {
      if (index % 2 == 0 && Math.sqrt(index) > 20) {
        result += index * 2;
        count++;
      }
      index++;
    }
    System.out.println(result);

    /* using Stream and declarative way of computing */
    return Stream.iterate(k, e -> e + 1)
        .filter(e -> e % 2 == 0)
        .filter(e -> Math.sqrt(e) > 20)
        .mapToInt(e -> e * 2)
        .limit(n)
        .sum();
  }
}
