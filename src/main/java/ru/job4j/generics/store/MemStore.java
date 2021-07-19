package ru.job4j.generics.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * каркас универсального хранилища
 * @param <T> обобщенный тип элемента
 */
public final class MemStore<T extends Base> implements Store<T> {
    /**
     * устанавливается структура хранилища
     */
    private final Map<String, T> mem = new HashMap<>();

    /**
     * Добавляет элемент в хранилище
     * @param model обобщенный тип элемента
     */
    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    /**
     * Изменяет значение элемента находячщегося в хранилище по индексу
     * @param id <n>id</n> элемента значение которого нужно заменить
     * @param model новое значение элемента обобщенного типа
     * @return true если есть в хранилище есть элемент с данным <n>id</n> и его значение измененго
     * и false если такого элемента нет
     */
    @Override
    public boolean replace(String id, T model) { //++
        return mem.replace(id, model) != null;
    }

    /**
     * Удаляет элемент хранилища с указанным  значением  <n>id</n>
     * @param id <n>id</n> элемента который нужно удалить
     * @return true если есть в хранилище есть элемент с данным <n>id</n> и его удалось удалить
     * и false если элемента с таким <n>id</n> не было
     */
    @Override
    public boolean delete(String id) {
        return mem.remove(id) != null;
    }

    /**
     * Возвращает значение элемента с указанным <n>id</n>
     * @param id <n>id</n> элемента
     * @return значение элемента по заданному <n>id</n> или null, если элемента с аким <n>id</n> нет
     */
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
