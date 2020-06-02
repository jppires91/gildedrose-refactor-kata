package com.gildedrose.processors;

import com.gildedrose.Item;

import java.util.function.Function;

/**
 * The Complex Item Processor allows you to define a complex factor, defined by a function that receive the item.
 * For instance, you can apply a factor of (item.sellIn * 2).
 *
 * @author Joao Pires
 */
public class ComplexItemProcessor extends ItemProcessor {

    /**
     * The factor function to apply to quality.
     */
    private final Function<Item, Integer> factor;

    /**
     * The factor after sell in function to apply to quality.
     */
    private final Function<Item, Integer> factorAfterSellIn;

    /**
     * Constructs a ComplexItemProcessor with minQuality, maxQuality, factor and factorAfterSellIn.
     *
     * @param minQuality        min quality allowed
     * @param maxQuality        max quality allowed
     * @param factor            the factor function to apply
     * @param factorAfterSellIn the factor after sellin function to apply
     */
    public ComplexItemProcessor(final int minQuality, final int maxQuality, final Function<Item, Integer> factor, final Function<Item, Integer> factorAfterSellIn) {
        super(minQuality, maxQuality);
        this.factor = factor;
        this.factorAfterSellIn = factorAfterSellIn;
    }

    /**
     * Processes the item internally with the factor and factorAfterSellIn function.
     *
     * @param item the item to be processed
     */
    protected void _process(final Item item) {
        item.sellIn--;

        if (item.sellIn < 0) {
            item.quality += factorAfterSellIn.apply(item);
        } else {
            item.quality += factor.apply(item);
        }
    }
}
