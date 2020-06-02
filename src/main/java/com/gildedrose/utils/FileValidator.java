package com.gildedrose.utils;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

/**
 * JCommander File validator, to validate if file is valid.
 *
 * @author Joao Pires
 */
public class FileValidator implements IParameterValidator {

    /**
     * Validates if provided file is valid.
     *
     * @param name  the property name
     * @param value the property value (file name)
     * @throws ParameterException in case of file doesn't exist or not being readable
     */
    @Override
    public void validate(final String name, final String value) throws ParameterException {
        final File f = new File(value);

        if (!f.exists() || !f.canRead()) {
            throw new ParameterException(String.format("File %s doesn't exist or is not readable", name));
        }
    }
}
