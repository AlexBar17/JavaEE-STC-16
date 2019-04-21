package lesson02.task03;

import lesson02.task03.ComparedEx.MatchNameAgeException;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Класс реализующий сортировку "Выбором".
 */

public class PersonInsertionSort implements Sortable {

    @Override
    public void sort(Person[] personArray) throws MatchNameAgeException {

        Comparator<Person> pcomp = new Person.PersonNameComparator().thenComparing(new Person.PersonAgeComparator());

        ArrayList<Person> subArrMen = new ArrayList<>();
        ArrayList<Person> subArrWomen = new ArrayList<>();

        Person temp;
        for (int i = 1; i < personArray.length; i++) {
            int j = i;
            while (j > 0 && pcomp.compare(personArray[j], personArray[j - 1]) <= 0) {
                if (pcomp.compare(personArray[j], personArray[j - 1]) == 0) {
                    throw new MatchNameAgeException("Имя и возраст совпали: " + personArray[j].getName() + " " + personArray[j].getAge());
                }
                temp = personArray[j - 1];
                personArray[j - 1] = personArray[j];
                personArray[j] = temp;
                j--;
            }
        }

        for (int i = 0; i < personArray.length; i++) {
            if (personArray[i].getSex() == Sex.M) {
                subArrMen.add(personArray[i]);
            } else {
                subArrWomen.add(personArray[i]);
            }

        }


        int j = 0;

        for (int k = 0; k < subArrMen.size(); k++) {
            personArray[j] = subArrMen.get(k);
            j++;
        }
        for (int k = 0; k < subArrWomen.size(); k++) {
            personArray[j] = subArrWomen.get(k);
            j++;
        }

    }

    @Override
    public String getNameOfSort() {
        return "Сортировка вставками";
    }


}
