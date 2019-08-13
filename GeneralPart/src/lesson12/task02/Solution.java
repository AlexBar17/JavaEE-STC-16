package lesson12.task02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class Solution {
    private static final int LOOP_COUNT = 100_000_000;

    /* C использованием флага -XX:MaxMetaspaceSize=64m */

    public static void main(String[] args) {

        System.out.println("Максимальный объем памяти JVM: " + Runtime.getRuntime().maxMemory());

        Integer[] myIntList = new Integer[LOOP_COUNT];

        List<Integer[]> objectsArray = new ArrayList<>();

        try {

            for (int loop = 0; loop < LOOP_COUNT; loop++) {

                System.out.println("Объем памяти: " + Runtime.getRuntime().freeMemory());

                objectsArray.add(myIntList);

                LockSupport.parkNanos(500*1000000);

            }

        } catch (OutOfMemoryError e) {

            System.out.println("Ошибка OutOfMemoryError: Metaspace");

            throw e;

        }

    }
}
