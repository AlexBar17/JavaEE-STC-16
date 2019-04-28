package lesson05.task01.AnimalEx;

/**
 * Исключение при попытке повторно добавить тот же самый объект в коллекцию
 */

public class DoubleException extends Exception {
    public DoubleException() {
        super();
    }

    public DoubleException(String message) {
        super(message);
    }
}
