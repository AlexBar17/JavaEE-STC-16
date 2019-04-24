package lesson04.task03;

import java.util.HashSet;
import java.util.Set;

public class ObjectClass<T> {

    protected Set<Object> set = new HashSet<>();

    ObjectClass() {
    }
    
    void addObject(T obj) {
        set.add(obj);
    }

    void deleteObject(T obj) {
        if (!set.contains(obj)) {
            return;
        }

        set.remove(obj);
    }

    void dump() {
        for (Object obj :
                set) {
            System.out.print(obj.toString() + " ");
        }
    }

}
