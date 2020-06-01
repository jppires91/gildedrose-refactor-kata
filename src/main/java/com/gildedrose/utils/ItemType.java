package com.gildedrose.utils;

import com.gildedrose.Item;
import com.gildedrose.processors.SimpleItemProcessor;
import com.gildedrose.processors.NoOpItemProcessor;
import com.gildedrose.processors.ComplexItemProcessor;
import com.gildedrose.processors.ItemProcessor;

import java.util.Arrays;

/**
 * Enum that determines the type of an item.
 * You can add new types, it receives the expected name and the corresponding {@link ItemProcessor}.
 *
 * @author Joao Pires
 */
public enum ItemType {

    /**
     * Aged Brie item type.
     * It uses a {@link SimpleItemProcessor} with factor of 1 and factorAfterSell of 2.
     */
    AGED_BRIE("Aged Brie", new SimpleItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX, 1,2)),

    /**
     * Backstage passes type.
     * It uses a {@link ComplexItemProcessor} with:
     * - complex factor of Math.max(3 - item.sellIn/5, 1)
     * - complex factor after sell of item.quality
     */
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert",
            new ComplexItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX, it -> Math.max(3 - it.sellIn/5, 1), it -> -it.quality)),

    /**
     * Sulfuras type.
     * It uses an {@link NoOpItemProcessor} which doesn't do anything.
     */
    SULFURAS("Sulfuras, Hand of Ragnaros", new NoOpItemProcessor(Constants.QUALITY_MIN, Constants.SULFURAS_MAX)),

    /**
     * Conjured type.
     * It uses a {@link SimpleItemProcessor} with factor of -2 and factorAfterSell of -4.
     */
    CONJURED("Conjured Mana Cake", new SimpleItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX, -2 ,-4)),

    /**
     * Default type (Normal).
     * It uses a {@link SimpleItemProcessor} with factor of -1 and factorAfterSell of -2.
     */
    DEFAULT("default", new SimpleItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX,-1, -2));

    /**
     * The name of the item, to get the type from.
     */
    private final String name;

    /**
     * The corresponding {@link ItemProcessor} for this item type.
     */
    private final ItemProcessor processor;

    /**
     * Constructs an item type by name and processor.
     *
     * @param name the corresponding name of the item
     * @param processor the processor to process the item
     */
    ItemType(final String name, final ItemProcessor processor) {
        this.name = name;
        this.processor = processor;
    }

    /**
     * Processes the item, obtaining the corresponding enum from the item name.
     *
     * @param item item to be processed
     */
    public static void process(final Item item) {
        Arrays.stream(values())
                .filter(it -> it.name.equals(item.name))
                .findFirst()
                .orElse(ItemType.DEFAULT)
                .processor
                .process(item);
    }
}
