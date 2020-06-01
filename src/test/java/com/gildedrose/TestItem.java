package com.gildedrose;

/**
 * Test item is an item that implements equals to be easier to test (assert equals).
 *
 * @author Joao Pires
 */
public class TestItem extends Item {
    public TestItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestItem testItem = (TestItem) o;

        return this.name.equals(testItem.name) && this.quality == testItem.quality && this.sellIn == testItem.sellIn;
    }
}
