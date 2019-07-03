package lesson09.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestCMD {


        public static void main(String args[]) throws IOException {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd GeneralPart\\src\\lesson09\\task01_02 && javac SomeClass.java");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }

            System.out.println("Done");
        }


}
