// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Константин Терских, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Отдел компании.<br>
 * (Здесь должен быть enum c методами, но enum ещё не проходили.
 * С enum всё было бы немного компактнее и вообще логичней.)
 *
 * @author Константин Терских, kostus.online.1974@yandex.ru, 2024
 * @version 1.1
 */
public class Division {
    /**
     * Константы для названий отделов.
     */
    public static final String DIVISION_UNKNOWN = "-";
    public static final String DIVISION_1 = "1";
    public static final String DIVISION_2 = "2";
    public static final String DIVISION_3 = "3";
    public static final String DIVISION_4 = "4";
    public static final String DIVISION_5 = "5";

    /**
     * Название отдела
     */
    @NotNull
    private final String name;

    /**
     * Конструктор
     *
     * @param name название отдела.
     *             Устанавливает название отдела {@link Division#name}.
     */
    public Division(@NotNull String name) {
        this.name = switch (name) {
            case "1" /* ... */ -> DIVISION_1;
            case "2" /* ... */ -> DIVISION_2;
            case "3" /* ... */ -> DIVISION_3;
            case "4" /* ... */ -> DIVISION_4;
            case "5" /* ... */ -> DIVISION_5;
            default -> DIVISION_UNKNOWN;
        };
    }

    /**
     * Получение названия отдела {@link Division#name}.
     */
    @NotNull
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Division that = (Division) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
