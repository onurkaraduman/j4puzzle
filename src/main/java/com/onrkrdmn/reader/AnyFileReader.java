package com.onrkrdmn.reader;

import com.onrkrdmn.reader.buffer.Buffer;
import lombok.extern.log4j.Log4j;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * This class reads physical file or memory data and buffers in {@link Buffer}
 *
 * @author Onur Karaduman
 * @since 08.04.17
 */
@Log4j
public class AnyFileReader implements CReader {

    /**
     * Buffer for buffering
     */
    private Buffer buffer;

    /**
     * File source.
     * It might be a physical file and memory data
     */
    private Source source;

    /**
     * File path to read
     */
    private String path;

    public AnyFileReader(Buffer buffer, Source source) {
        this.buffer = buffer;
        this.source = source;
    }

    /**
     * Read file with given path and buffer to {@link Buffer}
     *
     * @return
     * @throws IOException
     */
    private void read() throws IOException, InterruptedException {
        try (BufferedReader bufferedReader = new BufferedReader(this.source.getReader())) {
            String currentLine = null;
            while ((currentLine = bufferedReader.readLine()) != null) {
                log.debug("file content>>>>>" + currentLine);
                getBuffer().put(currentLine);
            }
            buffer.close();
        }

    }

    private Buffer getBuffer() {
        return this.buffer;
    }

    public void run() {
        try {
            this.read();
        } catch (IOException e) {
            log.error("Exception during the file reading!!", e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
