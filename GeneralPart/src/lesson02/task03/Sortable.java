package lesson02.task03;

import lesson02.task03.ComparedEx.MatchNameAgeException;

/**
 * Общий интерфейс для сортировок.
 */
public interface Sortable {

    /**
     * Сортирует переданный массив.
     *
     * @param arr массив для сортировки
     */
    void sort(Person[] arr) throws MatchNameAgeException;

    /**
     * Возвращает название методики сортировки.
     *
     * @return name - имя сортировки
     */
    String getNameOfSort();
}
