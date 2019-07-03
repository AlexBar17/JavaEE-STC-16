package lesson09.task01;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Домашнее задание:
 * Необходимо написать программу, выполняющую следующее:
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */

public class Solution {

    public static String code = "public void doWork() {\n" +
            "System.out.println(\"do something\");\n" +
            "}";



    public static void main(String[] args) throws IOException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {


        Solution solution = new Solution();

        NewLoader loader = new NewLoader();

        List<String> listTextOfClass = new ArrayList<>();

        Solution sol = new Solution();


        sol.printMethod("SomeClass.java", code);

        sol.compile();

        NewLoader newLoader = new NewLoader();

        Class<?> kindClass = newLoader.loadClass("lesson09.task01.SomeClass");

        kindClass.getMethod("doWork").invoke(kindClass.newInstance());

    }

    private List<String> readClass (String path) throws IOException {

        List<String> textOfClass = new ArrayList<>();

        FileReader file = new FileReader(path);

        BufferedReader reader = new BufferedReader(file);

        String line = reader.readLine();

        int i = 0;

        while (line != null) {

            textOfClass.add(line);

            line = reader.readLine();

            i++;

        }

        reader.close();

        return textOfClass;
    }

    private int findStartClassBody (List<String> textOfClass) {

        if (textOfClass == null) return -1;

        for (int i = 0; i < textOfClass.size(); i++) {

            if (textOfClass.get(i).indexOf("class") > 0) {
                return i+1;
            }

        }

        return -1;
    }
    
    private void printMethod(String classFilePath, String textOfMethod) throws IOException {

        List<String> textOfClass = readClass(classFilePath);

        int start = findStartClassBody(textOfClass);

        textOfClass.add(start, textOfMethod);

        FileWriter fileWriter = new FileWriter(classFilePath);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (String s :
                textOfClass) {
            bufferedWriter.write(s + "\n");
        }

        bufferedWriter.close();

    }

    private void compile() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
//                "cmd.exe", "/c", "cd GeneralPart\\src\\lesson09\\task01_02 && javac SomeClass.java");
                "cmd.exe", "/c", "javac SomeClass.java");

        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }
}
