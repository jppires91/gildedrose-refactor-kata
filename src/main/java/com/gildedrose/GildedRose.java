package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    final Item[] items;

    public GildedRose(final Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(ItemProcessor::process);
    }
}
