package com.gildedrose.utils;

import com.beust.jcommander.Parameter;

/**
 * GildedRoseParameters to be parsed by JCommander.
 *
 * @author Joao Pires
 */
public class GildedRoseParameters {

    /**
     * Parameter nrDays.
     * Default: 10
     */
    @Parameter(names = {"-n", "--nrDays"}, description = "Number of days")
    private int nrDays = 10;

    /**
     * Parameter fileName.
     * Default: test.json
     */
    @Parameter(names = {"-f", "--file"}, description = "Filename to configuration", validateWith = FileValidator.class)
    private String fileName = "test.json";

    /**
     * Help parameter.
     */
    @Parameter(names = {"-h", "--help"}, description = "Show the usage message", help = true)
    private boolean help = false;

    /**
     * Gets the number of days parameter.
     *
     * @return nrDays
     */
    public int getNrDays() {
        return nrDays;
    }

    /**
     * Gets the fileName parameter
     *
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets help parameter.
     *
     * @return true if asked for help, false otherwise
     */
    public boolean isHelp() {
        return help;
    }
}
