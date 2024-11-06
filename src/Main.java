// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import java.io.PrintWriter;
import java.text.DecimalFormat;

public class Main {

    private static PrintWriter out;
    private static DecimalFormat currencyFormat;

    private static void init() {
        final var charset = System.out.charset();
        System.out.printf("[charset: %s]%n", charset);
        out = new PrintWriter(System.out, true, charset);
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

        SalaryVerifier salaryVerifier = new SalaryVerifier();
        NameVerifier nameVerifier = new NameVerifier();

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
                new Salary(salaryVerifier, 150_000, currencyFormat));

        employees[i++] = new Employee(
                new Person(nameVerifier, "Лебедева Елена Петровна", "05.02.1990", 'Ж'),
                new Division("3"),
                new Salary(salaryVerifier, 18_450, currencyFormat));

        out.println("Исходная информация:");
        for (Employee employee : employees) {
            if (employee != null) {
                out.print("\t");
                out.println(employee);
            }
        }
        out.println();

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

        out.println("Список имён сотрудников:");
        for (Employee employee : employees) {
            if (employee != null) {
                out.print("\t");
                out.println(employee.toShortString());
            }
        }
        out.println();
    }
}