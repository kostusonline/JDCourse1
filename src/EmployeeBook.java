// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Константин Терских, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import java.io.PrintStream;

public class EmployeeBook {
    private final Employee[] employees;

    public EmployeeBook(Employee[] employees) {
        this.employees = employees;
    }

    public EmployeeBook(int capacity) {
        this.employees = new Employee[capacity];
    }

    public static final int NOT_FOUND = -1;

    private static int getFreeIndex(Employee[] employees) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public boolean addEmployee(Employee employee) {
        int freeIndex = getFreeIndex(employees);
        if (freeIndex == NOT_FOUND) {
            return false;
        }
        employees[freeIndex] = employee;
        return true;
    }

    public Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee != null && employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public void removeEmployee(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId() == id) {
                employees[i] = null;
                return;
            }
        }
    }

    public void printEmployees(PrintStream out) {
        for (Employee employee : employees) {
            if (employee != null) {
                out.print("\t");
                out.println(employee);
            }
        }
    }

    public double getSalarySum(Division division) {
        double sum = 0;
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }

            if (division != null && !employee.getDivision().equals(division)) {
                continue;
            }

            sum += employee.getSalary().getValue();
        }
        return sum;
    }

    private int getEmployeeCount(Division division) {
        int count = 0;
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }

            if (division != null && !employee.getDivision().equals(division)) {
                continue;
            }

            count++;
        }
        return count;
    }

    public double getSalaryAverage(Division division) {
        double sum = 0;
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }

            if (division != null && !employee.getDivision().equals(division)) {
                continue;
            }

            sum += employee.getSalary().getValue();
        }
        int employeesCount = getEmployeeCount(division);
        return sum / employeesCount;
    }

    private int getFirstIndex(Division division) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDivision().equals(division)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public Employee getEmployeePoorest(Division division) {
        int index = getFirstIndex(division);
        if (index == NOT_FOUND) {
            return null;
        }

        Employee poorest = employees[index];
        for (int i = index + 1; i < employees.length; i++) {
            if (employees[i] == null) {
                continue;
            }

            if (division != null && !employees[i].getDivision().equals(division)) {
                continue;
            }

            if (employees[i].getSalary().getValue() < poorest.getSalary().getValue()){
                poorest = employees[i];
            }
        }
        return poorest;
    }

    public Employee getEmployeeRichest(Division division) {
        int index = getFirstIndex(division);
        if (index == NOT_FOUND) {
            return null;
        }

        Employee richest = employees[index];
        for (int i = index + 1; i < employees.length; i++) {
            if (employees[i] == null) {
                continue;
            }

            if (division != null && !employees[i].getDivision().equals(division)) {
                continue;
            }

            if (employees[i].getSalary().getValue() > richest.getSalary().getValue()){
                richest = employees[i];
            }
        }
        return richest;
    }

    public void performSalaryIndexing(Division division, double positivePercentage) {
        if (positivePercentage < 0){
            throw new IllegalArgumentException("Параметр positivePercentage должен быть положительным числом");
        }

        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }

            if (division != null && !employee.getDivision().equals(division)) {
                continue;
            }

            employee.getSalary().performIndexing(positivePercentage);
        }
    }

    public static final int LESS_THAN = -1;
    public static final int EQUAL = 0;
    public static final int GREATER_THAN = 1;

    public Employee[] getEmployees(double salary, int compare, boolean inclusive) {
        return null;
    }
}
