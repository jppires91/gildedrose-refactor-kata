package com.gildedrose;

import com.beust.jcommander.JCommander;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gildedrose.utils.Constants;
import com.gildedrose.utils.GildedRoseParameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Main class to execute GildedRose.
 *
 * @author Joao Pires
 */
public class Main {

    /**
     * Main method to execute GildedRose.
     *
     * @param args the program args
     * @throws IOException in case of any error translating the file
     */
    public static void main(final String[] args) throws IOException {
        GildedRoseParameters parameters = new GildedRoseParameters();

        final JCommander jCommander = new JCommander(parameters);
        jCommander.setProgramName("gilded-rose-kata");
        jCommander.parse(args);

        if (parameters.isHelp()) {
            jCommander.usage();
            System.exit(0);
        }

        //Workaround since item doesn't have a default constructor, jackson can't handle it
        final List<Map<String, Object>> listMap = Constants
                .OBJECT_MAPPER
                .readValue(new FileInputStream(parameters.getFileName()), new TypeReference<List<Map<String, Object>>>() {
                });

        final Item[] items = listMap.stream()
                .map(m -> new Item(m.get("name").toString(), Integer.valueOf(m.get("sellIn").toString()), Integer.valueOf(m.get("quality").toString())))
                .toArray(Item[]::new);

        final GildedRose gildedRose = new GildedRose(items);

        for (int i = 0; i < parameters.getNrDays(); i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (final Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            gildedRose.updateQuality();
        }

    }
}
