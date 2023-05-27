package stream.reduce;

import util.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
* The reducing collector takes three arguments:
  An initial value of 0.
  A mapping function that extracts the transaction amount for each transaction.
  A combiner function that combines the results of two different sub-streams into a single result.
* */
public class ReduceInParallelStream {
  public static void main(String[] args) {
    List<Transaction> transactions = Arrays.asList(
        new Transaction(100, "USD"),
        new Transaction(200, "EUR"),
        new Transaction(300, "USD"),
        new Transaction(400, "EUR"),
        new Transaction(500, "USD")
    );

    Map<String, Integer> totalsByCurrency = transactions.parallelStream()
        .collect(
            Collectors.groupingBy(
                Transaction::getCurrency,
                Collectors.reducing(
                    0,
                    Transaction::getAmount,
                    Integer::sum
                )
            )
        );
    System.out.println(totalsByCurrency);
  }
}


