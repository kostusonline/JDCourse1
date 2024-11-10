// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Константин Терских, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Основной класс приложения.
 *
 * @author Константин Терских, kostus.online.1974@yandex.ru, 2024
 * @version 1.1
 */
public class Main {
    // Создадим все объекты статически для простоты манипулирования ими.

    /**
     * Валидатор зарплаты.
     */
    private static final SalaryVerifier salaryVerifier = new SalaryVerifier();
    /**
     * Валидатор имени.
     */
    private static final NameVerifier nameVerifier = new NameVerifier();
    /**
     * Мужской пол.
     */
    private static final Gender male = new Gender('m');
    /**
     * Женский пол.
     */
    private static final Gender female = new Gender('f');

    private static final Person person0 = new Person(nameVerifier, "Иванов Иван Иванович", "06.02.1969", male);
    private static final Person person1 = new Person(nameVerifier, "Семёнов Пётр Петрович", "07.05.1945", male);
    private static final Person person2 = new Person(nameVerifier, "Шаталин Александр Александрович", "01.10.1954", male);
    private static final Person person3 = new Person(nameVerifier, "Бортник Климент Янович", "02.12.1978", male);
    private static final Person person4 = new Person(nameVerifier, "Волошин Ким Аркадьевич", "12.02.1996", male);
    private static final Person person5 = new Person(nameVerifier, "Семенович Анна Григорьевна", "01.03.1978", female);
    private static final Person person6 = new Person(nameVerifier, "Гунько Яков Семёнович", "10.12.1985", male);
    private static final Person person7 = new Person(nameVerifier, "Сурдин Александр Борисович", "23.07.2001", male);
    private static final Person person8 = new Person(nameVerifier, "Здунов Питер Ираклиевич", "20.06.1970", male);
    private static final Person person9 = new Person(nameVerifier, "Семиконечный Серафим Валерьевич", "11.02.1979", male);

    private static final Division division0 = new Division(Division.DIVISION_1);
    private static final Division division1 = new Division(Division.DIVISION_2);
    private static final Division division2 = new Division(Division.DIVISION_3);
    private static final Division division3 = new Division(Division.DIVISION_4);
    private static final Division division4 = new Division(Division.DIVISION_5);

    private static final Employee employee0 = new Employee(person0, division0, salaryVerifier, 150_000);
    private static final Employee employee1 = new Employee(person1, division0, salaryVerifier, 160_000);

    private static final Employee employee2 = new Employee(person2, division1, salaryVerifier, 170_000);
    private static final Employee employee3 = new Employee(person3, division1, salaryVerifier, 250_000);
    private static final Employee employee4 = new Employee(person4, division1, salaryVerifier, 55_600);

    private static final Employee employee5 = new Employee(person5, division2, salaryVerifier, 1_380_200);

    private static final Employee employee6 = new Employee(person6, division3, salaryVerifier, 350_000);
    private static final Employee employee7 = new Employee(person7, division3, salaryVerifier, 120_000);

    private static final Employee employee8 = new Employee(person8, division4, salaryVerifier, 110_000);
    private static final Employee employee9 = new Employee(person9, division4, salaryVerifier, 110_000);

    /**
     * Конструктор.
     */
    public Main() {
    }

    /**
     * Объект для вывода результатов.
     */
    private static PrintWriter out;
    /**
     * Формат вывода чисел пл умолчанию.
     */
    private static DecimalFormat currencyFormat;

    /**
     * Тест отдела {@link Division}.
     */
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

    /**
     * Тест пола {@link Gender}.
     */
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

    /**
     * Тест зарплаты {@link Salary}.
     */
    private static void testSalary() {
        out.println("testSalary()");

        var salary1 = new Salary(150_000, salaryVerifier, currencyFormat);
        var salary1a = new Salary(150_000, salaryVerifier, currencyFormat);
        var salary2 = new Salary(250_000, salaryVerifier, currencyFormat);
        var salary3 = new Salary(350_000, salaryVerifier, currencyFormat);

        if (Objects.equals(salary1, salary1a)) {
            out.println("salary1 equals salary1a");
        } else {
            out.println("salary1 not equals salary1a");
        }
        if (Objects.equals(salary1, salary2)) {
            out.println("salary1 equals salary2");
        } else {
            out.println("salary1 not equals salary2");
        }

        out.println("salary1 = " + salary1a.getValue());
        out.println("salary2 = " + salary2);

        salary3.setValue(360_000);
        out.println("salary3 now = " + salary3);

        salary2.performIndexing(30);
        out.println("salary2 now = " + salary2);

        out.println();
    }

    /**
     * Тест персоны {@link Person}.
     */
    private static void testPerson() {
        out.println("testPerson()");

        var person1 = new Person(nameVerifier, "ивАнов", "Иван", "иванович",
                1964, 2, 13, male);
        var person2 = new Person(nameVerifier, "Сидоров Семён    семёнович", "19.05.1946", male);
        var person2a = new Person(nameVerifier, "Сидоров Семён Семёнович", "19.05.1946", male);
        var person3 = new Person(nameVerifier, "Собакевич Собака    Собакович", "29.04.1967", male);

        if (Objects.equals(person2, person2a)) {
            out.println("person2 equals person2a");
        } else {
            out.println("person2 not equals person2a");
        }

        if (Objects.equals(person1, person2)) {
            out.println("person1 equals person2");
        } else {
            out.println("person1 not equals person2");
        }

        person3.setBirthDate(1947, 5, 9);
        out.println("person3 = " + person3);

        out.println("person2 = " + person2.toStringShort());
        out.println();
    }

    /**
     * Тест сотрудника {@link Employee}.
     */
    private static void testEmployee() {
        out.println("testEmployee()");

        Employee[] employees = new Employee[EMPLOYEE_COUNT_DEFAULT];

        int i = 0;
        employees[i++] = new Employee(
                new Person(nameVerifier, "Иванов Иван Иванович", "16.04.1974", male),
                division0,
                salaryVerifier, 350_000);

        employees[i++] = new Employee(
                new Person(nameVerifier, "Сидоров", "Семён", "Семёнович",
                        1964, 8, 6, new Gender('М')),
                new Division("1"),
                new Salary(150_000, salaryVerifier, currencyFormat));

        employees[i++] = new Employee(
                new Person(nameVerifier, "Лебедева Елена Петровна", "05.02.1990", female),
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

    /**
     * Вместимость хранилища записей сотрудников.
     */
    public static final int EMPLOYEE_COUNT_DEFAULT = 10;

    /**
     * Книга сотрудников.
     */
    private static final EmployeeBook employeeBook = new EmployeeBook(EMPLOYEE_COUNT_DEFAULT);

    /**
     * Точка входа.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // Создаём поток вывода
        final var charset = System.out.charset();
        System.out.printf("[charset: %s]%n", charset);
        out = new PrintWriter(System.out, true, charset);

        // Запускаем тесты
        testDivision();
        testGender();
        testSalary();
        testPerson();
        testEmployee();

        // ...
    }
}