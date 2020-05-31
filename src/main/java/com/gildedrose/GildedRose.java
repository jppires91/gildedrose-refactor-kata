package com.gildedrose;

import com.google.common.collect.ImmutableList;

import java.util.List;

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
        for (int i = 0; i < items.length; i++) {
            final Item item = items[i];
            if (isNormalItem(item) && item.quality > 0) {
                item.quality--;
                item.sellIn--;

                if (item.sellIn < 0) {
                    item.quality--;
                }
                continue;
            }

            if (SPECIAL_ITEMS.contains(item.name)) {
                if (item.quality < 50) {
                    item.quality++;

                    if (BACKSTAGE_PASSES.equals(item.name)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality += 2;
                            }
                        }
                    }
                }
            }

            if (!isLegendaryItem(item)) {
                item.sellIn--;
            }

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
