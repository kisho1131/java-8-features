package stream.collect;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Example {
  public static void main(String[] args) {
    IntStream numbers = IntStream.range(1, 10); // 1, 2, 3, 4, 5, 6, 7, 8, 9

    // Example 1: Using mapToDouble and converting back to LinkedList<Double>
    LinkedList<Double> doubleList1 = numbers.filter(ele -> ele % 2 == 0)
        .mapToDouble(ele -> Math.sqrt(ele))
        .mapToObj(Double::valueOf) // Convert double to Double
        .collect(Collectors.toCollection(LinkedList::new));

    System.out.println("Example 1: " + doubleList1);

    // Reset the stream
    IntStream numbers1 = IntStream.range(1, 10);
    List<Integer> numberList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    // Example 2: Using map directly with LinkedList<Double>
    LinkedHashSet<Double> collectToDouble = numberList.stream()
        .filter(ele -> ele % 2 == 0)
        .map(Math::sqrt)
        .collect(Collectors.toCollection(LinkedHashSet::new));
    System.out.println("Collect to Double : " + collectToDouble);
  }
}
