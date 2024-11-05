// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class Person {

    public final NameVerifier nameVerifier;

    // ФИО

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (nameVerifier != null && !nameVerifier.isGood(lastName)) {
            return;
        }
        this.lastName = lastName;
        updateHash();
    }

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (nameVerifier != null && !nameVerifier.isGood(firstName)) {
            return;
        }
        this.firstName = firstName;
        updateHash();
    }

    private String middleName;

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (nameVerifier != null && !nameVerifier.isGood(middleName)) {
            return;
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

    public void setBirthDate(int year, int month, int day) {
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

        Person that = (Person) o;

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

    public Person(NameVerifier nameVerifier, String lastName, String firstName, String middleName,
                  int birthYear, int birthMonth, int birthDay, Gender gender) {
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

    public static String[] parseNames(String fullName) {
        String[] nameParts = fullName.split(" ");
        if (nameParts.length != 3) {
            return null;
        }
        return nameParts;
    }

    public static int[] parseDate(String birthDate) {
        String[] date = birthDate.split("\\.");
        if (date.length != 3) {
            return null;
        }

        int[] result = new int[3];
        result[0] = Integer.parseInt(date[0]);
        result[1] = Integer.parseInt(date[1]);
        result[2] = Integer.parseInt(date[2]);
        return result;
    }

    public Person(NameVerifier nameVerifier, String fullName, String birthDate, char genderSign) {
        freezeUpdateHash = true;

        this.nameVerifier = nameVerifier;
        var names = parseNames(fullName);
        assert names != null;
        setLastName(names[0]);
        setFirstName(names[1]);
        setMiddleName(names[2]);

        var dateParts = parseDate(birthDate);
        assert dateParts != null;
        setBirthDate(dateParts[2], dateParts[1], dateParts[0]);

        setGender(new Gender(genderSign));

        freezeUpdateHash = false;
        updateHash();
    }

    @Override
    public String toString() {
        return String.format("%s %s %s, %s, %02d.%02d.%d",
                lastName, firstName, middleName, gender, birthDay, birthMonth, birthYear);
    }
}
