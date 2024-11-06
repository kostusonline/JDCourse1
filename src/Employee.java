// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import java.util.Objects;

/**
 * Сотрудник.
 *
 * @author Терских Константин, kostus.online.1974@yandex.ru, 2024
 * @version 1.1
 */
public final class Employee {
    /**
     * Статический счётчик создаваемых экземпляров классов.
     */
    private static int idTop = 0;

    /**
     * Получение значения счётчика {@link Employee#idTop}.
     */
    public static int getIdTop() {
        return idTop;
    }

    /**
     * Идентификатор сотрудника.<br>
     * Устанавливается один раз в конструкторе.
     */
    private final int id;

    /**
     * Получение id сотрудника {@link Employee#id}.<br>
     * Устанавливается неявно в конструкторе.
     */
    public int getId() {
        return id;
    }

    /**
     * Сотрудник. Экземпляр класса {@link Person}.<br>
     * Внедряется только через конструктор.
     */
    private final Person person;

    /**
     * Получение ссылки на экземпляр сотрудника {@link Employee#person}.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Отдел, к которому прикреплён сотрудник.<br>
     * Внедряется через конструктор, но может быть заменён в дальнейшем.
     */
    private Division division;

    /**
     * Получение отдела сотрудника {@link Employee#division}.
     */
    public Division getDivision() {
        return division;
    }

    /**
     * Установка отдела сотрудника {@link Employee#division}.
     */
    public void setDivision(Division division) {
        this.division = division;
    }

    /**
     * Зарплата сотрудника.<br>
     * Экземпляр внедряется только через конструктор, но параметры могут быть изменены в любой момент.
     */
    private Salary salary;

    /**
     * Получение зарплаты сотрудника {@link Employee#salary}.
     */
    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    // Переопределим equals и hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Employee that = (Employee) o;

        // От быстрых сравнений к медленным.
        return this.division.equals(that.division) &&
                this.salary.equals(that.salary) &&
                this.person.equals(that.person);
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
        hash = Objects.hash(this.division, this.salary.hashCode(), this.person.hashCode());
    }

    public Employee(Person person,
                    Division division,
                    Salary salary) {
        if (person == null) {
            throw new IllegalArgumentException("Параметр person не должен быть null");
        } else if (division == null) {
            throw new IllegalArgumentException("Параметр division не должен быть null");
        } else if (salary == null) {
            throw new IllegalArgumentException("Параметр salary не должен быть  null");
        }

        this.freezeUpdateHash = true;

        id = idTop++;

        this.person = person;
        setDivision(division);
        setSalary(salary);

        updateHash();
        this.freezeUpdateHash = false;
    }

    public Employee(Person person,
                    String divisionSign,
                    SalaryVerifier salaryVerifier, double salaryValue) {
        if (person == null) {
            throw new IllegalArgumentException("person == null");
        } else if (divisionSign == null) {
            throw new IllegalArgumentException("divisionSign == null");
        } else if (salaryVerifier == null) {
            throw new IllegalArgumentException("salaryVerifier == null");
        }

        this.freezeUpdateHash = true;

        id = idTop++;

        this.person = person;
        setDivision(new Division(divisionSign));
        salary = new Salary(salaryVerifier, salaryValue, null);

        updateHash();
        this.freezeUpdateHash = false;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, %s, отдел %s, %s", id, person, division, salary);
    }

    public String toShortString() {
        return String.format("%s %s %s", person.getLastName(), person.getFirstName(), person.getMiddleName());
    }
}
