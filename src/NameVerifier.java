// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Константин Терских, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

public class NameVerifier {
    public static final String ALLOWED_CHARS_DEFAULT =
            "- абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                    "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int CHARS_MIN_DEFAULT = 1;
    public static final int CHARS_MAX_DEFAULT = 250;

    private final String allowedChars;
    private final int minLength;
    private final int maxLength;

    public NameVerifier() {
        this.allowedChars = ALLOWED_CHARS_DEFAULT;
        this.minLength = CHARS_MIN_DEFAULT;
        this.maxLength = CHARS_MAX_DEFAULT;
    }

    public NameVerifier(String allowedChars, int minLength, int maxLength) {
        this.allowedChars = allowedChars;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

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
}
