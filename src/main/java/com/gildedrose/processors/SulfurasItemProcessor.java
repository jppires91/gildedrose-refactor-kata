package com.gildedrose.processors;

import com.gildedrose.Item;
import com.gildedrose.processors.ItemProcessor;
import com.google.common.base.Preconditions;

/**
 * @author Joao Pires
 */
public class SulfurasItemProcessor implements ItemProcessor {

    private final int maxQuality;

    public SulfurasItemProcessor(final int maxQuality) {
        this.maxQuality = maxQuality;
    }

    @Override
    public void process(final Item item) {
        Preconditions.checkArgument(item.quality <= maxQuality);
    }
}
