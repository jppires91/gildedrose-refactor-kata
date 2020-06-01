package com.gildedrose.processors;

import com.gildedrose.Item;
import com.google.common.base.Preconditions;

/**
 * @author Joao Pires
 */
public abstract class ItemProcessor {

    protected final int minQuality;

    protected final int maxQuality;

    protected ItemProcessor(final int minQuality, final int maxQuality) {
        this.minQuality = minQuality;
        this.maxQuality = maxQuality;
    }

    protected abstract void _process(final Item item);

    public void process(final Item item) {
        Preconditions.checkArgument(item.quality <= maxQuality);
        Preconditions.checkArgument(item.quality >= minQuality);

        _process(item);

        if (item.quality > maxQuality) {
            item.quality = maxQuality;
        } else if (item.quality < minQuality) {
            item.quality = minQuality;
        }
    }
}
