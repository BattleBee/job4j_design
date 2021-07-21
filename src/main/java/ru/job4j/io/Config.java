package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Работает с данными конфигурационного файла, считывает их, записыввает значкения в карту
 * и предоставляет эти данные по запросу.
 * Конфигурационный файл имеет расширение .properties
 */
public class Config {
    /**
     * путь к конфигурационному файлу
     */
    private final String path;
    /**
     * карта для хранения  значений конфигурационного файла
     */
    private final Map<String, String> values;

    public Config(final String path) {
        this.path = path;
        values = new HashMap<>();
    }

    /**
     * Осуществляет считывание всех ключей из конфигурационного файла и записывает их в карту values.
     * При этом, пустые строки и коментарии (начинающиеся с #) - пропускаются.
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