package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

public class TriggerTest {

    @Test
    public void triggerTest() {
        Assert.assertEquals(1, new Trigger().someLogic());
    }

}