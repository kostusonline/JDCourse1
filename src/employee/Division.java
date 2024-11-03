// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package employee;

// Отделы компании
public enum Division {
    DIVISION_UNKNOWN {
        @Override
        public String toString() {
            return "-";
        }
    },

    DIVISION_1 {
        @Override
        public String toString() {
            return "1";
        }
    },

    DIVISION_2 {
        @Override
        public String toString() {
            return "2";
        }
    },

    DIVISION_3 {
        @Override
        public String toString() {
            return "3";
        }
    },

    DIVISION_4 {
        @Override
        public String toString() {
            return "4";
        }
    },

    DIVISION_5 {
        @Override
        public String toString() {
            return "5";
        }
    };

    public static Division getDivision(String name){
        return switch (name) {
            case "1" -> DIVISION_1;
            case "2" -> DIVISION_2;
            case "3" -> DIVISION_3;
            case "4" -> DIVISION_4;
            case "5" -> DIVISION_5;
            default -> DIVISION_UNKNOWN;
        };
    }
}
