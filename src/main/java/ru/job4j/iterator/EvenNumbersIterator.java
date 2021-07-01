package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Данный класс - Итератор, принимает список произвольных чисел и возвращает только четные цифры.
 * private final int[] numbers - входящий список произвольных чисел для итератора
 * private int point -  выполняет функцию указателя
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int point;

    public EvenNumbersIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Метод проверяет можно ли получить следующий элемент списка, соответствующий
     * заданному условию, также присваивает значение индекса этого элемента переменной указателю.
     * @return возвращает значение true если можно получить следующий элемент списка, соответствующий
     * заданному условию, и false  в противном случае.
     */
    @Override
    public boolean hasNext() {
        while (point < numbers.length && numbers[point] % 2 != 0) {
            point++;
        }
        return point < numbers.length;
    }

    /**
     * Метод возвращает элемент списка по индексу указателя и перемещает указатель увеличивая его значение на +1.
     * Если следующего элемента, соответствующего заданному условию, нет и при этом вызывается метод next,
     * то в этом случае итератор должен сгенерировать исключение NoSuchElementException()
     * @return возвращает элемент списка по индексу указателя.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }

}
