package com.onrkrdmn.model;

import com.onrkrdmn.constant.PuzzleConst;
import com.onrkrdmn.helper.ArrayHelper;

/**
 * The class represents edge of the puzzle piece
 *
 * @author Onur Karaduman
 * @since 09.04.17
 */
public class Edge {

    /**
     * Store the edge as int array
     * 1 means 'o'
     * 0 means ' '
     */
    private int[] units;

    public Edge() {
        this.units = new int[PuzzleConst.PUZZLE_PIECE_SIZE];
    }

    public Edge(int[] units) {
        this.units = units;
    }

    public Edge(String text) {
        int[] units = ArrayHelper.convertToEdgeArray(text);
        this.units = units;
    }

    public int[] getUnits() {
        return units;
    }

    /**
     * Check if the given edge is matched with current edge
     * XOR is used for matching
     *
     * @param edge
     * @return
     */
    public boolean isMatch(Edge edge) {
        for (int i = 0; i < this.units.length; i++) {
            if ((this.units[i] ^ edge.getUnits()[i]) == 0) {
                return false;
            }
        }

        // Set the edge with 1
        edge.fill();
        return true;
    }

    /**
     * Check if the given edge is matched with current edge
     * If matched, then fix the adjacent edges
     * (set the suitable units of adjacent edges with suitable values)
     *
     * @param edge
     * @param piece
     * @return
     */
    public boolean isMatch(Edge edge, Piece piece) {
        if (!this.isMatch(edge)) {
            return false;
        }
        piece.updateCorners(edge);
        return true;
    }

    /**
     * Check if the given edge is matched with current edge
     * This method doesnt look if last corners are mached or not
     *
     * @param edge
     * @param piece
     * @return
     */
    public boolean isMatchWithoutLastCorner(Edge edge, Piece piece) {
        for (int i = 0; i < this.units.length; i++) {
            if ((this.units[i] ^ edge.getUnits()[i]) == 0) {
                if (i == PuzzleConst.PUZZLE_PIECE_SIZE - 1) {
                    if (this.units[i] == 0) {
                        continue;
                    }
                }
                return false;
            }
        }
        edge.fillWithoutLastCorner();
        piece.updateCorners(edge);
        return true;
    }

    /**
     * Check if they are matched after reverse the edge
     * This is needed because of 3D
     *
     * @param edge
     * @return
     */
    public boolean isMatchReverse(Edge edge, Piece piece) {
        for (int i = 0; i < this.units.length; i++) {
            if ((this.units[i] ^ edge.getUnits()[edge.getUnits().length - 1 - i]) == 0) {
                return false;
            }
        }
        edge.fill();
        piece.updateCorners(edge);
        return true;
    }

    /**
     * Check if they are matched after reverse the edge
     * The method doesnt check the first corner of edge
     *
     * @param edge
     * @return
     */
    public boolean isMatchReverseWithoutFirstCorner(Edge edge, Piece piece) {
        for (int i = 0; i < this.units.length; i++) {
            if ((this.units[i] ^ edge.getUnits()[edge.getUnits().length - 1 - i]) == 0) {
                if (i == 0) {
                    if (this.units[i] == 0) {
                        continue;
                    }
                }
                return false;
            }
        }
        edge.fill();
        piece.updateCorners(edge);
        return true;
    }

    /**
     * Clone the current object
     *
     * @return
     */
    public Edge clone() {
        return new Edge(this.units.clone());
    }

    /**
     * Reverse the edge
     */
    public void reverse() {
        ArrayHelper.reverseArray(this.units);
    }

    /**
     * Fill the unit of edge according to given index
     *
     * @param index
     * @param value
     */
    public void fillByIndex(int index, int value) {
        if (value == 1) {
            this.units[index] = value;
        }
    }

    /**
     * Check if the edge has only value 1
     *
     * @return
     */
    public boolean isFilled() {
        for (int i : this.units) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * If two edge are matched, then set all edge with 1
     * in order to get correct result from next if conditions
     */
    public void fill() {
        for (int i = 0; i < this.units.length; i++) {
            this.units[i] = 1;
        }
    }

    /**
     * Fill the edge except for last corner
     *
     * @param index
     */
    public void fillWithoutLastCorner() {
        for (int i = 0; i < this.units.length; i++) {
            if (i == PuzzleConst.PUZZLE_PIECE_SIZE - 1) {
                continue;
            }
            this.units[i] = 1;
        }
    }

    /**
     * Fill the edge except for first corner
     *
     * @param index
     */
    public void fillWithoutFirstCorner() {
        for (int i = 1; i < this.units.length; i++) {
            this.units[i] = 1;
        }
    }
}
