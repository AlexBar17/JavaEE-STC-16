package lesson05.task01;

import lesson05.task01.AnimalEx.DoubleException;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class AnimalCards {
    HashMap<String, Animal> map = new HashMap<>();

    /**
     * Добавляет объект Animal в коллекцию HashMap, где ключом будет уникальный id
     * объекта Animal
     *
     * @param animal объект Animal, который необходимо сохранить в коллекцию
     * @throws DoubleException выбрасывается при попытке добавить элемент,
     *                         который уже есть в коллекции
     */

    public void addAnimal(Animal animal) throws DoubleException {
        if (map.containsKey(animal.getId())) throw new DoubleException("Найден дубликат: " + animal);
        map.put(animal.getId(), animal);

    }

    /**
     * Возвращает коллекцию HashSet из всех объектов Animal исходной коллекции
     * совпадающих по имени <b>name</b>
     *
     * @param name имя по которому ищутся совпадения
     * @return HashSet<Animal> из объектов Animal с совпадающими именами
     */

    public HashSet<Animal> searchAnimal(String name) {
        HashSet<Animal> set = new HashSet<>(map.values());
        HashSet<Animal> animalsWithThisName = new HashSet<>();
        for (Animal a :
                set) {
            if (a.getName().equals(name)) {
                animalsWithThisName.add(a);
            }

        }
        return animalsWithThisName;
    }

    /**
     * Изменяет данные животного по его идентификатору.
     * В случае, если идентификатора не существует, сообщает об этом и прекращает работу.
     *
     * @param id идентификатор
     * @param name новое имя
     * @param owner новый владелец
     * @param weight новый вес
     */

    public void changeAnimal(String id, String name, Person owner, int weight) {
        if (!map.containsKey(id)) {
            System.out.println("Животного с таким id нет в картотеке");
            return;
        }
        Animal animal = map.get(id);
        animal.setName(name);
        animal.setOwner(owner);
        animal.setWeight(weight);
    }

    /**
     * Выводит на экран отсортированный TreeSet<Animal> из всех животных в коллекции
     */

    public void printSortAnimalCard() {
        TreeSet<Animal> treeSet = new TreeSet<>(map.values());

        for (Animal animal :
                treeSet) {
            System.out.println(animal);
        }

    }


}
