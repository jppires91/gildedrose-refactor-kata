package com.gildedrose;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Joao Pires
 */
public class ItemProcessor {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final int QUALITY_MAX = 50;

    private static final int QUALITY_MIN = 0;

    private static final Map<String, Consumer<Item>> map = ImmutableMap.<String, Consumer<Item>>builder()
            .put(AGED_BRIE, ItemProcessor::processAgedBrie)
            .put(BACKSTAGE_PASSES, ItemProcessor::processBackstageItem)
            .put(SULFURAS, it -> {})
            .build();

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
        map.getOrDefault(item.name, ItemProcessor::processNormalItem).accept(item);
    }

}
