package lesson05.task01;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Comparator;

/**
 * Класс для работы с человеком.
 */

public class Person implements Comparable<Person> {
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
     * Получает возраст.
     *
     * @return age
     */

    public int getAge() {
        return age;
    }

    /**
     * Получает имя.
     *
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * Получает пол.
     *
     * @return sex
     */

    public Sex getSex() {
        return sex;
    }

    /**
     * Получает строковое представление человека со всеми полями
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
     * Генерирует массив со значениями из людей размера <B>size</B>.
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
     * Сранивает с другим человеком, сначала по полу (мужчина = 1, женщина = 2), затем по имени и возрасту
     *
     * @param p другой человек
     * @return отрицательное целое число, ноль или положительное целое число,
     * соответственно если этот объект меньше, равен или больше указанного объекта.
     */

    @Override
    public int compareTo(Person p) {
        Comparator<Person> pcomp =
                new PersonSexComparator().thenComparing(
                        new PersonNameComparator().thenComparing(
                                new PersonAgeComparator()));

        return pcomp.compare(this, p);
    }

    /**
     * Сранивает поля <B>sex</B> - <B>W</B> имеет больший вес чем <B>M</B>
     */

    public static class PersonSexComparator implements Comparator<Person> {

        public int compare(Person a, Person b) {
            if (a.getSex() == b.getSex()) return 0;
            if (a.getSex() == Sex.M) return -1;
            return 1;
        }
    }

    /**
     * Сранивает поля <B>name</B> через стандартное сравнение класса String.
     */

    public static class PersonNameComparator implements Comparator<Person> {

        public int compare(Person a, Person b) {

            return a.getName().compareTo(b.getName());
        }
    }

    /**
     * Сранивает поля <B>age</B> через стандартное сравнение класса Integer.
     */

    public static class PersonAgeComparator implements Comparator<Person> {

        public int compare(Person a, Person b) {

            Integer ageA = a.getAge();
            Integer ageB = b.getAge();

            return ageA.compareTo(ageB);
        }
    }


}
