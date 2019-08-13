package lesson12.task01;

import java.util.concurrent.locks.LockSupport;

public class Solution {

    private static final int LOOP_COUNT = 100_000_000;



    public static void main(String[] args) {



        int multiplier  = 100;

        System.out.println("Максимальный объем памяти JVM: " + Runtime.getRuntime().maxMemory());

        int[] myIntList = null;



        try {

            for (int loop = 0; loop < LOOP_COUNT; loop++) {

                System.out.println("Объем памяти: " + Runtime.getRuntime().freeMemory());

                myIntList = new int[multiplier];

                multiplier = multiplier * multiplier;

                LockSupport.parkNanos(500*1000000);

            }

        } catch (OutOfMemoryError e) {

            System.out.println("Ошибка OutOfMemoryError c пометкой Java Heap Space");

            throw e;

        }

    }

}
