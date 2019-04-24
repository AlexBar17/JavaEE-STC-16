package lesson04.task02;

import java.util.Set;

public class ObjectClass {
    Set<Object> set;

    public ObjectClass(Set<Object> set) {
        this.set = set;
    }

    void addObject(Object obj) {
        set.add(obj);
    }

    void deleteObject(Object obj) {
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
