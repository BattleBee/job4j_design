package ru.job4j.generics.store;

import java.util.Objects;

/**
 * Класс устанавливает тип элемента для контейнера
 */
public class User extends Base {
    private String name;

    public User(String id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * возвращает  значение приватного поля <n>name</n> для элемента контейнера
     * @return <n>name</n>>
     */
    public String getName() {
        return name;
    }

    /**
     * Задает  значение приватного поля <n>name</n> для элемента контейнера
     * @param name значение поля <n>name</n> элемента
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * возвращает  значение приватного поля id для элемента контейнера
     * @return id элемента
     */
    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
