package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс - универсальная обертка над массивом.
 * В методах, где используется индекс нужно делать валидацию. Индекс должен находиться в рамках добавленных элементов.
 * @param <T>
 */
public class SimpleArray<T> implements Iterable<T> {
    private T[] arr;
    int size = 0;

    public SimpleArray(int count) {
        this.arr = (T[]) new Object[count];
    }

    /**
     * Добавляет входной параметр в первую свободную ячейку массива;
     * @param model
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
      * @param index индекс заменяемого элемента
     * @param model элемент для замены значения массива по указанному индексу
     */
    public void set(int index, T model) {
        int i = Objects.checkIndex(index, size);
        arr[i] = model;
    }

    /**
     * возвращает элемент, расположенный по указанному индексу;
     * @param index
     */
    public T get(int index) {
        if (index >= 0 && index < arr.length) {
            return arr[index];
        }
        return null;
    }

    /**
     *remove(int index) - удаляет элемент по указанному индексу, все находящиеся справа элементы при этом
     * необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек);
     * @param index
     */
    public void remove(int index) {
        int i = Objects.checkIndex(index, size);
        System.arraycopy(arr, i + 1, arr, i, arr.length - i - 1);
        size--;
    }

    class SimpleArrayIterator<T> implements Iterator<T> {
        private int indx = 0;

        @Override
        public boolean hasNext() {
            return indx < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) arr[indx++];
        }
    }

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
        simpleArray.forEach(elem -> System.out.println(elem));
        }
}


