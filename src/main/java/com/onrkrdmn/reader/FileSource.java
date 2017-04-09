package com.onrkrdmn.reader;

import java.io.*;
import java.io.FileReader;
import java.io.Reader;

/**
 * Physical file source
 *
 * @author Onur Karaduman
 * @since 09.04.17
 */
public class FileSource implements Source {

    private Reader reader;

    public FileSource(String path) throws IOException {
        this.reader = new FileReader(path);
    }

    @Override
    public Reader getReader() {
        return this.reader;
    }
}
