package lesson05.task01;

/**
 * Перечисление содержащие пол человека: <b>MAN</b> и <b>WOMAN</b>.
 */

public enum Sex {
    /** MAN */
    M("MAN"),
    /** WOMAN */
    W("WOMAN");

    private final String s;

    Sex(String s) {
        this.s = s;
    }

    /**
     * Возвращает строковое представление.
     *
     * @return sex as String
     */

    @Override
    public String toString() {
        return s;
    }
}