package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.StringJoiner;

public class StatementDemo {
    /**
     * Создает подключение к базе данных
     * @return подключение к URL
     * @throws Exception SQLException - если возникает ошибка доступа к БД или URL-адрес равен null
     */
    private static Connection getConnection() throws Exception {
        var conf = new Config("app.properties");
        conf.load();
        Class.forName(conf.value("driver")); // регистрация драйвера в системе
        String url = conf.value("url");
        String login = conf.value("username");
        String password = conf.value("password");
        return DriverManager.getConnection(url, login, password);
    }

    /**
     * Создает  и возвращает схему таблицы, а именно наименование столбцов и типы данных.
     * В данном случае метод используется для проверки того, что таблица создалась
     * @param connection  создаваемое соединение
     * @param tableName наименование таблицы схему которой создает метод
     * @return строковое предстваление схемы таблицы
     * @throws Exception SQLException - при ошибке доступа к базе данных
     */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(20).concat(System.lineSeparator()); // разделитель
        var header = String.format("%-10s|%-10s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (var i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-10s|%-10s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        try (var connection = getConnection()) { // создаем соединение
            try (var statement = connection.createStatement()) { // создаем запрос
                var sql = String.format(
                        "create table if not exists demo_table(%s, %s);", 
                        "id serial primary key",
                        "name text"
                );
                statement.execute(sql); // исполнение сформированного запроса
// Чтобы проверить, что таблица создалась, выведем ее схему с помощью метода getTableScheme, а именно столбцы и их типы.
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }
}
