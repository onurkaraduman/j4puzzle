package com.onrkrdmn.reader;

import com.onrkrdmn.constant.PuzzleConst;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Onur Karaduman
 * @since 09.04.17
 */
public class SourceFactory {

    public static SourceFactory sourceFactory;

    public static SourceFactory getInstance() {
        if (sourceFactory == null) {
            sourceFactory = new SourceFactory();
        }
        return sourceFactory;
    }

    public Source getSource(String type, String param) throws IOException {
        if (type.equals("Int")) {
            return new MemorySource(PuzzleConst.getPuzzlePieces());
        } else if (type.equals("Ext")) {
            if (param == null || param.isEmpty()) {
                throw new IllegalArgumentException("File path couldnt be found.");
            }
            return new FileSource(param);
        }
        throw new IllegalArgumentException("Sourcetype is not known." + type);
    }
}
