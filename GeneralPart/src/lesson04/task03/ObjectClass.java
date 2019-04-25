package lesson04.task03;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс который который хранит Set c объектами родового класса T
 */

public class ObjectClass<T> {

    protected Set<Object> set = new HashSet<>();

    ObjectClass() {
    }

    /**
     * Добавляет объект в Set
     *
     * @param obj объект, который надо добавить
     */

    void addObject(T obj) {
        set.add(obj);
    }

    /**
     * Удаляет объект из Set
     *
     * @param obj объект, который надо удалить
     */
    void deleteObject(T obj) {
        if (!set.contains(obj)) {
            return;
        }

        set.remove(obj);
    }

    /**
     * Вывод коллекции Set в строку
     *
     */
    void dump() {
        for (Object obj :
                set) {
            System.out.print(obj.toString() + " ");
        }
    }

}
