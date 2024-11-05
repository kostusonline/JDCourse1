// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

// Отделы компании

public class Division {
    public static final String DIVISION_UNKNOWN = "-";
    public static final String DIVISION_1 = "1";
    public static final String DIVISION_2 = "2";
    public static final String DIVISION_3 = "3";
    public static final String DIVISION_4 = "4";
    public static final String DIVISION_5 = "5";

    private final String name;

    public Division(String name) {
        this.name = switch (name) {
            case "1" /* ... */ -> DIVISION_1;
            case "2" /* ... */ -> DIVISION_2;
            case "3" /* ... */ -> DIVISION_3;
            case "4" /* ... */ -> DIVISION_4;
            case "5" /* ... */ -> DIVISION_5;
            default -> DIVISION_UNKNOWN;
        };
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
