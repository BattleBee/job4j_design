package ru.job4j.generics.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * каркас универсального хранилища - ++
 * @param <T>
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) { //++
        return mem.replace(id, model) != null;
    }

    @Override
    public boolean delete(String id) {
        return mem.remove(id) != null;
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemStore<?> memStore = (MemStore<?>) o;
        return Objects.equals(mem, memStore.mem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mem);
    }
}
