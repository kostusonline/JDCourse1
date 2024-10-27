// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package employee;

// Отделы компании
public enum Divisions {
    DIVISION_1 {
        @Override
        public String toString() {
            return "Отдел 1";
        }
    },

    DIVISION_2 {
        @Override
        public String toString() {
            return "Отдел 2";
        }
    },

    DIVISION_3 {
        @Override
        public String toString() {
            return "Отдел 3";
        }
    },
}
