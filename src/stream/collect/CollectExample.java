package stream.collect;


import util.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectExample {
  public static void main(String[] args) {
    System.out.println("********* Collectors.toMap() *********");

    List<Book> bookList = new ArrayList<>();
    bookList.add(new Book("Deep Work", 2012, "ISBN2012DEEP"));
    bookList.add(new Book("Steve Jobs", 2013, "ISBN2013DEEP"));
    bookList.add(new Book("80/20 Principle", 2014, "ISBN2014DEEP"));
    bookList.add(new Book("The 10X Rule", 2015, "ISBN2015DEEP"));

    Map<String, String> bookMap = bookList.stream()
        .collect(Collectors.toMap(
            Book::getIsbn, // Key Mapper
            Book::getName // Value Mapper
        ));

    System.out.println("Book Map : " + bookMap);

  }
}