package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        point = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) { // если в итераторе нет элементов и мы вызовем метод next
            throw new NoSuchElementException(); // в этом случае итератор должен сгенерировать исключение
        }
        return data[point--];
    }
}

