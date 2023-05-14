import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Aditya SwayamSiddha
 */

class Employee {
  private String name, jobPosition, contactNumber, hireDate;
  private int empId;
  private double salary;
  private Address address;

  Employee(int empId, double salary, String name, Address address, String hireDate, String jobPosition,
      String contactNumber) {
    this.empId = empId;
    this.salary = salary;
    this.name = name;
    this.address = address;
    this.hireDate = hireDate;
    this.jobPosition = jobPosition;
    this.contactNumber = contactNumber;
  }

  public String getName() {
    return name;
  }

  public int getEmpId() {
    return empId;
  }

  public double getSalary() {
    return salary;
  }

  public String getHireDate() {
    return hireDate;
  }

  public String getJobPosition() {
    return jobPosition;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public Address getAddress() {
    return address;
  }

  public String toString() {
    return "Employee{" +
        "name='" + name + '\'' +
        ", empId=" + empId +
        ", salary=" + salary +
        ", hireDate=" + hireDate +
        ", jobPosition='" + jobPosition + '\'' +
        ", contactNumber='" + contactNumber + '\'' +
        ", address='" + address + '\'' +
        '}';
  }
}

class Address {
  private String street;
  private String city;
  private String state;
  private String country;
  private String zipCode;

  public Address(String street, String city, String state, String country, String zipCode) {
    this.street = street;
    this.city = city;
    this.state = state;
    this.country = country;
    this.zipCode = zipCode;
  }

  public String getStreet() {
    return street;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getZipCode() {
    return zipCode;
  }

  public String toString() {
    String res = city + " " + state + " " + country + " " + zipCode;
    return res;
  }
}

class Test {
  public static void arrangeEmployeeBySalary(Employee[] e) {
    for (int i = 0; i < e.length; i++) {
      for (int j = 0; j < e.length; j++) {
        if (e[j].getSalary() < e[i].getSalary()) {
          Employee temp = e[i];
          e[i] = e[j];
          e[j] = temp;
        }
      }
    }
    for (Employee employee : e) {
      System.out.println(employee.toString());
    }
  }

  public static void getEmployeesByJobPosition(Employee[] e, String jp) {
    for (Employee employee : e) {
      if (employee.getJobPosition().equalsIgnoreCase(jp)) {
        System.out.println("Name: " + employee.getName() + " is a manager");
      }
    }
  }

  public static void getEmployeesByHireDate(Employee e[], String d1, String d2) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    for (Employee employee : e) {
      String hireDateStr = employee.getHireDate();
      try {
        Date hireDate = dateFormat.parse(hireDateStr);
        Date startDate = dateFormat.parse(d1);
        Date endDate = dateFormat.parse(d2);
        if (hireDate.after(startDate) && hireDate.before(endDate)) {
          System.out.println("Name: " + employee.getName());
        }
      } catch (Exception ex) {
        System.out.println("Invalid date format for employee " + employee.getName());
      }
    }
  }

  public static int foreignEmployeeCount(Employee e[]) {
    int count = 0;
    for (Employee employee : e) {
      String countryCode = employee.getContactNumber().substring(0, 2);
      if (!countryCode.equals("91")) {
        count++;
      }
    }
    return count;
  }

  public static void getEmployeesBySalary(Employee e[], double s1, double s2) {
    for (Employee employee : e) {
      double salary = employee.getSalary();
      if (salary >= s1 && salary <= s2) {
        System.out.println("Name: " + employee.getName() + ", Salary: " + salary);
      }
    }
  }

  public static void main(String args[]) {
    Employee employees[] = new Employee[500];
    Scanner sc = new Scanner(System.in);

    for (int i = 0; i < employees.length; i++) {
      System.out.println("Information of " + (i + 1) + " employee");

      System.out.print("Enter your employee id : ");
      int empId = sc.nextInt();

      sc.nextLine();
      System.out.print("Enter your name : ");
      String name = sc.nextLine();

      System.out.print("Enter your salary: ");
      double salary = sc.nextDouble();

      sc.nextLine();
      System.out.println("Enter your address : ");
      System.out.print("Street: ");
      String street = sc.nextLine();
      System.out.print("City: ");
      String city = sc.nextLine();
      System.out.print("State: ");
      String state = sc.nextLine();
      System.out.print("Country: ");
      String country = sc.nextLine();
      System.out.print("Zip code: ");
      String zipCode = sc.nextLine();
      Address address = new Address(street, city, state, country, zipCode);

      System.out.print("Enter your hire date (dd-MM-yyyy) : ");
      String hireDate = sc.nextLine();

      System.out.print("Enter your job position : ");
      String jobPosition = sc.nextLine();

      System.out.print("Your contact number : ");
      String conactNumber = sc.nextLine();

      employees[i] = new Employee(empId, salary, name, address, hireDate, jobPosition, conactNumber);

    }

    System.out.println("Details of the employee salary in descending order ");
    arrangeEmployeeBySalary(employees);

    System.out.println("____________________________________________________");

    System.out.println("Details of employees whose jobPosition is manager");
    getEmployeesByJobPosition(employees, "manager");

    System.out.println("____________________________________________________");

    System.out.println("Details of employees whose hireDate is between 01-04-2022 to 31-03-2023");
    getEmployeesByHireDate(employees, "01-04-2022", "31-03-2023");

    System.out.println("____________________________________________________");

    int foreignEmployeeCount = foreignEmployeeCount(employees);
    System.out.println("Number of foreign employees: " + foreignEmployeeCount);

    System.out.println("____________________________________________________");

    System.out.println("Details of employees whose salary is in a range 150000 INR to 300000 INR: ");
    getEmployeesBySalary(employees, 150000.0, 300000.0);
    sc.close();
  }
}