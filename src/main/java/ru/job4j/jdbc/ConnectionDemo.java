package ru.job4j.jdbc;

import ru.job4j.io.Config;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Подключение к базе данных
 * Все необходимые данные(драйвер к БД, url, имя пользователя, пароль) берем из конфигурационного файла настроек,
 * в данном случае из файла <n>app.properties</n>,который читаем с помощью класса Config
 */
public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        var conf = new Config("app.properties"); // указываем из какого конфигурационного файла берем данные
        conf.load();
        Class.forName(conf.value("driver")); // регистрация драйвера в системе
        String url = conf.value("url");
        String login = conf.value("username");
        String password = conf.value("password");
        try (var connection = DriverManager.getConnection(url, login, password)) { // соединение с БД
            DatabaseMetaData metaData = connection.getMetaData(); //содетжит информацию о БД и ее внутренней структуре
            System.out.println(metaData.getUserName()); // получение имя пользователя
            System.out.println(metaData.getURL()); // получение url
        }
    }
}