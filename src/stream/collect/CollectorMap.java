package stream.collect;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class CollectorMap {

  public static void main(String[] args) {
    List<String> nameList = Arrays.asList("Sumit", "Kumar", "Virat", "Kholi", "Ventakesh", "Bod");

    Map<String, Integer> nameLengthMap = nameList.stream()
        .collect(Collectors.toMap(
            name -> name, // Key Mapper
            name -> name.length() // Value Mapper
        ));
    System.out.println(nameLengthMap);

    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

    // Collecting into a List
    List<String> namesList = names.stream()
        .collect(Collectors.toList());

    // Collecting into a Set
    Set<String> namesSet = names.stream()
        .collect(Collectors.toSet());

    // Collecting into a specific type of List
    LinkedList<String> namesLinkedList = names.stream()
        .collect(Collectors.toCollection(LinkedList::new));

    // Collecting into a Map with a single key-value mapping
    Map<String, Integer> nameLengths = names.stream()
        .collect(Collectors.toMap(
            name -> name,           // key mapper
            name -> name.length()   // value mapper
        ));

    // Collecting into a Map with a merging function for duplicate keys
    Map<String, Integer> nameLengthsMerged = names.stream()
        .collect(Collectors.toMap(
            name -> name,           // key mapper
            name -> name.length(),  // value mapper
            (oldValue, newValue) -> newValue   // merge function
        ));

    // Collecting into a concurrent Map
    ConcurrentMap<String, Integer> nameLengthsConcurrent = names.parallelStream()
        .collect(Collectors.toConcurrentMap(
            name -> name,           // key mapper
            name -> name.length()   // value mapper
        ));

    // Collecting into a primitive array
    int[] nameLengthsArray = names.stream()
        .mapToInt(String::length)
        .toArray();

    // Collecting into a String
    String namesString = names.stream()
        .collect(Collectors.joining(", "));

    System.out.println("Names String : " + namesString);

    // Collecting into a summary statistics object
    IntSummaryStatistics nameLengthStats = names.stream()
        .collect(Collectors.summarizingInt(String::length));

    List<Employeee> employees = Arrays.asList(
        new Employeee(1, "Alice", 50000),
        new Employeee(2, "Bob", 60000),
        new Employeee(3, "Charlie", 70000),
        new Employeee(4, "David", 80000),
        new Employeee(5, "Eve", 90000)
    );

    Map<Integer, String> employeeMap = employees.stream()
        .collect(Collectors.toMap(
            employeee -> employeee.getId(),  // Key Mapper
            employeee -> employeee.getName() //  Value Mapper
        ));

    System.out.println("Employee Map : " + employeeMap);
  }
}
