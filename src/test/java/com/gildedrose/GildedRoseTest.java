package com.gildedrose;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void dummyTest() {
        final Item[] items = new Item[] {
                new TestItem("+5 Dexterity Vest", 10, 20), //
                new TestItem("+5 Dexterity Vest", 2, 20), //
                new TestItem("Aged Brie", 2, 0), //
                new TestItem("Elixir of the Mongoose", 5, 7), //
                new TestItem("Sulfuras, Hand of Ragnaros", 0, 80), //
                new TestItem("Sulfuras, Hand of Ragnaros", -1, 80),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 11, 20),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 5, 20),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 3, 49),
                new TestItem("Conjured Mana Cake", 3, 6)
        };

        final Item[] expectedItems = new Item[] {
                new TestItem("+5 Dexterity Vest", 6, 16), //
                new TestItem("+5 Dexterity Vest", -2, 14), //
                new TestItem("Aged Brie", -2, 6), //
                new TestItem("Elixir of the Mongoose", 1, 3), //
                new TestItem("Sulfuras, Hand of Ragnaros", 0, 80), //
                new TestItem("Sulfuras, Hand of Ragnaros", -1, 80),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 11, 24),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 7, 27),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 1, 32),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 6, 50),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", 1, 50),
                new TestItem("Backstage passes to a TAFKAL80ETC concert", -1, 0),
                new TestItem("Conjured Mana Cake", -1, 0)
        };

        GildedRose app = new GildedRose(items);

        for (int i = 0; i < 4; i++) {
            app.updateQuality();
        }

        //Making test with lists because array.equals is done by reference
        assertEquals(ImmutableList.copyOf(expectedItems), ImmutableList.copyOf(items));

    }

}
