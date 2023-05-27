package stream.map;

import util.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MapFunction {
  public static void main(String[] args) {
    List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

    List<Integer> wordLengths = words.stream()
        .map(String::length)
        .toList();
    System.out.println(wordLengths);

    List<Person> persons = Arrays.asList(
        new Person("Sumit", 25, "India"),
        new Person("Alex", 45, "America"),
        new Person("Carry", 65, "China"),
        new Person("Bob", 15, "Singapore"),
        new Person("James", 95, "London")
    );

    List<String> nameOfPerson = persons.stream()
        .filter(person -> person.getAge() > 25)
        .map(Person::getName)
        .toList();
    System.out.println(nameOfPerson);

    System.out.println("************** Java Stream ************");
    List<String> wordsGreaterThanFive = words.stream()
        .filter(str -> str.length() > 5)
        .collect(Collectors.toList());

    System.out.println(wordsGreaterThanFive);

  }
}
