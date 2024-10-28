// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package person;

import verifiable.ExceptionVerify;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public interface Person {
    // Здесь может быть ID: серия и номер паспорта, СНИЛС и т.п.,
    // но для курсовой работы это слишком дискуссионный вопрос.

    // ФИО.
    // Может меняться, но это поведение не реализовано.
    // Может "не быть" отчества, но его всегда можно записать.

    String getLastName();

    void setLastName(String lastName) throws ExceptionVerify;

    String getFirstName();

    void setFirstName(String firstName) throws ExceptionVerify;

    String getMiddleName();

    void setMiddleName(String middleName) throws ExceptionVerify;

    // Дата рождения.
    // Вряд ли может меняться.

    String getBirthDate(DateTimeFormatter dtFormatter);

    void setBirthDate(int year, int month, int day) throws DateTimeException; // возможность исправления ошибки ввода

    // Пол.

    Gender getGender();

    void setGender(Gender gender); // возможность исправления ошибки ввода
}
