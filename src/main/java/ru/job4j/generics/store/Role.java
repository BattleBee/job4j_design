package ru.job4j.generics.store;

import java.util.Objects;

public class Role extends Base {
    private String name;

    public Role(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
        Role role = (Role) o;
        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

