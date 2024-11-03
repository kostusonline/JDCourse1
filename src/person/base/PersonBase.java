// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package person.base;

import verifiable.VerifyException;
import person.Gender;
import person.Person;
import verifiable.Verifiable;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class PersonBase implements Person {

    public final Verifiable<String> nameVerifier;

    // ФИО

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws VerifyException {
        if (nameVerifier != null && !nameVerifier.isGood(lastName)) {
            throw new VerifyException("Ошибка установки фамилии");
        }
        this.lastName = lastName;
        updateHash();
    }

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws VerifyException {
        if (nameVerifier != null && !nameVerifier.isGood(firstName)) {
            throw new VerifyException("Ошибка установки имени");
        }
        this.firstName = firstName;
        updateHash();
    }

    private String middleName;

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) throws VerifyException {
        if (nameVerifier != null && !nameVerifier.isGood(middleName)) {
            throw new VerifyException("Ошибка установки отчества");
        }
        this.middleName = middleName;
        updateHash();
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
        updateHash();
    }

    // Пол.

    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
        updateHash();
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

        PersonBase that = (PersonBase) o;
        // Сначала самые быстрые сравнения, потом строки.
        return this.birthYear == that.birthYear &&
                this.birthMonth == that.birthMonth &&
                this.birthDay == that.birthDay &&
                this.gender == that.gender &&
                this.lastName.equals(that.lastName) &&
                this.firstName.equals(that.firstName) &&
                this.middleName.equals(that.middleName);
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
        hash = Objects.hash(
                this.birthYear,
                this.birthMonth,
                this.birthDay,
                this.gender,
                this.lastName,
                this.firstName,
                this.middleName);
    }

    // Инициализация

    public PersonBase(Verifiable<String> nameVerifier, String lastName, String firstName, String middleName,
                      int birthYear, int birthMonth, int birthDay,
                      Gender gender) throws VerifyException, DateTimeException {
        freezeUpdateHash = true;

        this.nameVerifier = nameVerifier;
        setLastName(lastName);
        setFirstName(firstName);
        setMiddleName(middleName);

        setBirthDate(birthYear, birthMonth, birthDay);
        setGender(gender);

        freezeUpdateHash = false;
        updateHash();
    }

    public record Names(String lastName, String firstName, String middleName){}

    public static Names parseNames(String fullName) {
        String[] names = fullName.split(" ");
        return new Names(names[0], names[1], names[2]);
    }

    public record DateParts(int birthYear, int birthMonth, int birthDay){}

    public static DateParts parseDate(String birthDate) {
        String[] date = birthDate.split("\\.");
        return new DateParts(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
    }

    public PersonBase(Verifiable<String> nameVerifier, String fullName, String birthDate, String genderSign)
            throws VerifyException, DateTimeException {
        freezeUpdateHash = true;

        this.nameVerifier = nameVerifier;
        var names = parseNames(fullName);
        setLastName(names.lastName);
        setFirstName(names.firstName);
        setMiddleName(names.middleName);

        var dateParts = parseDate(birthDate);
        setBirthDate(dateParts.birthYear, dateParts.birthMonth, dateParts.birthDay);

        setGender(Gender.getGender(genderSign));

        freezeUpdateHash = false;
        updateHash();
    }
}
