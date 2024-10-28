// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package employee;

import person.Person;
import verifiable.ExceptionVerify;

import java.math.BigDecimal;

public interface Employee {
    int getId();

    Person getPerson();

    Divisions getDivision();

    void setDivision(Divisions division);

    BigDecimal getSalary();

    void setSalary(BigDecimal salary) throws ExceptionVerify;
}
