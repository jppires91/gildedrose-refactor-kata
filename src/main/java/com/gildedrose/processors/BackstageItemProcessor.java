package com.gildedrose.processors;

import com.gildedrose.Item;

/**
 * @author Joao Pires
 */
public class BackstageItemProcessor implements ItemProcessor {

    private final int factor;

    private final int maxQuality;

    public BackstageItemProcessor(final int factor, final int maxQuality) {
        this.factor = factor;
        this.maxQuality = maxQuality;
    }

    public void process(final Item item) {
        item.sellIn--;
        item.quality = Math.min(item.quality + factor, maxQuality);

        if (item.sellIn < 10) {
            item.quality = Math.min(item.quality + factor, maxQuality);
        }

        if (item.sellIn < 3) {
            item.quality = Math.min(item.quality + factor, maxQuality);
        }

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
