package lesson09.task01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NewLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("lesson09.task01_02.SomeClass".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass starts work: " + name);
        if ("lesson09.task01_02.SomeClass".equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("SomeClass.class"));
                return defineClass(name, bytes, 0, bytes.length); // мапит byte[] в Class
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.findClass(name);

    }

}
