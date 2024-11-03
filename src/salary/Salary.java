// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package salary;

import verifiable.VerifyException;

public interface Salary {
    double getValue() throws VerifyException;

    void setValue(double salary) throws VerifyException;

    void setIndexation(double percent) throws VerifyException;
}
