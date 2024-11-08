// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Константин Терских, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;
import java.util.Objects;

/**
 * Все сотрудники.
 *
 * @author Константин Терских, kostus.online.1974@yandex.ru, 2024
 * @version 1.1
 */
public class EmployeeBook {
    /**
     * Простое хранилище записей о сотрудниках. Размер задаётся в конструкторе {@link EmployeeBook}.
     */
    private final Employee[] employees;

    /**
     * Конструктор с внедрением уже готового хранилища.
     *
     * @param employees массив записей о сотрудниках
     */
    public EmployeeBook(@NotNull Employee[] employees) {
        this.employees = employees;
    }

    /**
     * Конструктор, в котором хранилище создаётся с учётом заданного размера.
     *
     * @param capacity вместимость хранилища
     */
    public EmployeeBook(int capacity) {
        this.employees = new Employee[capacity];
    }

    /**
     * Универсальный признак "не найдено".
     */
    public static final int NOT_FOUND = -1;

    /**
     * Получение первой от нуля свободной ячейки в хранилище<br>
     * с учётом или без учёта отдела.
     *
     * @return индекс свободной ячейки или {@link EmployeeBook#NOT_FOUND}
     */
    private int getFirstFreeIndex(@Nullable Division division) {
        if (employees == null) {
            return NOT_FOUND;
        }

        for (int i = 0; i < employees.length; i++) {
            if (division != null && !matchDivision(employees[i], division)) {
                continue;
            }
            if (employees[i] != null) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Добавление нового сотрудника в хранилище.
     *
     * @param employee запись о сотруднике {@link Employee}
     * @return {@code true} если добавление прошло успешно
     */
    public boolean tryAddEmployee(@NotNull Employee employee) {
        int freeIndex = getFirstFreeIndex(null);
        if (freeIndex == NOT_FOUND) {
            return false;
        }
        employees[freeIndex] = employee;
        return true;
    }

    /**
     * Получение записи о сотруднике по ID.
     *
     * @param id ID сотрудника {@link Employee#getId()}
     * @return запись о сотруднике {@link Employee} или {@code null}
     */
    @Nullable
    public Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Удаление сотрудника по ID.
     *
     * @param id ID сотрудника {@link Employee#getId()}
     */
    public void removeEmployee(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId() == id) {
                employees[i] = null;
                return;
            }
        }
    }

    /**
     * Печать всех сотрудников.
     *
     * @param out поток вывода
     */
    public void printEmployees(@NotNull PrintStream out) {
        for (Employee employee : employees) {
            if (employee != null) {
                out.print("\t");
                out.println(employee);
            }
        }
    }

    private boolean matchDivision(@NotNull Employee employee, @NotNull Division division) {
        return Objects.equals(employee.getDivision(), division);
    }

    /**
     * Подсчёт суммы заработной платы по отделу или по всем записям.
     *
     * @param division отдел {@link Division}
     * @return сумма вознаграждений
     */
    public double getSalarySum(@Nullable Division division) {
        double sum = 0;
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }

            if (division != null && !matchDivision(employee, division)) {
                continue;
            }

            sum += employee.getSalary().getValue();
        }
        return sum;
    }

    /**
     * Получение реального количества сотрудников в отделе или в целом.
     *
     * @param division отдел {@link Division} или {@code null}
     * @return количество сотрудников
     */
    public int getEmployeeCount(@Nullable Division division) {
        int count = 0;
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }

            if (division != null && !matchDivision(employee, division)) {
                continue;
            }

            count++;
        }
        return count;
    }

    /**
     * Подсчёт средней заработной платы по отделу или по всем записям.
     *
     * @param division отдел {@link Division} или {@code null}
     * @return средняя заработная плата
     */
    public double getSalaryAverage(@Nullable Division division) {
        double sum = 0;
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }

            if (division != null && !matchDivision(employee, division)) {
                continue;
            }

            sum += employee.getSalary().getValue();
        }
        int employeesCount = getEmployeeCount(division);
        return sum / employeesCount;
    }

    @Nullable
    public Employee getEmployeePoorest(@Nullable Division division) {
        int index = getFirstFreeIndex(division);
        if (index == NOT_FOUND) {
            return null;
        }

        Employee poorest = employees[index];
        for (int i = index + 1; i < employees.length; i++) {
            if (employees[i] == null) {
                continue;
            }

            if (division != null && !matchDivision(employees[i], division)) {
                continue;
            }

            if (employees[i].getSalary().getValue() < poorest.getSalary().getValue()) {
                poorest = employees[i];
            }
        }
        return poorest;
    }

    @Nullable
    public Employee getEmployeeRichest(@Nullable Division division) {
        int index = getFirstFreeIndex(division);
        if (index == NOT_FOUND) {
            return null;
        }

        Employee richest = employees[index];
        for (int i = index + 1; i < employees.length; i++) {
            if (employees[i] == null) {
                continue;
            }

            if (division != null && !matchDivision(employees[i], division)) {
                continue;
            }

            if (employees[i].getSalary().getValue() > richest.getSalary().getValue()) {
                richest = employees[i];
            }
        }
        return richest;
    }

    public void performSalaryIndexing(@Nullable Division division, double positivePercentage) {
        if (positivePercentage < 0) {
            throw new IllegalArgumentException("Параметр positivePercentage должен быть положительным числом");
        }

        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }

            if (division != null && !matchDivision(employee, division)) {
                continue;
            }

            employee.getSalary().performIndexing(positivePercentage);
        }
    }

    public static final int LESS_THAN = -1;
    public static final int EQUAL = 0;
    public static final int GREATER_THAN = 1;

    public Employee[] getEmployees(double salary, int compare, boolean inclusive) {
        return null;
    }
}
