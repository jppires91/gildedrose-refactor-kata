package com.gildedrose.processors;

import com.gildedrose.Item;

/**
 * @author Joao Pires
 */
public class IncreaseItemProcessor implements ItemProcessor {

    private final int factor;

    private final int factorAfterSell;

    private final int maxQuality;

    public IncreaseItemProcessor(final int factor, final int factorAfterSell, final int maxQuality) {
        this.factor = factor;
        this.factorAfterSell = factorAfterSell;
        this.maxQuality = maxQuality;
    }

    public void process(final Item item) {
        item.sellIn--;
        item.quality = Math.min(item.quality + factor, maxQuality);

        if (item.sellIn < 0) {
            item.quality = Math.min(item.quality + factorAfterSell, maxQuality);
        }
    }
}
