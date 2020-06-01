package com.gildedrose.processors;

import com.gildedrose.Item;
import com.google.common.base.Preconditions;

/**
 * Abstract item processor, to be implemented by an item processor.
 *
 * @author Joao Pires
 */
public abstract class ItemProcessor {

    /**
     * Min quality allowed on item processor.
     */
    protected final int minQuality;

    /**
     * Max quality allowed on item processor.
     */
    protected final int maxQuality;

    /**
     * Constructs an item processor with min quality and max quality.
     *
     * @param minQuality the min quality allowed for this item processor
     * @param maxQuality the max quality allowed for this item processor
     */
    protected ItemProcessor(final int minQuality, final int maxQuality) {
        this.minQuality = minQuality;
        this.maxQuality = maxQuality;
    }

    /**
     * Abstract internal method to be implemented by child item processors.
     *
     * @param item the item to be processed
     */
    protected abstract void _process(final Item item);

    /**
     * Processes the item:
     * - checks if item quality is right, in bound the limits)
     * - processes the item internally (by child item processor)
     * - update item quality to max quality or min quality (if out of bounds)
     *
     * @param item the item to be processed
     */
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
