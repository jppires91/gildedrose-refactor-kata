package com.gildedrose.processors;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Joao Pires (joao.pires@feedzai.com)
 */
public class SimpleItemProcessorTest {

    @Test
    public void simpleTestWithFactor1() {
        final ItemProcessor itemProcessor = new SimpleItemProcessor(0, 20, 1, 1);

        final Item item = new Item("dummy", 2, 2);

        for (int i = 0; i < 4; i++) {
            itemProcessor.process(item);
        }

        assertEquals(6, item.quality);
    }

    @Test
    public void simpleTestWithFactor1andFactorAfterSell2() {
        final ItemProcessor itemProcessor = new SimpleItemProcessor(0, 20, 1, 2);

        final Item item = new Item("dummy", 2, 2);

        for (int i = 0; i < 4; i++) {
            itemProcessor.process(item);
        }

        assertEquals(8, item.quality);
    }

    @Test
    public void testWithMaxQuality() {
        final ItemProcessor itemProcessor = new SimpleItemProcessor(0, 5, 1, 2);

        final Item item = new Item("dummy", 2, 2);

        for (int i = 0; i < 4; i++) {
            itemProcessor.process(item);
        }

        assertEquals(5, item.quality);
    }

    @Test
    public void testWithMinQuality() {
        final ItemProcessor itemProcessor = new SimpleItemProcessor(0, 5, -1, -2);

        final Item item = new Item("dummy", 2, 2);

        for (int i = 0; i < 4; i++) {
            itemProcessor.process(item);
        }

        assertEquals(0, item.quality);
    }

    @Test
    public void testWithInvalidMinQuality() {
        final ItemProcessor itemProcessor = new SimpleItemProcessor(0, 5, -1, -2);

        final Item item = new Item("dummy", 2, -2);
        try {

            for (int i = 0; i < 4; i++) {
                itemProcessor.process(item);
            }
        } catch (final Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            return ;
        }

        assertTrue(false);

    }

    @Test
    public void testWithInvalidMaxQuality() {
        final ItemProcessor itemProcessor = new SimpleItemProcessor(0, 5, -1, -2);

        final Item item = new Item("dummy", 2, 10);
        try {

            for (int i = 0; i < 4; i++) {
                itemProcessor.process(item);
            }
        } catch (final Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            return ;
        }

        assertTrue(false);

    }
}
