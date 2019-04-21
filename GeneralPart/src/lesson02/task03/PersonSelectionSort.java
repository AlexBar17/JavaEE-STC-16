package lesson02.task03;

import lesson02.task03.ComparedEx.MatchNameAgeException;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Класс реализующий сортировка "Вставками".
 */

public class PersonSelectionSort implements Sortable {

    @Override
    public void sort(Person[] personArray) throws MatchNameAgeException {

        Comparator<Person> pcomp = new Person.PersonNameComparator().thenComparing(new Person.PersonAgeComparator());

        ArrayList<Person> subArrMen = new ArrayList<>();
        ArrayList<Person> subArrWomen = new ArrayList<>();

        for (int i = 0; i < personArray.length - 1; i++) {
            int in = i;
            for (int j = i + 1; j < personArray.length; j++) {
                if (pcomp.compare(personArray[in], personArray[j]) == 0) {
                    throw new MatchNameAgeException("Имя и возраст совпали: " + personArray[in].getName() + " " + personArray[in].getAge());
                }
                if (pcomp.compare(personArray[in], personArray[j]) > 0) {
                    in = j;
                }
            }
            Person temp = personArray[in];
            personArray[in] = personArray[i];
            personArray[i] = temp;

            if (personArray[i].getSex() == Sex.M) {
                subArrMen.add(personArray[i]);
            } else {
                subArrWomen.add(personArray[i]);
            }

        }

        if (personArray[personArray.length - 1].getSex() == Sex.M) {
            subArrMen.add(personArray[personArray.length - 1]);
        } else {
            subArrWomen.add(personArray[personArray.length - 1]);
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
        return "Сортировка выбором";
    }
}
