package com.onrkrdmn.helper;

import com.onrkrdmn.reader.buffer.Buffer;
import com.onrkrdmn.reader.buffer.FileBuffer;

/**
 * @author Onur Karaduman
 * @since 09.04.17
 */
public class PuzzleTestHelper {

    public static Buffer createFullBuffer() throws InterruptedException {
        Buffer buffer = new FileBuffer();

        buffer.put("  o  ");
        buffer.put(" ooo ");
        buffer.put("ooooo");
        buffer.put(" ooo ");
        buffer.put("  o  ");
        buffer.put("o o o");
        buffer.put("ooooo");
        buffer.put(" ooo ");
        buffer.put("ooooo");
        buffer.put("o o o");
        buffer.put("  o  ");
        buffer.put(" oooo");
        buffer.put("oooo ");
        buffer.put(" oooo");
        buffer.put("  o  ");
        buffer.put(" o o ");
        buffer.put("oooo ");
        buffer.put(" oooo");
        buffer.put("oooo ");
        buffer.put("oo o ");
        buffer.put(" o o ");
        buffer.put("ooooo");
        buffer.put(" ooo ");
        buffer.put("ooooo");
        buffer.put("o o  ");
        buffer.put(" o o ");
        buffer.put(" oooo");
        buffer.put("oooo ");
        buffer.put(" oooo");
        buffer.put("oo oo");
        buffer.close();
        return buffer;
    }

    public static String solution() {
        StringBuilder sb = new StringBuilder();

        sb.append("     oo oo\n");
        sb.append("      oooo\n");
        sb.append("     oooo \n");
        sb.append("      oooo\n");
        sb.append("      o o \n");
        sb.append(" o o o o o  o  \n");
        sb.append(" ooo ooooo ooo \n");
        sb.append("ooooo ooo ooooo\n");
        sb.append(" ooo ooooo ooo \n");
        sb.append("  o  o o o  o  \n");
        sb.append("      o o \n");
        sb.append("     ooooo\n");
        sb.append("      ooo \n");
        sb.append("     ooooo\n");
        sb.append("       o o\n");
        sb.append("     oo o \n");
        sb.append("     ooooo\n");
        sb.append("      ooo \n");
        sb.append("     ooooo\n");
        sb.append("       o  \n");

        return sb.toString();

    }
}
