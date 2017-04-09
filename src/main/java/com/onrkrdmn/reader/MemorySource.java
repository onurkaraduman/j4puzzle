package com.onrkrdmn.reader;

import java.io.*;

/**
 * Memory data source
 *
 * @author Onur Karaduman
 * @since 09.04.17
 */
public class MemorySource implements Source {


    private Reader reader;

    public MemorySource(String string) {
        this.reader = new StringReader(string);
    }


    @Override
    public Reader getReader() {

        return this.reader;
    }
}
