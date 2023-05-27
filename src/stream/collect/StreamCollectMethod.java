package stream.collect;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectMethod {

  public static void main(String[] args) {

    // collect() -> used to perform the mutable reduction operation on the element of the stream
    // takes Collector as an argument and accumulates the elements of the stream into the container
    // according to the rules defined by the collector.

    // overloaded collect() method
    // 1. final<R, A> R collect(Collectors<?, super T, A, R> collector)
    // 2. <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)

    // Basics of collect()
    List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    List<Integer> doubleOfEvenNumbers = numbers.stream()
        .filter(ele -> ele % 2 == 0)
        .map(ele -> ele * 2)
        .collect(Collectors.toList());
    System.out.println("Double od even elements : " + doubleOfEvenNumbers);

    // overloaded form of collect method
    numbers.stream()
        .collect(() -> new ArrayList<Integer>(),  // Supplier
            (list, e) -> list.add(e),             // accumulator
            (list1, list2) -> list1.addAll(list2) // combiner
        );

    // if you want to collect the reduced stream to any other collection
    LinkedList<Double> collectInLinkedList = numbers.stream()
        .filter(ele -> ele % 2 == 0)
        .map(Math::sqrt)
        .collect(Collectors.toCollection(LinkedList<Double>::new));
    System.out.println("Collect Result in LinkedList : " + collectInLinkedList);

    // counting the number elements in the stream
    Long countEvenElement = numbers.stream()
        .filter(ele -> ele % 2 == 0)
        .collect(Collectors.counting());
    System.out.println("Even Elements  : " + countEvenElement);

    // Collectors.joining(String determiner, String prefix, String suffix) -> return the contacted string using the
    // delimiter and append the prefix string and suffix string at the first and last respectively
    String concactStringFromCollect =
        List.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven")
            .stream()
            .collect(Collectors.joining(" , ", "{ ", " }"));
    System.out.println("Contact String from collect : " + concactStringFromCollect);

    // partitioningBy(Predicate<T> predicate)
    // returns Map<Boolean, List<T>>
    // predicated takes condition on which the partitioning is being done
    Map<Boolean, List<Integer>> partitionByEvenOddMap = numbers.stream()
        .collect(Collectors.partitioningBy(ele -> ele % 2 == 0));
    System.out.println("Partition By Even & Odd Map : " + partitionByEvenOddMap);

    // Overloaded form of the partitionBy()
    // partitioningBy(Predicate<T> predicate, Collector<T,A,D> downstream)
    // we can pass the downstream collector  into which we can collect the result
    Map<Boolean, Set<Integer>> collectPartitionInSet = numbers.stream()
        .collect(Collectors.partitioningBy(ele -> ele > 3, Collectors.toSet()));
    System.out.println("Collect Partition in set : " + collectPartitionInSet);

    // groupingBy(Function<T> function) -> group by the given Function (Classifier)
    // Overloaded form -> groupingBy(Function<T> function, Collector<T, A, D> downstream)
    //          -> take the Collector as downstream into which w can collect after groupingBy()
    //          -> downstream  we can also pass the other requirements / Operations

    Map<Integer, List<String>> mapOfStringBasedOnLengthGraterThan3 =
        List.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven")
            .stream()
            .collect(Collectors.groupingBy(str -> str.length(), Collectors.filtering(str -> str.length() > 2, Collectors.toList())));
    System.out.println("Map of String With Length Greater Than 3 : " + mapOfStringBasedOnLengthGraterThan3);


    // Perform filtering while collecting
    // filtering / mapping / flatmapping are the intermediate collector we need to provide them downstream
    Map<Integer, String> collectAndFilterMap = Stream.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight")
        .collect(
            Collectors.filtering((str) -> str.length() > 3,
                Collectors.toMap(String::length, str -> str, (oldValue, newValue) -> oldValue + " & " + newValue))
        );
    System.out.println("Collecting & Filtering : " + collectAndFilterMap);


    // Filtering and Mapping (Note : mapping provide the one to one mapping )
    List<String> collectWithFilteringAndMapping =
        Stream.of("Name", "Last Name", "Designation", "Company", "Home", "Experience", "Manager")
            .collect(
                Collectors.filtering(str -> str.length() > 5, Collectors.mapping(String::toUpperCase, Collectors.toList()))
            );
    System.out.println("Collect with Mapping and Filtering : " + collectWithFilteringAndMapping);


  }
}

class CustomCollectorForList implements Collector<Integer, List<Integer>, List<Integer>> {

  // interface that doesn't take any input but return output
  // T get() -> method signature of supplier functional interface
  @Override
  public Supplier<List<Integer>> supplier() {
    return ArrayList::new;
  }

  // functional interface that accept two arguments as input and return void //
  // void accept(T, R) -> method signature of BiConsumer Function
  @Override
  public BiConsumer<List<Integer>, Integer> accumulator() {
    return List::add;
  }

  // functional interface that accept three arguments as input and return One arguments as output
  // R apply(T, U) -> method signature of BiFunction (Note : BinaryOperator extends the BiFunction)
  @Override
  public BinaryOperator<List<Integer>> combiner() {
    return (list1, list2) -> {
      list1.addAll(list2);
      return list1;
    };
  }

  // functional interface that accept one argument as input and return one arguments as output
  // R apply(T) -> method signature for Functional Interface
  @Override
  public Function<List<Integer>, List<Integer>> finisher() {
    return ele -> ele;
  }

  // enum of the characteristics are obtained form the Collector Interface
  @Override
  public Set<Characteristics> characteristics() {
    return Set.of(Characteristics.IDENTITY_FINISH);
  }
}
