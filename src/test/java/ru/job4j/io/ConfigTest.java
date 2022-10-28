package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/main/resources/properties/testWithoutComments.properties";
        Config config = new Config(path);
        config.load();
        assertEquals("Petr Arsentev", config.value("name"));
        assertNull(config.value("surname"));
    }

    @Test
    public void whenPairWithCommentAndEmptyLines() {
        String path = "src/main/resources/properties/testFullContent.properties";
        Config config = new Config(path);
        config.load();
        assertEquals("Petr", config.value("name"));
        assertEquals("Arsentev", config.value("surname"));
    }

    @Test
    public void whenWrongSyntax() {
        String path = "src/main/resources/properties/testWrongSyntax.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, () -> config.load());
    }

}