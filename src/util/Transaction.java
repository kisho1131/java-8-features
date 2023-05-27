package util;

public class Transaction {
  private final int amount;
  private final String currency;

  public Transaction(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public int getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }
}