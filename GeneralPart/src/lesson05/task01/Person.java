package lesson05.task01;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Comparator;

/**
 * Класс для работы с человеком.
 */

public class Person {
    private Sex sex;
    private String name;
    private int age;

    /**
     * Конструктор для стандартного человека
     *
     * @param sex  пол
     * @param name имя
     * @param age  возраст
     */

    public Person(Sex sex, String name, int age) {
        this.sex = sex;
        this.name = name;
        this.age = age;
    }

    /**
     * Получить возраст.
     *
     * @return age
     */

    public int getAge() {
        return age;
    }

    /**
     * Получить имя.
     *
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * Получить пол.
     *
     * @return sex
     */

    public Sex getSex() {
        return sex;
    }

    /**
     * Получить строковое представление человека со всеми полями
     */
    @Override
    public String toString() {
        return "Person{" +
                "sex=" + sex +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * Сгенерировать массив из людей размера <B>size</B>.
     *
     * @param size размер массива
     * @return Person[]
     */

    public static Person[] getArrayOfPerson(int size) {

        Person[] ArrayOfPerson = new Person[size];

        Sex[] arrValueSex = Sex.values();
        Sex sex;
        String name;
        int age;

        for (int i = 0; i < ArrayOfPerson.length; i++) {

            sex = arrValueSex[((int) (Math.random() * 2))];
            name = RandomStringUtils.randomAlphabetic(4, 12);
            age = ((int) (Math.random() * 101));

            ArrayOfPerson[i] = new Person(sex, name, age);
        }


        return ArrayOfPerson;
    }

    /**
     * Сравнить поля <B>name</B> через стандартное сравнение класса String.
     */

    public static class PersonNameComparator implements Comparator<Person> {

        public int compare(Person a, Person b) {

            return a.getName().compareTo(b.getName());
        }
    }

    /**
     * Сравнить поля <B>age</B> через стандартное сравнение класса Integer.
     */

    public static class PersonAgeComparator implements Comparator<Person> {

        public int compare(Person a, Person b) {

            Integer ageA = a.getAge();
            Integer ageB = b.getAge();

            return ageA.compareTo(ageB);
        }
    }

}
