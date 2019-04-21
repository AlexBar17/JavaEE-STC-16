package lesson02.task03.ComparedEx;

/**
 * Класс реализующий исключение в случае совпадения людей с одинаковыми имена и возрастом.
 */

public class MatchNameAgeException extends Exception {
    public MatchNameAgeException() {
        super();
    }

    public MatchNameAgeException(String message) {
        super(message);
    }
}
