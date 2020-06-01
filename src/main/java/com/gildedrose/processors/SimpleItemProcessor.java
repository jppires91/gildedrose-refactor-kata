package com.gildedrose.processors;

import com.gildedrose.Item;

/**
 * SimpleItemProcessor is an item processor that adds a configured factor to the quality.
 *
 * @author Joao Pires
 */
public class SimpleItemProcessor extends ItemProcessor {

    /**
     * The configured factor to be used.
     */
    private final int factor;

    /**
     * The configured factor to be used, after sellIn (item.sellIn < 0).
     */
    private final int factorAfterSell;

    /**
     * Constructs a SimpleItemProcessord with minQuality, maxQuality, factor and factorAfterSell.
     *
     * @param minQuality      the min quality allowed
     * @param maxQuality      the max quality allowed
     * @param factor          the factor to add to quality
     * @param factorAfterSell the factor to add to quality after sell
     */
    public SimpleItemProcessor(final int minQuality, final int maxQuality, final int factor, final int factorAfterSell) {
        super(minQuality, maxQuality);
        this.factor = factor;
        this.factorAfterSell = factorAfterSell;
    }

    /**
     * Processes the item internally:
     * - Adds to quality the factorAfterSell if item.sellIn < 0
     * - Adds to quality the factor if item.sellIn >= 0
     *
     * @param item the item to be processed
     */
    public void _process(final Item item) {
        item.sellIn--;

        if (item.sellIn < 0) {
            item.quality = item.quality + factorAfterSell;
        } else {
            item.quality = item.quality + factor;
        }
    }
}
