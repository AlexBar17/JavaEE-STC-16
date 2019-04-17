package lesson02.task01;

//Задание 1. Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение и завершиться с ошибкой.
//
//Смоделировав ошибку «NullPointerException»
//Смоделировав ошибку «ArrayIndexOutOfBoundsException»
//Вызвав свой вариант ошибки через оператор throw

public class HeloWorld {

    private static String helloWorld;

    public static void main(String[] args) throws FooException {

//        java.lang.NullPointerException
        try {
            System.out.println(helloWorld.toLowerCase());
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        }

//        java.lang.ArrayIndexOutOfBoundsException
        try {
            helloWorld = "Hello world!";
            System.out.println(helloWorld.toCharArray()[100]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }

//        java.lang.FooException
        throw new FooException();

    }

    private static class FooException extends Exception {

    }
}
