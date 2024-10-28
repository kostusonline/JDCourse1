// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Main {
    public static final String DIVIDER = " -------------------------------------";
    public static final String LANG_CODE = "RU";

    public static void main(String[] args) throws ParseException {
        final var charset = System.out.charset();
        System.out.printf("[charset: %s]%n", charset);
        final var os = new PrintWriter(System.out, true, charset);

        final DecimalFormat currencyFormat;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.of(LANG_CODE));
        if (numberFormat instanceof DecimalFormat df) {
            currencyFormat = df;
            df.setPositiveSuffix(" руб.");
            var s1 = df.format(100000);
            var p1 = df.parse("100 000,00");
        }
        else {
            currencyFormat = new DecimalFormat("# ##.###0.00");
        }
    }
}