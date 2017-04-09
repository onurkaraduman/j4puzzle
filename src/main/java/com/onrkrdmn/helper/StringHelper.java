package com.onrkrdmn.helper;

public class StringHelper {

    public static String createStringWithSpace(String[] strArray, int spaceAmount) {
        StringBuffer buffer = new StringBuffer();
        for (String string : strArray) {
            for (int i = 0; i < spaceAmount; i++) {
                buffer.append(" ");
            }
            buffer.append(string);
            buffer.append("\n");
        }
        return buffer.toString();
    }

    /**
     * Create string puzzle piece pattern according to given edge represented as int array
     *
     * @param left      edge of piece
     * @param right     edge of piece
     * @param top       edge of piece
     * @param bottom    edge of piece
     * @param pieceSize piece size ()
     * @return
     */
    public static String createStringPattern(int[] left, int[] right, int[] top, int[] bottom, int pieceSize) {
        StringBuffer buffer = new StringBuffer();

        // Create first line
        for (int element : top) {
            if (element == 1) {
                buffer.append("o");
            } else {
                buffer.append(" ");
            }
        }
        buffer.append("\n");

        for (int i = 1; i < left.length - 1; i++) {
            if (left[i] == 1) {
                buffer.append("o");
            } else {
                buffer.append(" ");
            }
            for (int j = 0; j < pieceSize - 2; j++) {
                buffer.append("o");
            }
            if (right[i] == 1) {
                buffer.append("o");
            } else {
                buffer.append(" ");
            }
            buffer.append("\n");
        }

        // Create last line
        for (int element : bottom) {
            if (element == 1) {
                buffer.append("o");
            } else {
                buffer.append(" ");
            }
        }

        return buffer.toString();
    }
}
