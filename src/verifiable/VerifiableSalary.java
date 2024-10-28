// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package verifiable;

import java.math.BigDecimal;

public class VerifiableSalary implements Verifiable<BigDecimal> {
    public static final double SALARY_MIN_DEFAULT = 10_000; // условный МРОТ
    public static final double SALARY_MAX_DEFAULT = 500_000; // да куда уже больше

    private final BigDecimal minSalary;
    private final BigDecimal maxSalary;

    public VerifiableSalary(BigDecimal minSalary, BigDecimal maxSalary) {
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public boolean isGood(BigDecimal salary) {
        if (salary == null) {
            return false;
        }
        return salary.compareTo(minSalary) > 0 && salary.compareTo(maxSalary) < 0;
    }
}
