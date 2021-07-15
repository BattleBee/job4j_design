package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор, возвращает значения  списка  чисел в обратном порядке.
 */
public class BackwardArrayIt implements Iterator<Integer> {
    /**
     * входящий список произвольных чисел для итератора
     */
    private final int[] data;
    /**
     * выполняет функцию указателя
     */
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        point = data.length - 1;
    }

    /**
     * Метод проверяет можно ли получить следующий элемент списка, соответствующий
     * заданному условию, также присваивает значение индекса этого элемента переменной указателю.
     * @return возвращает значение true если можно получить следующий элемент списка, соответствующий
     * заданному условию, и false  в противном случае.
     */
    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    /**
     * Возвращает элемент списка по индексу указателя и перемещает указатель уменьшая его значение на -1.
     * @throws NoSuchElementException запрошенный элемент не существует.
     * @return возвращает элемент списка по индексу указателя.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}

