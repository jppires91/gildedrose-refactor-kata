package com.gildedrose.processors;

import com.gildedrose.Item;

/**
 * @author Joao Pires
 */
public class SimpleItemProcessor extends ItemProcessor {

    private final int factor;

    private final int factorAfterSell;


    public SimpleItemProcessor(final int minQuality, final int maxQuality, final int factor, final int factorAfterSell) {
        super(minQuality, maxQuality);
        this.factor = factor;
        this.factorAfterSell = factorAfterSell;
    }

    public void _process(final Item item) {
        item.sellIn--;

        if (item.sellIn < 0) {
            item.quality = item.quality + factorAfterSell;
        } else {
            item.quality = item.quality + factor;
        }
    }
}
