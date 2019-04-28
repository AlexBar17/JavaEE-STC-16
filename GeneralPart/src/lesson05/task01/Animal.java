package lesson05.task01;


import java.util.Comparator;

/**
 * Класс содержащий данные о животном.
 *
 */

public class Animal implements Comparable<Animal> {

    /**
     * Поля для вычисления уникального ID. Хранит в себе значение ID последнего созданного животного
     */
    private static int IDcount = 0;
    private String id;
    private String name;
    private Person owner;
    private int weight;

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", weight=" + weight +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Animal(String name, Person owner, int weight) {
        this.id = IDcount++ + "";
        this.name = name;
        this.owner = owner;
        this.weight = weight;
    }

    public static class AnimalPersonComparator implements Comparator<Animal> {

        public int compare(Animal a, Animal b) {
            return a.getOwner().compareTo(b.getOwner());
        }
    }


    public static class AnimalNameComparator implements Comparator<Animal> {

        public int compare(Animal a, Animal b) {
            return a.getName().compareTo(b.getName());
        }
    }

    public static class AnimalWeightComparator implements Comparator<Animal> {

        public int compare(Animal a, Animal b) {
            return a.getWeight() - b.getWeight();
        }
    }

    /**
     * Сранивнение с другим животным, сначала по хозяину Person, затем по имени и весу
     *
     * @param animal животное с которым идет сранение
     * @return Отрицательное целое число, ноль или положительное целое число,
     * соответственно если этот объект меньше, равен или больше указанного объекта.
     */

    @Override
    public int compareTo(Animal animal) {
        Comparator<Animal> acomp =
                new AnimalPersonComparator().thenComparing(
                        new AnimalNameComparator().thenComparing(
                                new AnimalWeightComparator()));

        return acomp.compare(this, animal);
    }


}
