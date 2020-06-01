package com.gildedrose.utils;

import com.gildedrose.Item;
import com.gildedrose.processors.SimpleItemProcessor;
import com.gildedrose.processors.NoOpItemProcessor;
import com.gildedrose.processors.ComplexItemProcessor;
import com.gildedrose.processors.ItemProcessor;

import java.util.Arrays;

/**
 * @author Joao Pires
 */
public enum ItemQuality {

    AGED_BRIE("Aged Brie", new SimpleItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX, 1,2)),

    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert",
            new ComplexItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX, it -> Math.max(3 - it.sellIn/5, 1), it -> -it.quality)),

    SULFURAS("Sulfuras, Hand of Ragnaros", new NoOpItemProcessor(Constants.QUALITY_MIN, Constants.SULFURAS_MAX)),

    CONJURED("Conjured Mana Cake", new SimpleItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX, -2 ,-4)),

    DEFAULT("default", new SimpleItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX,-1, -2));


    private final String name;
    private final ItemProcessor processor;

    ItemQuality(final String name, final ItemProcessor processor) {
        this.name = name;
        this.processor = processor;
    }

    public static void process(final Item item) {
        Arrays.stream(values())
                .filter(it -> it.name.equals(item.name))
                .findFirst()
                .orElse(ItemQuality.DEFAULT)
                .processor
                .process(item);
    }
}
