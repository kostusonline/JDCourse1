// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import employee.Division;
import employee.Employee;
import employee.base.EmployeeBase;
import person.Gender;
import person.base.PersonBase;
import salary.Salary;
import salary.base.SalaryBase;
import verifiable.Verifiable;
import verifiable.VerifyException;
import verifiable.implementations.NameVerifier;
import verifiable.implementations.SalaryVerifier;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static final String DIVIDER = " -------------------------------------";

    private static PrintWriter out;
    private static DecimalFormat currencyFormat;

    private static void init() {
        final var charset = System.out.charset();
        System.out.printf("[charset: %s]%n", charset);
        out = new PrintWriter(System.out, true, charset);
    }

    public static final int EMPLOYEE_COUNT_DEFAULT = 10;

    public static void main(String[] args) throws VerifyException {
        init();

        Salary[] salaries = new Salary[3];

        Verifiable<Double> salaryVerifier = new SalaryVerifier();
//        salaries[0] = new SalaryBase(salaryVerifier, 150_000, currencyFormat);
//        salaries[1] = new SalaryBase(salaryVerifier, 12_300, currencyFormat);
//        salaries[2] = new SalaryBase(salaryVerifier, 350_000, currencyFormat);
//
//        for (Salary salary : salaries) {
//            out.println(salary);
//        }

        Employee[] employees = new Employee[EMPLOYEE_COUNT_DEFAULT];

        Verifiable<String> nameVerifier = new NameVerifier();

        int i = 0;
        employees[i++] = new EmployeeBase(
                new PersonBase(nameVerifier, "Иванов Иван Иванович", "16.04.1974", "М"),
                "1",
                salaryVerifier, 350_000);

        employees[i++] = new EmployeeBase(
                new PersonBase(nameVerifier, "Сидоров", "Семён", "Семёнович",
                        1964, 8, 6, Gender.MALE),
                Division.DIVISION_1,
                new SalaryBase(salaryVerifier, 150_000, currencyFormat));

        employees[i++] = new EmployeeBase(
                new PersonBase(nameVerifier, "Лебедева Елена Петровна", "05.02.1990", "Ж"),
                Division.DIVISION_3,
                new SalaryBase(salaryVerifier, 18_450, currencyFormat));

        // Получить список всех сотрудников со всеми имеющимися по ним данными
        // (вывести в консоль значения всех полей (toString))
        for (Employee employee : employees) {
            if (employee != null) {
                out.println(employee);
            }
        }

        for (Employee employee : employees) {
            if (employee != null) {
                employee.getSalary().performIndexation(12);
            }
        }

        for (Employee employee : employees) {
            if (employee != null) {
                out.println(employee);
            }
        }

        for (Employee employee : employees) {
            if (employee != null) {
                out.println(employee.toShortString());
            }
        }
    }
}