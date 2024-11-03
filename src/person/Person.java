// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package person;

import verifiable.VerifyException;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public interface Person {
    // ФИО.

    String getLastName();

    void setLastName(String lastName) throws VerifyException;

    String getFirstName();

    void setFirstName(String firstName) throws VerifyException;

    String getMiddleName();

    void setMiddleName(String middleName) throws VerifyException;

    // Дата рождения.

    String getBirthDate(DateTimeFormatter dtFormatter);

    void setBirthDate(int year, int month, int day) throws DateTimeException; // возможность исправления ошибки ввода

    // Пол.

    Gender getGender();

    void setGender(Gender gender); // возможность исправления ошибки ввода
}
