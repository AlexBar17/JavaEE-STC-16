package lesson02.task03;


import lesson02.task03.ComparedEx.MatchNameAgeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/**
 * Класс с точкой входа.
 */

public class Solution {

    public static void main(String[] args) throws IOException, MatchNameAgeException {

        System.out.println("Введите размер массива:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int sizeOfPersonArray = Integer.parseInt(reader.readLine());
        reader.close();

        Person[] personArray = Person.getArrayOfPerson(sizeOfPersonArray);


        ArrayList<Sortable> sorts = new ArrayList<>();

        sorts.add(new PersonInsertionSort());
        sorts.add(new PersonSelectionSort());

        for (Sortable sorter : sorts) {

            Person[] testArrPerson = (Person[]) personArray.clone();

            Date start = new Date();
            sorter.sort(testArrPerson);
            Date end = new Date();

            long time = end.getTime() - start.getTime();

            System.out.println(sorter.getNameOfSort() +" заняла: ~" + time + "мс");

            for (Person p :
                    testArrPerson) {
                System.out.println(p);

            }

        }


    }
}


