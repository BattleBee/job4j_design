package ru.job4j.generics.store;

/**
 * реализация для пользователя - ++
 */
public class UserStore implements Store<User> {

    private final Store<User> userStore = new MemStore<>();

    @Override
    public void add(User model) {
        userStore.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return userStore.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return userStore.delete(id);
    }

    @Override
    public User findById(String id) {
        return userStore.findById(id);
    }
}
