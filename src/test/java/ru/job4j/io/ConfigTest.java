package ru.job4j.io;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/main/resources/properties/testWithoutComments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(CoreMatchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentAndEmptyLines() {
        String path = "src/main/resources/properties/testFullContent.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr"));
        assertThat(config.value("surname"), is("Arsentev"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSyntax() {
        String path = "src/main/resources/properties/testWrongSyntax.properties";
        Config config = new Config(path);
        config.load();
    }

}