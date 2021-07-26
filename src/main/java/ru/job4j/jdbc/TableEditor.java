package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    /**
     * Соединение с БД
     */
    private Connection connection;
    /**
     * ??? где его брать
     */
    private final Properties properties;

    public TableEditor(String propertiesFileName) {
        properties = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(propertiesFileName))) {
            properties.load(br);
            initConnection();
        } catch (IOException ex) {
            System.out.println("Ошибка чтения конфигурационного файла: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Создает подключение к БД
     */
    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver"));
            String url = properties.getProperty("url");
            String login = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Исполняет SQL запрос
     * @param sql сформированный запрос
     */
    private void execute(String sql) {
        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Ошибка подключения к базе данных!" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * создает пустую таблицу без столбцов с указанным именем;
     * @param tableName наименование создаваемой таблицы
     *
     */
    private void createTable(String tableName) {
        String sql = String.format("CREATE TABLE %s (%s);", tableName, "id serial primary key");
        execute(sql);
    }

    /**
     * удаляет таблицу по указанному имени;
     * @param tableName наименование удаляемой таблицы
     */
    public void dropTable(String tableName) {
        String sql = "DROP TABLE IF EXISTS " + tableName + ";";
        execute(sql);
    }

    /**
     * добавляет столбец в таблицу;
     * @param tableName наименование таблицы в которой создает столбец
     * @param columnName наименование создаваемого столбца
     * @param type тип содержимого столбца
     */
    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s NOT NULL;", tableName, columnName, type);
        execute(sql);
    }

    /**
     * удаляет столбец из таблицы;
     * @param tableName наименование таблицы из которой удаляется столбец
     * @param columnName наименование удаляемого столбца
     */
    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        execute(sql);
    }

    /**
     * переименовывает столбец.
     * @param tableName наименование таблицы в которой переименовыывает столбец
     * @param columnName текущее наименование столбца
     * @param newColumnName новое наименование столбца
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        execute(sql);
    }

    /**
     * Создает  и возвращает схему таблицы, а именно наименование столбцов и типы данных.
     * В данном случае метод используется для проверки того, что таблица создалась
     * @param connection  создаваемое соединение
     * @param tableName наименование таблицы схему которой создает метод
     * @return строковое предстваление схемы таблицы
     * @throws SQLException - при ошибке доступа к базе данных
     */
    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    /**
     * Закрывает поток из ресурса и высвобождает ресурс.
     * Рекомендуется использовать этот метод в конструкции try -with-resources
     * @throws Exception SQLException
     */
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String tableName = "testTable";
        try (TableEditor editor = new TableEditor("app.properties")) {
            editor.dropTable(tableName);
            editor.createTable(tableName);
            editor.addColumn(tableName, "column1", "int");
            editor.addColumn(tableName, "column2", "varchar(50)");
            editor.addColumn(tableName, "column3", "text");
            System.out.println(getTableScheme(editor.connection, tableName));

            System.out.println("+".repeat(30).concat(System.lineSeparator()));

            editor.dropColumn(tableName, "column3");
            editor.renameColumn(tableName, "column2", "newColumn2");
            System.out.println(getTableScheme(editor.connection, tableName));
            editor.dropTable(tableName);
        }
    }
}

