package lesson04.task03;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Класс работающий с обертками примитивных типов
 */

public class MathBox<T extends Number> extends ObjectClass<Number> {

    public MathBox(Number[] arr) throws NullPointerException {

        if (arr == null) throw new NullPointerException("Найден null");


        for (Number num :
                arr) {
            set.add(num.doubleValue());
        }

    }

    /**
     * Считает сумму всех элементов коллекции.
     *
     * @return Сумма всех элементов коллекции.
     */

    public Double summator() {
        Double sum = 0.0;
        for (Object obj :
                set) {
            Double d = (Double) obj;
            sum += d;
        }
        return sum;
    }

    /**
     * Выполняет поочередное деление всех хранящихся в объекте элементов на делитель,
     * являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
     *
     * @param del делитель
     */

    public void splitter(Double del) {
        Set<Object> delSet = new HashSet<>();

        for (Object obj :
                set) {
            Double d = (Double) obj;
            d /= del;
            delSet.add(d);
        }
        set = delSet;

    }

    /**
     * Удаляет переданный элемент класса Integer
     *
     * @param i элемент Integer, который надо удалить
     */

    public void delInt(Integer i) {

        Double intForDel = i.doubleValue();

        if (!set.contains(intForDel)) return;

        set.remove(intForDel);

    }

    /**
     * @return hash зависящий от размера переданного массива
     */

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (set == null ? 0 : set.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(set, mathBox.set);
    }


//    public static void main(String[] args) {
//        Number[] arr = new Number[]{4, 2, 30.40};
//        MathBox<Number> mathBox = new MathBox<>(arr);
//
//        System.out.println(mathBox.summator());
//        mathBox.splitter(4.0);
//        Double a = 4d;
//        Double b = 40d;
//        Object o = a;
//        mathBox.addObject(o); //Ошибка
//        mathBox.dump();
//        System.out.println();
//        mathBox.deleteObject(b);
//        mathBox.dump();
//
//        ObjectClass<Object> as = new ObjectClass<>();
//
//
//    }

}
