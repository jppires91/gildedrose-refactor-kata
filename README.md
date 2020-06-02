# Purpose
The purpose of this repository is to implement the [GildedRose Kata Exercise](https://github.com/emilybache/GildedRose-Refactoring-Kata/).

The solution was implemented in Java.

# Implementation steps

## First iteration
The first step was to create a dummy test to guarantee that all continues to work fine, even with refactor.

## Second iteration

Then I made a little refactor on the if clauses, to be easier to me to understand it. 
In that refactor I used some constants and separated the code by item types (normal, special and legendary).
Special means Aged Brie and Backstage.

## Third iteration

In the next refactor I used java 8 to filter off the legendary items (Sulfuras) since this items can't be updated.
I also updated the tests with more test scenario

I also refactored the last if clause.

## Forth Iteration

Next, I made the first big refactor: I created an ItemProcessor which processes depending on the type, using java 8 Consumers:
* Created a Map with String as key, and consumer to be applied on item as value: the key represents the item name
* Create functions for each type of item (normal, special and legendary)

In the next refactor I converted the ItemProcessor to an Enum. In that way, I don't need to use constants,
and I can obtain the corresponding item type by item name.

## Fifth Iteration

Next, I applied a big refactor again: I separated each type of item in java classes, creating configurable item processors.
In this case:
* IncreaseItemProcessor: processor to increase a configured quantity while days pass
* DecreaseItemProcessor: processor to decrease a configured quantity while days pass
* BackstageItemProcessor: processor to process Backstage items
* SulfurasItemProcessor: processor to process Sulfuras items

## Last Iteration

Finally, I applied the last refactor. 
During this refactor I realized that Backstage Item could be simpler processed, using a dynamic formula:
It seems that the quality factor to be increased while the sellIn date is positive is equal to: `max(3 - sellIn/5, 1)`
This formula means: while sellIn date is greater or equal to 10, the quality will be increased by 1, else the quality will be increased by `3 - sellIn/5`.\
e.g: 
* sellIn = 9 -> 3 - 9/5 = 2
* sellIn = 4 -> 3 - 4/5 = 3

Given this conclusion, I decided to implement a more generic Processors:
* SimpleItemProcessor: you configure a factor and a factorAfterSellIn; this factor will be sum up to the quality. 
If the date is still valid to sell it uses the **factor**, otherwise it uses the **factorAfterSellIn**. 
Note: you can configure a negative factor / factorAfterSellIn
* ComplexItemProcessor: you can configure a function to determine the factor; this function is to provide a dynamic way of calculating a factor to add to the quality.
In the case of Backstage item type, we apply a function as factor of `it -> Math.max(3 - it.sellIn/5, 1)`; 
as factorAfterSellIn we apply the following function: `it -> -it.quality` (it subtracts the same quality, making the quality = 0)
* NoOpItemProcessor: this processor doesn't do anything. It's used on Sulfuras Item type.

# How it works

First, the **ItemProcessor** is fully extensible:
* It receives by default the quality min and quality max allowed
* It validates the item quality by configured quality min and quality max
* It guarantees that item quality is in the defined limits, after being processed

As explained in the chapter before, I've created two item processors: **SimpleItemProcessor** and **ComplexItemProcessor**:
* Both receive the factor and factorAfterSell (in **ComplexItemProcessor** is a lambda function)
* if sellIn < 0 -> sums up the factorAfterSell, else sums up the factor

This exercise is fully configurable through the **ItemType** class: this class is an Enum which receives:
* name: the corresponding item name 
* itemProcessor: the corresponding item processor to be used

The following ItemType instances were created to obey to the exercise requirement:
```
    /**
     * Aged Brie item type.
     * It uses a {@link SimpleItemProcessor} with factor of 1 and factorAfterSell of 2.
     */
    AGED_BRIE("Aged Brie", new SimpleItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX, 1,2)),

    /**
     * Backstage passes type.
     * It uses a {@link ComplexItemProcessor} with:
     * - complex factor of Math.max(3 - item.sellIn/5, 1)
     * - complex factor after sell of item.quality
     */
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert",
            new ComplexItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX, it -> Math.max(3 - it.sellIn/5, 1), it -> -it.quality)),

    /**
     * Sulfuras type.
     * It uses an {@link NoOpItemProcessor} which doesn't do anything.
     */
    SULFURAS("Sulfuras, Hand of Ragnaros", new NoOpItemProcessor(Constants.QUALITY_MIN, Constants.SULFURAS_MAX)),

    /**
     * Conjured type.
     * It uses a {@link SimpleItemProcessor} with factor of -2 and factorAfterSell of -4.
     */
    CONJURED("Conjured Mana Cake", new SimpleItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX, -2 ,-4)),

    /**
     * Default type (Normal).
     * It uses a {@link SimpleItemProcessor} with factor of -1 and factorAfterSell of -2.
     */
    DEFAULT("default", new SimpleItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX,-1, -2));
```

It uses DEFAULT if the item name is not defined in the Enum.\
If you want, you can add a new Item Type with a configured Item Processor 
(e.g new type "Porto Wine" where quality increases by 2 and by 4 after sellIn date)
```
PORTO_WINE("Porto Wine", new SimpleItemProcessor(Constants.QUALITY_MIN, Constants.QUALITY_MAX,2, 4));
```

# How to Run It
This exercise runs with an input file in json format, containing all the items to be processed.
The json must be an array (check test.json to see an example)

## Compile
`make compile`\
or
`mvn clean install -DskipTests`

## Packaging
To create an executable jar:\
`make package`\
or\
`mvn package -Ppackage`

## Run
`make run`: runs with default parameters\
or\
`java -jar gilded-rose-kata.jar` (after packaging)
* -h shows help
* -n nr of days to be processed
* -f file containing the items in json (check test.json for an example)

## Unit testing
`make test`\
or\
`mvn test`
