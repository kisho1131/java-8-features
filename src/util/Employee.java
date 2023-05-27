package util;

import java.util.Set;

public class Employee {
  private final String name;
  private final String empId;
  private final Set<String> skills;

  public Employee(String name, String empId, Set<String> skills) {
    this.name = name;
    this.empId = empId;
    this.skills = skills;
  }

  public String getName() {
    return name;
  }

  public String getEmpId() {
    return empId;
  }

  public Set<String> getSkills() {
    return skills;
  }

}
