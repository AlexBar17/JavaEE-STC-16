package lesson11.task01;

/*

Задание: Перевести одну из предыдущих работ на использование
стримов и лямбда-выражений там, где это уместно (возможно, жертвуя производительностью)

 */



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Solution{

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService forkJoinPool = Executors.newWorkStealingPool();


        Integer[] array = new Integer[30];


        List<Callable<BigInteger>> callables = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            int n = i;

            Callable<BigInteger> callable = () -> {
                if (n == 0) return BigInteger.ONE;
                BigInteger res = BigInteger.ONE;
                for (int j = 2; j <= n; j++) res = BigInteger.valueOf(j).multiply(res);
                return res;
            };


            callables.add(callable);

        }


        forkJoinPool.invokeAll(callables).stream()
                .map(f -> {
                    try {
                        return f.get();
                    } catch (InterruptedException e) {
                        return null;
                    } catch (ExecutionException e) {
                        return null;
                    }
                })
                .forEach(System.out::println);

    }


}
