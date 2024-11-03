// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package salary.base;

import salary.Salary;
import verifiable.Verifiable;
import verifiable.VerifyException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

public final class SalaryBase implements Salary {
    public static final String SALARY_STORE_IS_NULL = "Вознаграждение не установлено";
    public static final String ERROR_SET_SALARY = "Ошибка установки вознаграждения";
    public static final String NEW_SALARY_LESS_THAN_CURRENT = "Назначаемое вознаграждение меньше установленного";
    public static final String INDEX_IS_LESS_THAN_ZERO = "Процент индексации не может быть меньше нуля";

    private BigDecimal salary;

    // Реализация интерфейса Salary

    public double getValue() throws VerifyException {
        if (salary == null) {
            throw new VerifyException(SALARY_STORE_IS_NULL);
        }
        return salary.doubleValue();
    }

    private final Verifiable<Double> salaryVerifier;

    public void setValue(double newSalary) throws VerifyException {
        if (salaryVerifier != null && !salaryVerifier.isGood(newSalary)) {
            throw new VerifyException(ERROR_SET_SALARY);
        }

        // Будем думать, что нельзя уменьшать размер вознаграждения.
        if (this.salary != null && this.salary.doubleValue() > newSalary) {
            throw new VerifyException(NEW_SALARY_LESS_THAN_CURRENT);
        }

        this.salary = new BigDecimal(newSalary);
    }

    public void performIndexation(double positivePercent) throws VerifyException {
        if (salary == null) {
            throw new VerifyException(SALARY_STORE_IS_NULL);
        }
        if (positivePercent < 0) {
            throw new VerifyException(INDEX_IS_LESS_THAN_ZERO);
        }

        double delta = salary.doubleValue() * positivePercent / 100;
        salary = salary.add(new BigDecimal(delta));
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

        if (salary == null) {
            return false;
        }

        SalaryBase that = (SalaryBase) o;
        if (that.salary == null) {
            return false;
        }

        return salary.equals(that.salary);
    }

    public int hashCode() {
        if (salary == null) {
            Objects.hash(this);
        }
        return salary.hashCode();
    }

    // Инициализация

    public static final String CURRENCY_FORMAT_DEFAULT = "#,###.## руб/мес";

    private final DecimalFormat currencyFormat; //

    public SalaryBase(Verifiable<Double> salaryVerifier,
                      double salary, DecimalFormat currencyFormat) throws VerifyException {
        if (salaryVerifier == null) {
            throw new VerifyException(ERROR_SET_SALARY);
        }
        this.salaryVerifier = salaryVerifier;
        setValue(salary);

        this.currencyFormat = Objects.requireNonNullElseGet(
                currencyFormat,
                () -> new DecimalFormat(CURRENCY_FORMAT_DEFAULT));
    }

    @Override
    public String toString() {
        return String.format("%s", currencyFormat.format(salary.doubleValue()));
    }
}
