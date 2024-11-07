// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Константин Терских, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Objects;

public class Main {
    private static PrintWriter out;
    private static DecimalFormat currencyFormat;

    private static void init() {
        final var charset = System.out.charset();
        System.out.printf("[charset: %s]%n", charset);
        out = new PrintWriter(System.out, true, charset);
    }

    private static final SalaryVerifier salaryVerifier = new SalaryVerifier();
    private static final NameVerifier nameVerifier = new NameVerifier();

    private static void testDivision() {
        out.println("testDivision()");

        var division1 = new Division("1");
        var division1a = new Division("1");
        var division2 = new Division("2");
        var division3 = new Division("3");

        out.println(division1);
        out.println(division2);
        out.println(division3);

        if (Objects.equals(division1, division1a)) {
            out.println("division1 equals division1a");
        } else {
            out.println("division1 not equals division1a");
        }

        if (Objects.equals(division1, division3)) {
            out.println("division1 equals division3");
        } else {
            out.println("division1 not equals division3");
        }

        out.println();
    }

    private static void testGender() {
        out.println("testGender()");

        var gender1 = new Gender('m');
        var gender2 = new Gender('ж');
        var gender1a = new Gender('М');
        var gender3 = new Gender('9');

        out.println(gender1);
        out.println(gender2);
        out.println(gender1a);
        out.println(gender3);

        if (Objects.equals(gender1, gender1a)) {
            out.println("gender1 equals gender1a");
        } else {
            out.println("gender1 not equals gender1a");
        }

        if (Objects.equals(gender1, gender3)) {
            out.println("gender1 equals gender3");
        } else {
            out.println("gender1 not equals gender3");
        }

        out.println();
    }

    private static void testSalary() {
        out.println("testSalary()");
        out.println();
    }

    private static void testPerson() {
        out.println("testPerson()");
        out.println();
    }

    private static void testEmployee() {
        out.println("testPerson()");

        Employee[] employees = new Employee[EMPLOYEE_COUNT_DEFAULT];

        int i = 0;
        employees[i++] = new Employee(
                new Person(nameVerifier, "Иванов Иван Иванович", "16.04.1974", 'М'),
                "1",
                salaryVerifier, 350_000);

        employees[i++] = new Employee(
                new Person(nameVerifier, "Сидоров", "Семён", "Семёнович",
                        1964, 8, 6, new Gender('М')),
                new Division("1"),
                new Salary(150_000, salaryVerifier, currencyFormat));

        employees[i++] = new Employee(
                new Person(nameVerifier, "Лебедева Елена Петровна", "05.02.1990", 'Ж'),
                new Division("3"),
                new Salary(18_450, salaryVerifier, currencyFormat));

        out.println("Исходная информация:");
        for (Employee employee : employees) {
            if (employee != null) {
                out.print("\t");
                out.println(employee);
            }
        }
        out.println();

        out.println("Список имён сотрудников:");
        for (Employee employee : employees) {
            if (employee != null) {
                out.print("\t");
                out.println(employee.toShortString());
            }
        }
        out.println();
    }

    public static final int EMPLOYEE_COUNT_DEFAULT = 10;

//    private record SalariesAndExpenses(BigDecimal salariesSum, BigDecimal salariesAverage,
//                                       Employee employeeWithMinSalary, Employee employeeWithMaxSalary) {
//        public SalariesAndExpenses() {
//            this(null, null, null, null);
//        }
//
//        @Override
//        public String toString() {
//            DecimalFormat currencyFormat = new DecimalFormat("#,###.##");
//            return String.format("\tСумма выплат: %s руб%n" +
//                            "\tСредняя выплата: %s руб%n" +
//                            "\tСотрудник с максимальной зарплатой: %s%n" +
//                            "\tСотрудник с минимальной зарплатой: %s",
//                    currencyFormat.format(salariesSum.doubleValue()),
//                    currencyFormat.format(salariesAverage.doubleValue()),
//                    employeeWithMaxSalary.toShortString(),
//                    employeeWithMinSalary.toShortString());
//        }
//    }

//    private static SalariesAndExpenses calculateSalariesAndExpenses(Employee[] employees)
//            throws VerifyException {
//        if (employees == null) {
//            return new SalariesAndExpenses();
//        }
//        int count = employees.length;
//        if (count == 0) {
//            return new SalariesAndExpenses();
//        }
//
//        BigDecimal salariesSum = new BigDecimal(0);
//        BigDecimal salariesAverage = new BigDecimal(0);
//
//        Employee employeeWithMinSalary = employees[0];
//        Employee employeeWithMaxSalary = employees[0];
//
//        int existsCount = 0;
//        for (Employee employee : employees) {
//            if (employee == null) {
//                continue;
//            }
//
//            existsCount++;
//
//            double salary = employee.getSalary().getValue();
//            salariesSum = salariesSum.add(BigDecimal.valueOf(salary));
//
//            if (salary < employeeWithMinSalary.getSalary().getValue()) {
//                employeeWithMinSalary = employee;
//            } else if (salary > employeeWithMaxSalary.getSalary().getValue()) {
//                employeeWithMaxSalary = employee;
//            }
//        }
//
//        salariesAverage = salariesSum.divide(BigDecimal.valueOf(existsCount), RoundingMode.HALF_UP);
//
//        return new SalariesAndExpenses(salariesSum, salariesAverage, employeeWithMinSalary, employeeWithMaxSalary);
//    }

    public static void main(String[] args) {
        init();

        testDivision();
        testGender();
        testSalary();
        testPerson();
        testEmployee();

//        var salariesAndExpenses = calculateSalariesAndExpenses(employees);
//        out.println("Статистика по зарплатам:");
//        out.println(salariesAndExpenses);
//        out.println();

//        double indexationPercent = 12;
//        for (Employee employee : employees) {
//            if (employee != null) {
//                employee.getSalary().performIndexation(indexationPercent);
//            }
//        }
//        out.println("Информация после индексации на " + indexationPercent + "%:");
//        for (Employee employee : employees) {
//            if (employee != null) {
//                out.print("\t");
//                out.println(employee);
//            }
//        }
//        out.println();
    }
}