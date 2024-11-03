// SkyPro
// Курсовая работа «Введение в профессию и синтаксис языка»
// Терских Константин, kostus.online.1974@yandex.ru, 2024
// https://google.github.io/styleguide/javaguide.html

package employee.book;

import employee.Employee;

import java.util.List;

public interface EmployeeBook {
    // CRUD
    boolean addEntry(Employee employee);

    Employee getEntry(int id);

    List<Employee> getEntries(String lastName, String firstName, String middleName);

    boolean removeEntry(int id);

    // Вывод всех записей
    // ...

    // Статистика по вознаграждениям
    // ...
}
