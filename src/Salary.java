// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import java.text.DecimalFormat;
import java.util.Objects;

public final class Salary {
    private double salary;

    public double getValue() {
        return salary;
    }

    private final SalaryVerifier salaryVerifier;

    public void setValue(double newSalary) {
        if (salaryVerifier != null && !salaryVerifier.isGood(newSalary)) {
            return;
        }

        // Будем думать, что нельзя уменьшать размер вознаграждения.
        if (this.salary > newSalary) {
            return;
        }

        salary = newSalary;
    }

    public void performIndexing(double positivePercentage) {
        if (positivePercentage < 0) {
            return;
        }

        double delta = salary * positivePercentage / 100;
        salary += delta;
    }

    // Перегрузка сравнения.

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Salary that = (Salary) o;
        return salary == that.salary;
    }

    public int hashCode() {
        return Objects.hash(salary);
    }

    // Инициализация

    public static final String CURRENCY_FORMAT_DEFAULT = "#,###.## руб/мес";

    private final DecimalFormat currencyFormat; //

    public Salary(SalaryVerifier salaryVerifier,
                  double salary,
                  DecimalFormat currencyFormat) {
        if (salaryVerifier == null) {
            throw new NullPointerException("Параметр salaryVerifier не должен быть null");
        }

        this.salaryVerifier = salaryVerifier;
        setValue(salary);

        this.currencyFormat = Objects.requireNonNullElseGet(
                currencyFormat,
                () -> new DecimalFormat(CURRENCY_FORMAT_DEFAULT));
    }

    @Override
    public String toString() {
        return String.format("%s", currencyFormat.format(salary));
    }
}
