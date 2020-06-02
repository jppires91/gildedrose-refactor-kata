package com.gildedrose.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Constants, to define the quality min, quality max and quality max for Sulfuras item type.
 *
 * @author Joao Pires
 */
public final class Constants {

    /**
     * Private constructor of utility class.
     */
    private Constants() {
        //do nothing
    }

    /**
     * Quality max.
     */
    public static final int QUALITY_MAX = 50;

    /**
     * Quality min.
     */
    public static final int QUALITY_MIN = 0;

    /**
     * Sulfuras max.
     */
    public static final int SULFURAS_MAX = 80;

    /**
     * Object Mapper to translate json objects.
     */
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}
