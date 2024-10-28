// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package employee;

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

import person.Person;
import verifiable.ExceptionVerify;
import verifiable.Verifiable;

import java.math.BigDecimal;

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

    private Divisions division;

    public Divisions getDivision() {
        return division;
    }

    public void setDivision(Divisions division) {
        this.division = division;
    }

    private final Verifiable<BigDecimal> salaryVerifier;

    // Базовое хранилище - BigDecimal
    // (для возможных последующих расширений валютной арифметики)
    private BigDecimal salary;

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) throws ExceptionVerify {
        if (salaryVerifier != null && !salaryVerifier.isGood(salary)) {
            throw new ExceptionVerify("Ошибка установки вознаграждения");
        }

        // будем думать, что КЗОТ работает и нельзя уменьшать размер вознаграждения
        if (this.salary != null && salary.compareTo(this.salary) < 0) {
            throw new ExceptionVerify("Назначаемое вознаграждение меньше установленного");
        }
        this.salary = salary;
    }

    public EmployeeBase(Person person, Divisions division,
                        Verifiable<BigDecimal> salaryVerifier, BigDecimal salary) throws ExceptionVerify {
        id = idTop++;

        this.person = person;

        setDivision(division);

        this.salaryVerifier = salaryVerifier;
        setSalary(salary);
    }
}
