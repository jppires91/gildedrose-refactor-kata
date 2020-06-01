package com.gildedrose.processors;

import com.gildedrose.Item;

/**
 * NoOpItemProcessor is a processor that doesn't do anything.
 *
 * @author Joao Pires
 */
public class NoOpItemProcessor extends ItemProcessor {

    /**
     * Constructor matching super.
     *
     * @param minQuality the min quality allowed
     * @param maxQuality the max quality allowed
     */
    public NoOpItemProcessor(final int minQuality, final int maxQuality) {
        super(minQuality, maxQuality);
    }

    /**
     * Process the item (no operation).
     *
     * @param item the item to be processed
     */
    @Override
    public void _process(final Item item) {
        //no-op
    }
}
