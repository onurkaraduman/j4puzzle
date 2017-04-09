package com.onrkrdmn.reader.buffer;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Thread safe File Buffer in order to process async operations
 * This buffer is designed for producer-consumer relationship
 *
 * @author Onur Karaduman
 * @since 09.04.17
 */
@Log4j
public class FileBuffer implements Buffer {

    private int QUEU_SIZE = 1024;

    /**
     * Store lines of file
     */
    private ArrayBlockingQueue<String> lines;

    /**
     * This flag is using to check if writing buffer is done
     */
    private boolean done;

    public FileBuffer() {
        this.lines = new ArrayBlockingQueue<String>(this.QUEU_SIZE);
        this.done = false;
    }

    /**
     * Put value to queue as sync
     *
     * @param line
     * @throws InterruptedException
     */
    public synchronized void put(String line) throws InterruptedException {
        if (lines.size() == this.QUEU_SIZE) {
            wait();
        }
        lines.put(line);
        notifyAll();
        log.info("New line has been produced::" + line);
    }

    /**
     * Take value from queue as sync
     *
     * @return
     * @throws InterruptedException
     */
    public synchronized String take() throws InterruptedException {
        try {
            if (lines.size() == 0) {
                wait();
            }
            return this.lines.take();
        } catch (InterruptedException e) {
            log.error("Error during consuming buffer!!", e);
            throw e;
        } finally {
            log.info("New line has been consumed");
            notifyAll();
        }
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    public void close() {
        this.done = true;
    }

    @Override
    public boolean dataAvailable() {
        return this.lines.size() > 0;
    }
}
