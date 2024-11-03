// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package employee;

import person.Person;
import salary.Salary;

public interface Employee {
    int getId();

    Person getPerson();

    Division getDivision();

    void setDivision(Division division);

    Salary getSalary();

    void setSalary(Salary salary);

    String toShortString();
}
