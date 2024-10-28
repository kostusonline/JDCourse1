// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package person;

public enum Gender {
    NOT_SPECIFIED {
        @Override
        public String toString() {
            return "-";
        }
    },

    MALE {
        @Override
        public String toString() {
            return "М";
        }
    },

    FEMALE {
        @Override
        public String toString() {
            return "Ж";
        }
    },
}
