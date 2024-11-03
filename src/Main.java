// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import employee.Division;
import employee.Employee;
import employee.base.EmployeeBase;
import person.Gender;
import person.base.PersonBase;
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
    public static final String CURRENCY_FORMAT_DEFAULT = "#,###.##";

    private static PrintWriter out;
    private static DecimalFormat currencyFormat;

    private static void init() {
        final var charset = System.out.charset();
        System.out.printf("[charset: %s]%n", charset);
        out = new PrintWriter(System.out, true, charset);
        currencyFormat = new DecimalFormat(CURRENCY_FORMAT_DEFAULT);
    }

    public static final int EMPLOYEE_COUNT_DEFAULT = 10;

    public static void main(String[] args) throws VerifyException {
        init();

        Employee[] employees = new Employee[EMPLOYEE_COUNT_DEFAULT];

        Verifiable<String> nameVerifier = new NameVerifier();
        Verifiable<BigDecimal> salaryVerifier = new SalaryVerifier();

        int i = 0;
        employees[i++] = new EmployeeBase(
                new PersonBase(nameVerifier, "Иванов Иван Иванович", "16.04.1974", "М"),
                "1", salaryVerifier, currencyFormat, 350_000);

        employees[i++] = new EmployeeBase(
                new PersonBase(nameVerifier, "Сидоров", "Семён", "Семёнович",
                        1964, 8, 6, Gender.MALE),
                Division.DIVISION_1,
                salaryVerifier, currencyFormat, new BigDecimal(144_500));

        employees[i++] = new EmployeeBase(
                new PersonBase(nameVerifier, "Лебедева Елена Петровна", "05.02.1990", "Ж"),
                Division.DIVISION_3, salaryVerifier, currencyFormat, new BigDecimal(18_300));

        // Получить список всех сотрудников со всеми имеющимися по ним данными
        // (вывести в консоль значения всех полей (toString))
        for (Employee employee : employees) {
            if (employee != null) {
                out.println(employee);
            }
        }
    }
}