// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package person.base;

import verifiable.ExceptionVerify;
import person.Gender;
import person.Person;
import verifiable.Verifiable;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class PersonBase implements Person {

    public final Verifiable<String> nameVerifier;

    // ФИО

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws ExceptionVerify {
        if (nameVerifier != null && !nameVerifier.isGood(lastName)) {
            throw new ExceptionVerify("Ошибка установки фамилии");
        }
        this.lastName = lastName;
    }

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws ExceptionVerify {
        if (nameVerifier != null && !nameVerifier.isGood(firstName)) {
            throw new ExceptionVerify("Ошибка установки имени");
        }
        this.firstName = firstName;
    }

    private String middleName;

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) throws ExceptionVerify {
        if (nameVerifier != null && !nameVerifier.isGood(middleName)) {
            throw new ExceptionVerify("Ошибка установки отчества");
        }
        this.middleName = middleName;
    }

    // Дата рождения.
    // Предпочтение отдано простому хранению в int т.к.
    // в Calendar или в *Date* можно преобразовать всегда (для проверки валидности, например).

    private int birthYear;
    private int birthMonth;
    private int birthDay;

    public String getBirthDate(DateTimeFormatter dtFormatter) {
        String result;
        LocalDate date = null;
        try {
            date = LocalDate.of(birthYear, birthMonth, birthDay);
            result = date.format(dtFormatter);
        } catch (Exception ex) {
            result = "Ошибка определения даты рождения";
        }
        return result;
    }

    public void setBirthDate(int year, int month, int day) throws DateTimeException {
        LocalDate date = LocalDate.of(year, month, day);
        birthYear = year;
        birthMonth = month;
        birthDay = day;
    }

    // Пол.

    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // Инициализация

    public PersonBase(Verifiable<String> nameVerifier, String lastName, String firstName, String middleName,
                      int birthYear, int birthMonth, int birthDay,
                      Gender gender) throws ExceptionVerify, DateTimeException {
        this.nameVerifier = nameVerifier;
        setLastName(lastName);
        setFirstName(firstName);
        setMiddleName(middleName);

        setBirthDate(birthYear, birthMonth, birthDay);
        setGender(gender);
    }
}
