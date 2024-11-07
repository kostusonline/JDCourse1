// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Константин Терских, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import org.jetbrains.annotations.NotNull;

/**
 * Валидатор имени.
 *
 * @author Константин Терских, kostus.online.1974@yandex.ru, 2024
 * @version 1.1
 */
public class NameVerifier {
    public static final String ALLOWED_CHARS_DEFAULT =
            "- абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                    "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int CHARS_MIN_DEFAULT = 1;
    public static final int CHARS_MAX_DEFAULT = 250;

    /**
     * Разрешённые символы.
     */
    @NotNull
    private final String allowedChars;

    private final int minLength;
    private final int maxLength;

    /**
     * Конструктор. Всё устанавливается в значения по умолчанию.
     */
    public NameVerifier() {
        this.allowedChars = ALLOWED_CHARS_DEFAULT;
        this.minLength = CHARS_MIN_DEFAULT;
        this.maxLength = CHARS_MAX_DEFAULT;
    }

    /**
     * Конструктор.
     *
     * @param allowedChars разрешённые символы
     * @param minLength    минимальная длина имени
     * @param maxLength    максимальная длина имени
     */
    public NameVerifier(@NotNull String allowedChars, int minLength, int maxLength) {
        this.allowedChars = allowedChars;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    /**
     * Единственный метод, который проверяет имя на валидность.
     *
     * @param str строка для проверки
     * @return результат проверки
     */
    public boolean isGood(String str) {
        if (str == null) {
            return false;
        }

        if (str.isEmpty() || str.trim().isEmpty()) {
            return false;
        }

        int count = str.length();

        if (count < minLength || count > maxLength) {
            return false;
        }

        if (allowedChars.isEmpty()) {
            return true;
        }

        for (int i = 0; i < count; i++) {
            var ch = str.charAt(i);
            if (allowedChars.indexOf(ch) < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Нормализация строки. Пока только капитализация с затрагиванием всех символов строки.<br>
     *
     * @param str ненормализованная строка; пример: иВаНОв
     * @return нормализованная строка; пример: Иванов;<br>
     * для null возвращает null, <br>
     * для пустой строки возвращает ту же строку,<br>
     * для строки с одним символом возвращает строку с нормализованным символом (и -> И)
     */
    public static String normalize(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim().toLowerCase();
        if (str.isEmpty()) {
            return str;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
