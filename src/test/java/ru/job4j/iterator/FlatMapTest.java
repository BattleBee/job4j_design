package ru.job4j.iterator;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
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
        assertSame(flat.next(), 1);
        assertSame(flat.next(), 2);
        assertSame(flat.next(), 3);
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertSame(flat.next(), 1);
        assertSame(flat.next(), 2);
        assertSame(flat.next(), 3);
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertSame(flat.hasNext(), true);
        assertSame(flat.hasNext(), true);
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertSame(flat.next(), 1);
        assertSame(flat.hasNext(), false);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        Iterator<Iterator<Object>> data = List.of(
                List.of().iterator()
        ).iterator();
        FlatMap<Object> flat = new FlatMap<>(data);
        flat.next();
    }

    @Test
    public void whenSeveralEmptyAndNotEmpty() {
        Iterator<Iterator<?>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of(1).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        Assert.assertTrue(flat.hasNext());
        assertSame(1, flat.next());
    }

    @Test
    public void whenSeveralEmptyThenReturnFalse() {
        Iterator<Iterator<Object>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        Assert.assertFalse(flat.hasNext());
    }
}
