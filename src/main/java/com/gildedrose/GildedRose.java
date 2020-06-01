package com.gildedrose;

import com.gildedrose.utils.ItemType;

import java.util.Arrays;

class GildedRose {
    final Item[] items;

    public GildedRose(final Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        //For each item, it processes from the enum
        Arrays.stream(items).forEach(ItemType::process);
    }
}
