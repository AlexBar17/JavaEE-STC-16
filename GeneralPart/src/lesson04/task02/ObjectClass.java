package lesson04.task02;

import java.util.Set;

/**
 * Класс который который хранит Set c объектами класса Object
 */

public class ObjectClass {
    private Set<Object> set;

    /**
     * Конструктор принимающий объект Set для дальнейщей работы
     *
     * @param set рабочая коллекция Set
     */
    public ObjectClass(Set<Object> set) {
        this.set = set;
    }

    /**
     * Добавляет объект в Set
     *
     * @param obj объект, который надо добавить
     */
    void addObject(Object obj) {
        set.add(obj);
    }

    /**
     * Удаляет объект из Set
     *
     * @param obj объект, который надо удалить
     */
    void deleteObject(Object obj) {
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
