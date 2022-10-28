package ru.job4j.iterator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMapTest {
    @Test
    public void whenDiffNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        Assertions.assertSame(flat.next(), 1);
        Assertions.assertSame(flat.next(), 2);
        Assertions.assertSame(flat.next(), 3);
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        Assertions.assertSame(flat.next(), 1);
        Assertions.assertSame(flat.next(), 2);
        Assertions.assertSame(flat.next(), 3);
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(List.of(1).iterator()).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertTrue(flat.hasNext());
        assertTrue(flat.hasNext());
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        Assertions.assertSame(flat.next(), 1);
        Assertions.assertFalse(flat.hasNext());
    }

    @Test
    public void whenEmpty() {
        Iterator<Iterator<Object>> data = List.of(Collections.emptyIterator()).iterator();
        FlatMap<Object> flat = new FlatMap<>(data);
        assertThrows(NoSuchElementException.class, () -> flat.next());
    }

    @Test
    public void whenSeveralEmptyAndNotEmpty() {
        Iterator<Iterator<?>> it = List.of(
                Collections.emptyIterator(),
                Collections.emptyIterator(),
                Collections.emptyIterator(),
                List.of(1).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        assertTrue(flat.hasNext());
        Assertions.assertSame(1, flat.next());
    }

    @Test
    public void whenSeveralEmptyThenReturnFalse() {
        Iterator<Iterator<Object>> it = List.of(
                Collections.emptyIterator(),
                Collections.emptyIterator(),
                Collections.emptyIterator(),
                Collections.emptyIterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        Assertions.assertFalse(flat.hasNext());
    }
}
