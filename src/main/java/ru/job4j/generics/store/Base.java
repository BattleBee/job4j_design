package ru.job4j.generics.store;

/**
 * Базовая модель от наследуются все модели  - ++
 */
public abstract class Base {
    private final String id;

    public Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
