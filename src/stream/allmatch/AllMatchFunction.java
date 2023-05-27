package stream.allmatch;

import util.Employee;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllMatchFunction {
  public static void main(String[] args) {

    /* Find the Name of all the employee which have common skills */
    List<Employee> employees = Arrays.asList(
        new Employee("Sumit", "9056", new HashSet<>(Arrays.asList("Java", "Python", "Spring", "SpringBoot"))),
        new Employee("Rishabh", "9051", new HashSet<>(Arrays.asList("PHP", "Scala", "Spring", "SpringBoot"))),
        new Employee("Sunil", "9050", new HashSet<>(Arrays.asList("JavaScript", "Python", "Spring", "Django", "SpringBoot"))),
        new Employee("Pawan", "9052", new HashSet<>(Arrays.asList("CPP", "Python", "Spring", "Jenkins", "SpringBoot")))
    );

    Set<String> commonSkillSet = employees.stream()
        .map(Employee::getSkills)
        .reduce((s1, s2) -> {
          s1.retainAll(s2);
          return s1;
        })
        .orElse(new HashSet<>());

    boolean isAllHaveCommonSkillSet =
        !commonSkillSet.isEmpty() && employees.stream()
            .allMatch(emp -> emp.getSkills().containsAll(commonSkillSet));

    if (isAllHaveCommonSkillSet) {
      System.out.println("All Employee Have Common Skill Set");
    } else {
      System.out.println("All Employee Don't have common skill set");
    }
  }
}
