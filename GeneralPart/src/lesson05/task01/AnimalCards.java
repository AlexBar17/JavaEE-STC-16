package lesson05.task01;

import lesson05.task01.AnimalEx.DoubleException;

import java.util.HashMap;
import java.util.HashSet;

public class AnimalCards {
    HashMap<String, Animal> map = new HashMap<>();

    public void addAnimal(Animal animal) throws DoubleException {
        if (map.containsKey(animal.getId())) throw new DoubleException("Наден дубликат");
        map.put(animal.getId(), animal);
    }

    public Animal searchAnimal(String name) {
        HashSet<Animal> set = new HashSet<>(map.values());
        for (Animal a :
                set) {
            if (a.getName().equals(name)) return a;

        }
        return null;
    }
}
