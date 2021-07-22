package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Работает с данными конфигурационного файла, считывает их, записыввает значения в HashMap
 * и предоставляет эти данные по запросу.
 * Конфигурационный файл имеет расширение .properties
 */
public class Config {
    /**
     * Путь к конфигурационному файлу.
     */
    private final String path;
    /**
     * Карта для хранения  значений конфигурационного файла.
     */
    private final Map<String, String> values;

    /**
     * Класс работает с данными конфигурационного файла, считывает их, записыввает значкения в HashMap
     * и предоставляет эти данные по запросу.
     * @param path Путь к конфигурационному файлу.
     */
    public Config(final String path) {
        this.path = path;
        values = new HashMap<>();
    }

    /**
     * Считывает ключи и значения из конфигурационного файла и записывает их в HashMap.
     * Пустые строки и коментарии (начинающиеся с #) - пропускаются.
     */
    public void load() {
        try (var in = new BufferedReader(new FileReader(path))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
                String[] expressions = line.split("=", 2);
                if (
                        expressions.length < 2
                                || expressions[0].isEmpty()
                                || expressions[1].isEmpty()
                ) {
                    throw new IllegalArgumentException();
                }
                values.put(expressions[0].trim(), expressions[1].trim());
            }
        } catch (IOException ex) {
            System.out.println("Ошибка чтения файла конфигурации: " + ex.getMessage());
        }
    }

    /**
     * Предоставляет значение элемента по ключу
     * @param key ключ элемента
     * @return значение элемента
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        var out = new StringJoiner(System.lineSeparator());
        try (var read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        var config = new Config("app.properties");
        config.load();
        System.out.println("values = " + config.values);
        System.out.println("password = " + config.values.get("password"));
    }
}