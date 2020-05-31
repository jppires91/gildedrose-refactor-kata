package com.gildedrose;

import com.gildedrose.utils.ItemQuality;

import java.util.Arrays;

class GildedRose {
    final Item[] items;

    public GildedRose(final Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(ItemQuality::process);
    }
}
