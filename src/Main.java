import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) {

    System.out.println("Hello world!");

    IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
        .limit(10)
        .forEach(System.out::print);

    System.out.println();

    List<String> names = List.of("Bob", "James", "Virat", "Kholi", "Dhoni", "Manish", "Pandey");

    List<String> namesMap = names.stream()
        .map(String::toUpperCase)
        .sorted(Collections.reverseOrder())
        .collect(Collectors.toList());

    System.out.println("namemap = " + namesMap);

  }
}