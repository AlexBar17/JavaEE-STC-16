package lesson08.task01_02;


/*
Задание 1. Необходимо разработать класс, реализующий следующие методы:

void serialize (Object object, String file);

Object deSerialize(String file);

Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла. Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).

Задание 2. Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы).

ребование: Использовать готовые реализации (Jaxb, jackson и т.д.) запрещается.
*/

import java.io.*;

public class Solution implements Serializable {


    void serialize (Object object, String file) throws IOException {

        if (!(object instanceof Serializable)) {
            System.out.println("Объект не подлежит сериализации");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(object);
        } catch (NotSerializableException e) {
            System.out.println("Ошибка сериализации");
        }

    }

    Object deSerialize(String file) throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object o = ois.readObject();
            return o;
        } catch (Exception e) {
            System.out.println("Объект не может быть прочитан");
            return null;
        }

    }

}

