// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

public class Gender {
    public static final char NOT_SPECIFIED = '-';
    public static final char MALE = 'M';
    public static final char FEMALE = 'F';

    private final char gender;

    public Gender(char value) {
        gender = switch (value) {
            case MALE, 'm', 'М', 'м' -> MALE;
            case FEMALE,'f', 'Ж', 'ж'-> FEMALE;
            default -> NOT_SPECIFIED;
        };
    }

    public int getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return switch (gender) {
            case MALE -> "М";
            case FEMALE -> "Ж";
            default -> "-";
        };
    }
}
