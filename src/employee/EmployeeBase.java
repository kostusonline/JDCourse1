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

import java.math.BigDecimal;

public final class EmployeeBase implements Employee {
    // Работа с идентификаторами сотрудников

    private static int idTop = 0;

    public static int getIdTop() {
        return idTop;
    }

    private final int id;

    public int getId() {
        return id;
    }

    // ФИО сотрудника

    public final Verifiable<String> nameVerifier;

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) throws ExceptionVerify {
        if (nameVerifier != null && !nameVerifier.isGood(lastName)) {
            throw new ExceptionVerify("Ошибка установки фамилии");
        }
        this.lastName = lastName;
    }

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) throws ExceptionVerify {
        if (nameVerifier != null && !nameVerifier.isGood(lastName)) {
            throw new ExceptionVerify("Ошибка установки имени");
        }
        this.firstName = firstName;
    }

    private String middleName;

    public String getMiddleName() {
        return middleName;
    }

    private void setMiddleName(String middleName) throws ExceptionVerify {
        if (nameVerifier != null && !nameVerifier.isGood(lastName)) {
            throw new ExceptionVerify("Ошибка установки отчества");
        }
        this.middleName = middleName;
    }

    // Отдел сотрудника

    private Divisions division;

    public Divisions getDivision() {
        return division;
    }

    public void setDivision(Divisions division) {
        this.division = division;
    }

    // Вознаграждение сотрудника по договору

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

    // Инициализация записи сотрудника

    public EmployeeBase(VerifiableString nameVerifier, String lastName, String firstName, String middleName,
                        Divisions division,
                        VerifiableSalary salaryVerifier, BigDecimal salary) throws ExceptionVerify {
        id = idTop++;

        this.nameVerifier = nameVerifier;
        setLastName(lastName);
        setFirstName(firstName);
        setMiddleName(middleName);

        setDivision(division);

        this.salaryVerifier = salaryVerifier;
        setSalary(salary);
    }
}
