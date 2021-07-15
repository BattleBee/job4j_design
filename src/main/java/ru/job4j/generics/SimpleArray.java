package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс - универсальная обертка над массивом.
 * В методах, где используется индекс нужно делать валидацию. Индекс должен находиться в рамках добавленных элементов.
 * @param <T> обобщенный тип элементов массива
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * массив с обобщенным типом элементов.
     */
    private T[] arr;
    /**
     * указыыает индекс последнего элемента массива
     */
    int size;

    public SimpleArray(int count) {
        this.arr = (T[]) new Object[count];
        size = 0;
    }

/**
 * Добавляет входной параметр в первую свободную ячейку массива;
 * @param model значение элемента обобщенного типа
 * @throws IllegalStateException В массиве больше нет свободного места!
 */
    public void add(T model) {
        if (size >= arr.length) {
            throw new IllegalStateException("Нет свободного места!");
        } else {
            arr[size] = model;
            size++;
        }
    }

/**
 * Заменяет указанным элементом (model) элемент, находящийся по индексу index;
 * С помощью метода <b>Objects.checkIndex(index, size);</b> проверяется что значение index
 * находится в пределах ранее добавленных в массив элементов.
  * @param index индекс заменяемого элемента
 * @param model элемент для замены значения массива по указанному индексу
 * @throws IndexOutOfBoundsException значение index вне рамок добавленных элементов.
 */
    public void set(int index, T model) {
        int i = Objects.checkIndex(index, size);
        arr[i] = model;
    }

/**
 * Возвращает элемент, расположенный по заданному в параметре индексу;
 * С помощью метода <b>Objects.checkIndex(index, size);</b> проверяется, что значение index
 * находится в пределах ранее добавленных в массив элементов.
 * @param index индекс элемента
 * @return значение элемента массива по <b>index</b>.
 * Метод <b>Objects.checkIndex(index, size);</b> проверяет, что значение index
 * находится в пределах ранее добавленных в массив элементов и возвращает значение <b>index</b>.
 * @throws IndexOutOfBoundsException значение index вне рамок добавленных элементов.
 */
    public T get(int index) {
        return arr[Objects.checkIndex(index, size)];
    }

 /**
  * Удаляет элемент по аданному в параметре индексу индексу, а также сдвигает  все находящиеся
  * справа элементы на единицу влево, чтобы в середине массива не образовывалось пустых ячеек;
  * Метод <b>Objects.checkIndex(index, size);</b> проверяет, что значение index
  * находится в пределах ранее добавленных в массив элементов и возвращает значение <b>index</b>.
  * @param index индекс удаляемого элемента.
  * @throws IndexOutOfBoundsException значение index вне рамок добавленных элементов.
  */
    public void remove(int index) {
        int i = Objects.checkIndex(index, size);
        System.arraycopy(arr, i + 1, arr, i, arr.length - i - 1);
        size--;
    }

/**
 * Итератор для обьектов класса {@link SimpleArray}
 * @param <T> обобщенный тип элементов массива
 */
    class SimpleArrayIterator<T> implements Iterator<T> {
/**
 * выполняет функцию указателя итератора
 */
        private int point = 0;

/**
 * Проверяет наличие следующего элемента в списке.
 * @return озвращает true если можно получить следующий элемент и false если нет.
 */
        @Override
        public boolean hasNext() {
            return point < size;
        }

/**
 * Возвращает элемент списка по индексу указателя и перемещает указатель увеличивая его значение на +1.
 * @throws NoSuchElementException запрошенный элемент не существует.
 * @return возвращает элемент списка по индексу указателя.
 */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Запрошенный элемент не существует!");
            }
            return (T) arr[point++];
        }
    }

/**
 * Определяет итератор для объектов класса (массивов).
 * @return выбранный итератор
 */
    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<T>();
    }

    public static void main(String[] args) {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        simpleArray.add(5);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(2);
        simpleArray.add(1);
        simpleArray.forEach(System.out::println);
        }
}


