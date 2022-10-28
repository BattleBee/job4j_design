package ru.job4j.iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EvenIteratorTest {

    private Iterator<Integer> it;

    @BeforeEach
    void setUp() {
        it = new EvenNumbersIterator(new int[] {1, 2, 3, 4, 5, 6, 7});
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Assertions.assertTrue(it.hasNext());
        Assertions.assertTrue(it.hasNext());
        Assertions.assertSame(it.next(), 2);
        Assertions.assertSame(it.next(), 4);
        Assertions.assertSame(it.next(), 6);
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[]{1});
        Assertions.assertFalse(it.hasNext());
    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenNumbersIterator(new int[] {2, 4, 6, 8});
        Assertions.assertTrue(it.hasNext());
        Assertions.assertSame(it.next(), 2);
        Assertions.assertTrue(it.hasNext());
        Assertions.assertSame(it.next(), 4);
        Assertions.assertTrue(it.hasNext());
        Assertions.assertSame(it.next(), 6);
        Assertions.assertTrue(it.hasNext());
        Assertions.assertSame(it.next(), 8);
    }

    @Test
    public void shouldReturnEvenNumbersSequentially() {
        Assertions.assertTrue(it.hasNext());
        Assertions.assertSame(it.next(), 2);
        Assertions.assertTrue(it.hasNext());
        Assertions.assertSame(it.next(), 4);
        Assertions.assertTrue(it.hasNext());
        Assertions.assertSame(it.next(), 6);
        Assertions.assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

}