package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * В данном классе практикуется специальный класс PrepareStatement. Данный класс в отличии от Statement
 * предназначен для DML операций – INSERT, SELECT, UPDATE, DELETE.
 * Предварительно создал в БД таблицу по скрипту:
 * create table cities(
 *     id serial primary key,
 *     name text,
 *     population int
 * );
 */
public class PrepareStatementDemo {

    private Connection connection;

    public PrepareStatementDemo() throws Exception {
        initConnection();
    }

    /**
     * Создает подключение к базе данных
     * подключение к URL
     * @throws Exception SQLException - если возникает ошибка доступа к БД или URL-адрес равен null
     */
    public void initConnection() throws Exception {
        var conf = new Config("app.properties");
        conf.load();
        Class.forName(conf.value("driver"));
        String url = conf.value("url");
        String login = conf.value("username");
        String password = conf.value("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    /**
     * Добавляет значение в таблицу
     * Обратить внимание- Во-первых, параметры, т.е. места куда будут подставляться аргументы обозначаются «?».
     * Во-вторых, для подстановки аргументов используются методы виды “setТип(позиция, аргумент)”.
     * В-третьих, позиция аргумента считается как его порядковый номер, а не как индекс, т.е. позиции начинаются с 1.
     * @param city City вносимый в таблицу
     * @return City
     */
    public City insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement("insert into cities(name, population) values (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    /**
     * Обновляет данные в таблице, вносит измененные значения поля в таблицу
     * @param city City
     * @return true или false в зависимости от выполнения
     */
    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Удаление значения из таблицы
     *
     * @param id City id
     * @return true или false в зависимости от выполнения
     */
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public void printAll() {
        List<City> cities = this.findAll();
        for (City city : cities) {
            System.out.println(city.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        City msk = new City(1, "Москва", 5_000_000);
        City spb = new City(2, "Питер", 3_000_000);
        PrepareStatementDemo psd = new PrepareStatementDemo();
        psd.insert(msk);
        psd.insert(spb);
        psd.printAll();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        spb.setName("NewName");
        spb.setPopulation(250_000);
        psd.update(spb);
        psd.printAll();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        psd.delete(spb.getId());
        psd.printAll();
    }
}