package com.onrkrdmn.constant;

/**
 * All constant values related with puzzle
 *
 * @author Onur Karaduman
 * @since 09.04.17
 */
public interface PuzzleConst {
    public static int PUZZLE_PIECE_SIZE = 5;

    public static String getPuzzlePieces() {
        StringBuilder sb = new StringBuilder();
        sb.append("  o  \n");
        sb.append(" ooo \n");
        sb.append("ooooo\n");
        sb.append(" ooo \n");
        sb.append("  o  \n");
        sb.append("o o o\n");
        sb.append("ooooo\n");
        sb.append(" ooo \n");
        sb.append("ooooo\n");
        sb.append("o o o\n");
        sb.append("  o  \n");
        sb.append(" oooo\n");
        sb.append("oooo \n");
        sb.append(" oooo\n");
        sb.append("  o  \n");
        sb.append(" o o \n");
        sb.append("oooo \n");
        sb.append(" oooo\n");
        sb.append("oooo \n");
        sb.append("oo o \n");
        sb.append(" o o \n");
        sb.append("ooooo\n");
        sb.append(" ooo \n");
        sb.append("ooooo\n");
        sb.append("o o  \n");
        sb.append(" o o \n");
        sb.append(" oooo\n");
        sb.append("oooo \n");
        sb.append(" oooo\n");
        sb.append("oo oo\n");
        return sb.toString();
    }
}
