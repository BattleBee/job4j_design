package ru.job4j.generics.store;

/**
 * все контейнеры должны быть описаны данным интерфейсом. ++
 * @param <T>
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
