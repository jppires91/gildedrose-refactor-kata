package com.gildedrose;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @author Joao Pires
 */
public enum ItemProcessor {

    AGED_BRIE("Aged Brie", ItemProcessor::processAgedBrie),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert", ItemProcessor::processBackstageItem),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    DEFAULT("default", ItemProcessor::processNormalItem);

    private static final int QUALITY_MAX = 50;

    private static final int QUALITY_MIN = 0;

    private String name;
    private Consumer<Item> consumer;

    ItemProcessor(final String name, Consumer<Item> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    ItemProcessor(final String name) {
        this(name,it -> {});
    }

    private static void processBackstageItem(final Item item) {
        item.sellIn--;
        item.quality = Math.min(item.quality + 1, QUALITY_MAX);

        if (item.sellIn < 10) {
            item.quality = Math.min(item.quality + 1, QUALITY_MAX);
        }

        if (item.sellIn < 3) {
            item.quality = Math.min(item.quality + 1, QUALITY_MAX);
        }

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private static void processAgedBrie(final Item item) {
        item.sellIn--;
        item.quality = Math.min(item.quality + 1, QUALITY_MAX);

        if (item.sellIn < 0) {
            item.quality = Math.min(item.quality + 1, QUALITY_MAX);
        }
    }

    private static void processNormalItem(final Item item) {
        item.sellIn--;
        item.quality = Math.max(item.quality - 1, QUALITY_MIN);

        if (item.sellIn < 0) {
            item.quality = Math.max(item.quality - 1, QUALITY_MIN);
        }
    }

    public static void process(final Item item) {
        Arrays.stream(values())
                .filter(it -> it.name.equals(item.name))
                .findFirst()
                .orElse(ItemProcessor.DEFAULT)
                .consumer
                .accept(item);
    }
}
