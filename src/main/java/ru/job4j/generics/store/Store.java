package ru.job4j.generics.store;

/**
 * Содержит описание общего шаблона для всех контейнеров
 * @param <T> обобщенный тип элемента контейнера
 */
public interface Store<T extends Base> {
    /**
     * Добавляет новое значение в контейнер
     * @param model значение элемента обобщенного типа
     */
    void add(T model);

    /**
     * Заменяет значение элемента по ключу <n>id</n> на значение <n>model</n>
     * @param id id элемента значенгие которого подлежит замене
     * @param model новое значение элемента обобщенного типа
     */
    boolean replace(String id, T model);

    /**
     * Удаляет элемент по указанному id
     * @param id id удаляемого элемента
     */
    boolean delete(String id);

    /**
     * возвращает значение элемента по id
     * @param id id элемента значение которого возвращает метод
     */
    T findById(String id);
}
