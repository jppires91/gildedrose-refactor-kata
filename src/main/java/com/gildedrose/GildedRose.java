package com.gildedrose;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GildedRose {
    final Item[] items;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";


    private static final List<String> SPECIAL_ITEMS = ImmutableList.of(AGED_BRIE, BACKSTAGE_PASSES);

    private static final List<String> LEGENDARY_ITEMS = ImmutableList.of(SULFURAS);

    public GildedRose(final Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).filter(it -> !isLegendaryItem(it)).forEach(item -> {
            //Normal Item processing
            if (isNormalItem(item) && item.quality > 0) {
                item.quality--;
                item.sellIn--;

                if (item.sellIn < 0) {
                    item.quality--;
                }
            } else

                //Special item processing
                if (isSpecialItem(item)) {
                    if (item.quality < 50) {
                        item.quality++;

                        if (BACKSTAGE_PASSES.equals(item.name)) {
                            if (item.quality < 50) {
                                if (item.sellIn < 11) {
                                    item.quality++;
                                }

                                if (item.sellIn < 4) {
                                    item.quality++;
                                }
                            }
                        }
                    }

                    item.sellIn--;

                    //if sellin < 0
                    if (item.sellIn < 0) {
                        if (!AGED_BRIE.equals(item.name)) {
                            if (!BACKSTAGE_PASSES.equals(item.name)) {
                                if (!SULFURAS.equals(item.name) && item.quality > 0) {
                                    item.quality--;
                                }
                            } else {
                                item.quality = 0;
                            }
                        } else if (item.quality < 50) {
                            item.quality++;
                        }
                    }
                }
        });
    }

    private boolean isSpecialItem(final Item item) {
        return SPECIAL_ITEMS.contains(item.name);
    }

    private boolean isLegendaryItem(final Item item) {
        return LEGENDARY_ITEMS.contains(item.name);
    }

    private boolean isNormalItem(final Item item) {
        return !isLegendaryItem(item) && !isSpecialItem(item);
    }
}
