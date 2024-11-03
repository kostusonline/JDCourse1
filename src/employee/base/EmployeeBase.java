// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package employee.base;

/* Выдержка из условий задания (copy/paste на 27.10.2024)
https://my.sky.pro/student-cabinet/stream-module/21417/course-final-work/materials

Базовая сложность
 Создать класс Employee, который содержит информацию о
    ФИО,
    отделе и
    зарплате сотрудника.
 Отделы для простоты должны быть названы от 1 до 5.

 Добавить статическую переменную-счетчик, которая будет отвечать за id.
 Добавить в класс Employee поле id, которое проставляется из счетчика, а затем счетчик увеличивает свое значение.

 Скрыть прямой доступ к полям класса Employee и добавить возможность получать значения полей (геттеры) и
 устанавливать значения полей отдела и зарплаты (сеттеры).

 Реализовать в классе Employee контракты equals и hashCode.

 По-умолчанию все поля должны передаваться через конструктор (кроме id) и заполняться в нем
 (включая id, который нужно получить из счетчика).

 Создать внутри класса Main метод main и поле типа Employee[10], которое будет выполнять роль "хранилища"
 для записей о сотрудниках.
 Создать статические методы, которые будут взаимодействовать с массивом из пункта 6 и возвращать результат:

 Получить список всех сотрудников со всеми имеющимися по ним данными (вывести в консоль значения всех полей (toString));
 Посчитать сумму затрат на ЗП в месяц;
 Найти сотрудника с минимальной ЗП;
 Найти сотрудника с максимальной ЗП;
 Подсчитать среднее значение зарплат (можно использовать для этого метод из пункта b);
 Распечатать ФИО всех сотрудников (метод ничего).
*/

import employee.Division;
import employee.Employee;
import person.Person;
import person.base.PersonBase;
import salary.Salary;
import verifiable.VerifyException;
import verifiable.Verifiable;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

public final class EmployeeBase implements Employee {
    private static int idTop = 0;

    public static int getIdTop() {
        return idTop;
    }

    private final int id;

    public int getId() {
        return id;
    }

    private final Person person;

    public Person getPerson() {
        return person;
    }

    private Division division;

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    private Salary salary;

    public Salary getSalary() { return salary; }

    public void setSalary(Salary salary) { this.salary = salary; }

    // Переопределим equals и hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeBase that = (EmployeeBase) o;
        return this.division.equals(that.division) && this.salary.equals(that.salary) && this.person.equals(that.person);
        // (От быстрых сравнений к медленным.)
    }

    @Override
    public int hashCode() {
        return hash;
    }

    private int hash;
    private boolean freezeUpdateHash;

    private void updateHash() {
        if (freezeUpdateHash) {
            return;
        }
        hash = Objects.hash(this.division, this.salary, this.person);
    }

    private final DecimalFormat currencyFormat; //

    public EmployeeBase(Person person, Division division, Salary salary) throws VerifyException {
        id = idTop++;

        this.person = person;

        setDivision(division);

        setSalary(salary);
    }

    public EmployeeBase(Person person, String divisionSign, Verifiable<BigDecimal> salaryVerifier, DecimalFormat currencyFormat, double salaryDouble) throws VerifyException {
        id = idTop++;

        this.person = person;

        setDivision(Division.getDivision(divisionSign));

        this.salaryVerifier = salaryVerifier;
        this.currencyFormat = currencyFormat;
        getSalary().setSalary(new BigDecimal(salaryDouble));
    }

    @Override
    public String toString() {
        return String.format("ID: %d, %s, отдел %s, вознаграждение: %s руб/мес", id, person, division, currencyFormat.format(salary.doubleValue()));
    }

    public String toShortString() {
        return String.format("%s %s %s", person.getLastName(), person.getFirstName(), person.getMiddleName());
    }
}
