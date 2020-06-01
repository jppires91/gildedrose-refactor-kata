package com.gildedrose.processors;

import com.gildedrose.Item;

/**
 * @author Joao Pires
 */
public class NoOpItemProcessor extends ItemProcessor {

    public NoOpItemProcessor(final int minQuality, final int maxQuality) {
        super(minQuality, maxQuality);
    }

    @Override
    public void _process(final Item item) {
        //no-op
    }
}
