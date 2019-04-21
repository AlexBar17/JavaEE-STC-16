package lesson02.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {

        System.out.println("Введите количество случайных чисел для программы:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        reader.close();

        for (int i = 0; i < count; i++) {

            int k = (int) (-2 + (Math.random() * 101));

            if (k < 0) {
                throw new ArithmeticException(String.format("Число %d меньше нуля", k));
            }

            double q = Math.sqrt(k);
            int intQ = (int) q;

            if (Math.pow(intQ, 2) == k) {

                System.out.println(k);

            }

        }


    }

}
