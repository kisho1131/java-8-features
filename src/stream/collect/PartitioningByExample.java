package stream.collect;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningByExample {
  public static final Integer PARTITION_YEAR = 5;

  public static void main(String[] args) {
    List<Employees> employees = new ArrayList<>();
    employees.add(new Employees("Sumit", LocalDate.of(2021, 7, 21)));
    employees.add(new Employees("Jon", LocalDate.of(2007, 1, 21)));
    employees.add(new Employees("Bob", LocalDate.of(1998, 7, 21)));
    employees.add(new Employees("Henry", LocalDate.of(1992, 7, 21)));
    employees.add(new Employees("Jacob", LocalDate.of(1999, 5, 5)));
    employees.add(new Employees("Adam Mills", LocalDate.of(2022, 7, 21)));
    employees.add(new Employees("Alex Cary", LocalDate.of(2009, 9, 9)));
    employees.add(new Employees("David", LocalDate.of(2014, 5, 3)));
    employees.add(new Employees("Green", LocalDate.of(2015, 7, 6)));
    employees.add(new Employees("Richard", LocalDate.of(2018, 5, 16)));
    employees.add(new Employees("Steve", LocalDate.of(2020, 6, 20)));

    Map<Boolean, List<Employees>> seniorJuniorPartition =
        employees.stream()
            .collect(
                Collectors.partitioningBy(
                    emp -> emp.getDateOfHire().isBefore(LocalDate.now().minusYears(PARTITION_YEAR))
                )
            );

    System.out.println();
    List<Employees> seniorEmployee = seniorJuniorPartition.get(true);
    List<Employees> juniorEmployee = seniorJuniorPartition.get(false);
    System.out.println("Senior Employee : ");
    seniorEmployee.stream().forEach(emp -> System.out.print(" " + emp.getName()));
    System.out.println();
    System.out.println("Junior Employee :");
    juniorEmployee.stream().forEach(emp -> System.out.print(" " + emp.getName()));

  }
}

class Employees {
  private String name;
  private LocalDate dateOfHire;

  Employees(String name, LocalDate dateOfHire) {
    this.name = name;
    this.dateOfHire = dateOfHire;
  }

  public String getName() {
    return name;
  }

  public LocalDate getDateOfHire() {
    return dateOfHire;
  }
}
