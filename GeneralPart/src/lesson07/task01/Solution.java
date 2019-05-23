package lesson07.task01;

/*

Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива.
Использовать пул потоков для решения задачи.

 */


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService forkJoinPool = Executors.newWorkStealingPool();


        Integer[] array = new Integer[30];


        List<Callable<BigInteger>> callables = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {

            callables.add(new FactorialThread(i));

        }



        for (Future<BigInteger> f : forkJoinPool.invokeAll(callables)) {

            System.out.println(f.get());

        }


    }

    static class FactorialThread implements Callable<BigInteger> {

        int n;

        public FactorialThread(int n) {
            this.n = n;
        }

        @Override
        public BigInteger call() throws Exception {
            if (n==0) return BigInteger.ONE;
            BigInteger res = BigInteger.ONE;
            for (int i = 2; i<=n; i++) res = BigInteger.valueOf(i).multiply(res);
            return res;
        }
    }

}
