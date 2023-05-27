package stream.allmatch;

import util.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchAnyFunction {
  public static void main(String[] args) {
    List<String> stringList = Arrays.asList("apple", "banana", "apricot", "cherry", "sumit", "shiva");
    List<String> randomList = Arrays.asList("Sumit", "Shiva", "Shubham", "Sarita", "Sangita");

    // allMatch(Predicate<T> test) -> return true if all element matches the
    // predicate condition
    // anyMatch(Predicate<T> test) -> return true if any element matches the
    // predicate condition
    boolean isAvailable = randomList.stream()
        .allMatch(str -> str.startsWith("S"));

    boolean isStringStartWithS = stringList.stream()
        .anyMatch(str -> str.startsWith("s"));

    if (isStringStartWithS)
      System.out.println("<< Some String Start With 'S' >>");

    if (isAvailable)
      System.out.println("<< All String Start with 'S' >>");
    else
      System.out.println("<< All String Don't Start with 'S' >>");

    List<Integer> integerList = Arrays.asList(90, 12, 12, 12, 12, 12, 33, 31, 95, 23, 86, 66, 24);
    // Check if all the Number in the List is Even
    boolean isAllEven = integerList.stream()
        .allMatch(ele -> ele % 2 == 0);

    boolean isAnyEven = integerList.stream()
        .anyMatch(ele -> ele % 2 == 0);

    if (isAnyEven)
      System.out.println("Some Number are Even !!");
    if (isAllEven)
      System.out.println("All the Number are Even !!");

    // stream().count() -> return the count of the element of stream
    // this is terminal operation
    long countOfEven = integerList.stream()
        .filter(ele -> ele % 2 == 0)
        .count();
    System.out.println("No of Even Elements : " + countOfEven);

    // Stream<T> distinct() -> return the stream of distinct element
    // this is terminal operation. (according to Object.equals(Object))
    List<Integer> distinctElement = integerList.stream()
        .filter(ele -> ele % 2 == 0)
        .distinct()
        .toList();
    List<Integer> duplicateElement = integerList.stream()
        .filter(ele -> ele % 2 == 0)
        .toList();
    System.out.println("Duplicate Element : " + duplicateElement);
    System.out.println("Distinct Element : " + distinctElement);

    // Optional<T> findAny() -> return Optional<T> describing some element of the
    // stream this is terminal operation. or empty optional if stream is empty()
    // Do not guarantee any specific element, may return different element each time.

    List<Integer> integers = Arrays.asList(1, 3, 6, 7, 9, 0, 12, 5);
    Optional<Integer> evenNumber = integers.stream()
        .filter(ele -> ele % 2 == 0)
        .findAny();
    evenNumber.ifPresent(integer -> System.out.println("Even Number : " + integer));
    // return the Optional<T> first odd number
    Optional<Integer> firstOddNumber = integers.stream()
        .filter(ele -> ele % 2 != 0)
        .findFirst();
    firstOddNumber.ifPresent(integer -> System.out.println("First Odd Number : " + integer));

    // Stream<R> map(Function<T, R> function) -> Returns a stream consisting of the
    // results
    // Do the one to one mapping (processing) of the element
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
    List<Integer> nameLengthList = names.stream()
        .map(String::length)
        .toList();
    System.out.println("Name Array : " + nameLengthList);

    List<Integer> elementList = Arrays.asList(120, 230, 12, 34, 56, 77, 89, 87, 20, 37);
    List<Integer> elementDoubleList = elementList.stream()
        .map(ele -> ele * 2)
        .toList();
    System.out.println("Element Double List : " + elementDoubleList);

    elementList.stream()
        .filter(ele -> ele % 2 == 0)
        .mapToInt(ele -> ele * 2)
        .forEach(ele -> System.out.print(ele + " "));
    System.out.println();

    // reduce() -> use to reduce the stream to the binary operation
    // It returns Optional<T>
    List<Integer> numberList = Arrays.asList(12, 34, 67, 89, 323, 187, 34);

    Optional<Integer> maxReduceElement = numberList.stream()
        .reduce((ele1, ele2) -> ele1 > ele2 ? ele1 : ele2);

    Integer reduceMethodSum = numberList.stream()
        .reduce(0, Integer::sum);

    Optional<Integer> maxElementRef = numberList.stream()
        .reduce(Integer::max);

    System.out.println("Reduce Sum = " + reduceMethodSum);
    System.out.println("Max Element Reference = " + maxElementRef.get());
    System.out.println("Max Element = " + maxReduceElement.get());

    // Given the transaction class, return the map containing the currency -> total
    // amount in that currency
    Transaction transaction_1 = new Transaction(1230, "USD");
    Transaction transaction_2 = new Transaction(122, "INR");
    Transaction transaction_3 = new Transaction(1230, "INR");
    Transaction transaction_4 = new Transaction(560, "SGD");
    Transaction transaction_5 = new Transaction(9642, "YEN");
    Transaction transaction_6 = new Transaction(970, "RUB");
    List<Transaction> transactions = Arrays.asList(
        transaction_3,
        transaction_1,
        transaction_2,
        transaction_4,
        transaction_5,
        transaction_6);

    Map<String, Integer> currencySumMap = transactions.parallelStream()
        .collect(
            Collectors.groupingBy(
                Transaction::getCurrency,
                Collectors.reducing(0, Transaction::getAmount, Integer::sum)));

    System.out.println("Currency Sum Map = " + currencySumMap);

    // sorted() -> return the sorted stream based on the comparator function
    // Comparator<T> comparator

    List<Integer> listOfIntegers = Arrays.asList(1, 5, 9, 34, 12, 9, 44, 2);

    List<Integer> sortedList = listOfIntegers.stream()
        .sorted(Integer::compareTo)
        .toList();

    System.out.println("Sorted List = " + sortedList);
    System.out.println("Integer List = " + listOfIntegers);

    transactions.stream()
        .sorted(Comparator.comparingInt(Transaction::getAmount))
        .forEach(trans -> System.out.println(trans.getAmount() + " -> " + trans.getCurrency()));

    // skip(long n) -> skips the first n elements and return the stream for remaining numbers
    // returns Stream<T> remaining elements

    List<Integer> skipNumberList = Stream.of(21, 34, 56, 78, 90, 12, 34, 65)
        .skip(2)
        .sorted()
        .toList();
    System.out.println("Skip List = " + skipNumberList);

  }
}
