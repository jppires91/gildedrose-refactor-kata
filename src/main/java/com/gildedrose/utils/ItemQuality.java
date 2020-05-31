package com.gildedrose.utils;

import com.gildedrose.Item;
import com.gildedrose.processors.SulfurasItemProcessor;
import com.gildedrose.processors.BackstageItemProcessor;
import com.gildedrose.processors.DecreaseItemProcessor;
import com.gildedrose.processors.IncreaseItemProcessor;
import com.gildedrose.processors.ItemProcessor;

import java.util.Arrays;

/**
 * @author Joao Pires
 */
public enum ItemQuality {

    AGED_BRIE("Aged Brie", new IncreaseItemProcessor(1,1, Constants.QUALITY_MAX)),

    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert", new BackstageItemProcessor(1, Constants.QUALITY_MAX)),

    SULFURAS("Sulfuras, Hand of Ragnaros", new SulfurasItemProcessor(Constants.SULFURAS_MAX)),

    CONJURED("Conjured Mana Cake", new DecreaseItemProcessor(2, 2, Constants.QUALITY_MIN)),

    DEFAULT("default", new DecreaseItemProcessor(1, 1, Constants.QUALITY_MIN));


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
