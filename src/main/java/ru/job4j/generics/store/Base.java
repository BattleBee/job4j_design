package ru.job4j.generics.store;

/**
 * Базовая модель от которой наследуются все модели
 */
public abstract class Base {
    private final String id;

    public Base(final String id) {
        this.id = id;
    }

    /**
     * возвращает  значение приватного поля id для элемента контейнера
     * @return id элемента
     */
    public String getId() {
        return id;
    }
}
