package com.gildedrose.processors;

import com.gildedrose.Item;

/**
 * @author Joao Pires
 */
public class DecreaseItemProcessor implements ItemProcessor {

    private final int factor;

    private final int factorAfterSell;

    private final int minQuality;

    public DecreaseItemProcessor(final int factor, final int factorAfterSell, final int minQuality) {
        this.factor = factor;
        this.factorAfterSell = factorAfterSell;
        this.minQuality = minQuality;
    }

    public void process(final Item item) {
        item.sellIn--;
        item.quality = Math.max(item.quality - factor, minQuality);

        if (item.sellIn < 0) {
            item.quality = Math.max(item.quality - factorAfterSell, minQuality);
        }
    }
}
