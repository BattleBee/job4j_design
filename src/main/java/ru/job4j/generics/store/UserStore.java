package ru.job4j.generics.store;

/**
 * Класс реализует пользовательскую версю контейнера по шаблону <n>Store</n> для типа даных <n>User</n>
 */
public class UserStore implements Store<User> {
    /**
     * контейнер по шаблону <n>Store</n> для типа даных <n>User</n>
     * Объект класса MemStore<>() представляет собой HashMap<>(),
     * @see MemStore
     */
    private final Store<User> uStore = new MemStore<>();

    /**
     * Добавляет элемент в хранилище
     * @param model обобщенный тип элемента
     */
    @Override
    public void add(User model) {
        uStore.add(model);
    }

    /**
     * Изменяет значение элемента находячщегося в хранилище по индексу
     * @param id <n>id</n> элемента значение которого нужно заменить
     * @param model новое значение элемента обобщенного типа
     * @return true если есть в хранилище есть элемент с данным <n>id</n> и его значение измененго
     * и false если такого элемента нет
     */
    @Override
    public boolean replace(String id, User model) {
        return uStore.replace(id, model);
    }

    /**
     * Удаляет элемент хранилища с указанным  значением  <n>id</n>
     * @param id <n>id</n> элемента который нужно удалить
     * @return true если есть в хранилище есть элемент с данным <n>id</n> и его удалось удалить
     * и false если элемента с таким <n>id</n> не было
     */
    @Override
    public boolean delete(String id) {
        return uStore.delete(id);
    }

    /**
     * Возвращает значение элемента с указанным <n>id</n>
     * @param id <n>id</n> элемента
     * @return значение элемента по заданному <n>id</n> или null, если элемента с аким <n>id</n> нет
     */
    @Override
    public User findById(String id) {
        return uStore.findById(id);
    }
}
