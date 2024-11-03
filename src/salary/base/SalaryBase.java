// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package salary.base;

import salary.Salary;
import verifiable.Verifiable;
import verifiable.VerifyException;

import java.math.BigDecimal;

public final class SalaryBase implements Salary {
    public static final String ERROR_SET_SALARY = "Ошибка установки вознаграждения";
    public static final String SALARY_STORE_IS_NULL = "Вознаграждение не установлено";
    public static final String NEW_SALARY_LESS_THAN_CURRENT = "Назначаемое вознаграждение меньше установленного";
    public static final String INDEX_IS_LESS_THAN_ZERO = "Процент индексации не может быть меньше нуля";

    private BigDecimal salary;

    public double getValue() throws VerifyException {
        if (salary == null) {
            throw new VerifyException(SALARY_STORE_IS_NULL);
        }
        return salary.doubleValue();
    }

    private final Verifiable<Double> salaryVerifier;

    public void setValue(double salary) throws VerifyException {
        if (salaryVerifier != null && !salaryVerifier.isGood(salary)) {
            throw new VerifyException(ERROR_SET_SALARY);
        }

        // будем думать, нельзя уменьшать размер вознаграждения
        if (this.salary != null && this.salary.doubleValue() > salary) {
            throw new VerifyException(NEW_SALARY_LESS_THAN_CURRENT);
        }

        this.salary = new BigDecimal(salary);
    }

    public void setIndexation(double percent) throws VerifyException{
        if (salary == null) {
            throw new VerifyException(SALARY_STORE_IS_NULL);
        }
        if (percent < 0) {
            throw new VerifyException(INDEX_IS_LESS_THAN_ZERO);
        }

        double delta = salary.doubleValue() * percent / 100;
        salary = salary.add(new BigDecimal(delta));
    }

    // Инициализация

    public SalaryBase(Verifiable<Double> salaryVerifier, double salary) {
        this.salaryVerifier = salaryVerifier;
    }
}
