package com.gildedrose.processors;

import com.gildedrose.Item;

import java.util.function.Function;

/**
 * @author Joao Pires
 */
public class ComplexItemProcessor extends ItemProcessor {

    private final Function<Item, Integer> factor;

    private final Function<Item, Integer> factorAfterSellIn;

    public ComplexItemProcessor(final int minQuality, final int maxQuality, final Function<Item, Integer> factor, final Function<Item, Integer> factorAfterSellIn) {
        super(minQuality, maxQuality);
        this.factor = factor;
        this.factorAfterSellIn = factorAfterSellIn;
    }


    /**
     * Factor:
     * sellIn >= 10 -> 1 -> 20/5 -> 4 3 - sellIn/5
     * sellIn < 10 -> 2 / 10 / 5 -> 2
     * sellIn < 5 -> 3 -> 5 / 5 -> 1
     * @param item
     */
    public void _process(final Item item) {
        item.sellIn--;

        if (item.sellIn < 0) {
            item.quality += factorAfterSellIn.apply(item);
        } else {
            item.quality += factor.apply(item);
        }
    }
}
