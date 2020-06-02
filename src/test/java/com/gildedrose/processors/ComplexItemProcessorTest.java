package com.gildedrose.processors;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Joao Pires (joao.pires@feedzai.com)
 */
public class ComplexItemProcessorTest {

    @Test
    public void testComplexFunction() {

        //double the quantity on each iteration
        final ItemProcessor itemProcessor = new ComplexItemProcessor(0, 50, it -> it.quality, it -> it.quality);

        final Item item = new Item("dummy", 2, 2);

        for (int i = 0; i < 4; i++) {
            itemProcessor.process(item);
        }

        assertEquals(32, item.quality);
    }

}
