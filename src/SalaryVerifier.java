// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

public class SalaryVerifier {
    public static final double SALARY_MIN_DEFAULT = 10_000; // условный МРОТ
    public static final double SALARY_MAX_DEFAULT = 500_000; // да куда уже больше

    private final double minSalary;
    private final double maxSalary;

    public SalaryVerifier() {
        this.minSalary = SALARY_MIN_DEFAULT;
        this.maxSalary = SALARY_MAX_DEFAULT;
    }

    public SalaryVerifier(double minSalary, double maxSalary) {
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public boolean isGood(Double salary) {
        return minSalary <= salary && salary <= maxSalary;
    }
}
