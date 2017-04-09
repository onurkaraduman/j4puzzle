package com.onrkrdmn.reader.buffer;

/**
 * Buffer mechanism for async operations
 *
 * @author Onur Karaduman
 * @since 08.04.17
 */
public interface Buffer {

    public void put(String text) throws InterruptedException;

    public String take() throws InterruptedException;

    public boolean isDone();

    public void close();

    public boolean dataAvailable();

}
