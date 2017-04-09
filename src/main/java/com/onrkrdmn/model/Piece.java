package com.onrkrdmn.model;

import com.onrkrdmn.constant.PuzzleConst;
import com.onrkrdmn.helper.StringHelper;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Onur Karaduman
 * @since 08.04.17
 */
public class Piece {
    private int id;

    private Edge right;
    private Edge left;
    private Edge top;
    private Edge bottom;

    public Piece() {
    }

    public Piece(Edge right, Edge left, Edge top, Edge bottom, int id) {
        this.right = right;
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Edge getRight() {
        return right;
    }

    public void setRight(Edge right) {
        this.right = right;
    }

    public Edge getLeft() {
        return left;
    }

    public void setLeft(Edge left) {
        this.left = left;
    }

    public Edge getTop() {
        return top;
    }

    public void setTop(Edge top) {
        this.top = top;
    }

    public Edge getBottom() {
        return bottom;
    }

    public void setBottom(Edge bottom) {
        this.bottom = bottom;
    }

    /**
     * Create Piece with given piece string pattern
     *
     * @param text
     * @param id
     * @return
     */
    public static Piece createPiece(String text, int id) {
        String[] split = text.split("\n");

        // Create topEdge
        Edge topEdge = new Edge(split[0]);

        // Create bottom Edge
        Edge bottomEdge = new Edge(split[4]);

        int[] leftEdgeUnits = new int[5];
        int[] rightEdgeUnits = new int[5];
        for (int i = 0; i < split.length; i++) {
            char[] charArray = split[i].toCharArray();

            if (charArray[0] == 'o') {
                leftEdgeUnits[i] = 1;
            } else {
                leftEdgeUnits[i] = 0;
            }

            if (charArray[4] == 'o') {
                rightEdgeUnits[i] = 1;
            } else {
                rightEdgeUnits[i] = 0;
            }
        }

        Edge leftEdge = new Edge(leftEdgeUnits);
        Edge rightEdge = new Edge(rightEdgeUnits);

        Piece piece = new Piece(rightEdge, leftEdge, topEdge, bottomEdge, id);
        return piece;

    }

    /**
     * Rotate the piece to clockwise
     */
    public void rotateClockwise() {
        Edge topTmp = this.top.clone();
        this.top = this.left.clone();
        this.top.reverse();

        Edge rightTmp = this.right.clone();
        this.right = topTmp;
        this.right.reverse();

        Edge bottomTmp = this.bottom.clone();
        this.bottom = rightTmp;

        this.left = bottomTmp;
    }

    /**
     * Rotate the puzzle piece to counter clockwise
     */
    public void rotateCounterClockwise() {
        Edge topTmp = this.top;
        this.top = this.right;

        Edge leftTmp = this.left;
        this.left = topTmp;

        Edge bottomTmp = this.bottom;
        this.bottom = leftTmp;

        this.right = bottomTmp;

    }

    /**
     * Turn the puzzle piece (Note: This is not rotating)
     */
    public void turn() {
        Edge topTmp = this.top;
        this.top = this.bottom;

        this.bottom = topTmp;

        this.left.reverse();
        this.right.reverse();
    }

    @Override
    public int hashCode() {
        return this.id * 34;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getId() == ((Piece) obj).getId();
    }

    /**
     * Clone the piece
     *
     * @return
     */
    public Piece clone() {
        Piece p = new Piece();
        p.setBottom(this.getBottom().clone());
        p.setTop((this.getTop().clone()));
        p.setRight(this.getRight().clone());
        p.setLeft(this.getLeft().clone());
        p.setId(this.getId());
        return p;
    }

    /**
     * Convert to string puzzle piece pattern
     *
     * @return
     */
    @Override
    public String toString() {
        return StringHelper.createStringPattern(this.left.getUnits(),
                this.right.getUnits(),
                this.top.getUnits(),
                this.bottom.getUnits(),
                PuzzleConst.PUZZLE_PIECE_SIZE);
    }

    /**
     * Update the corners according to adjacent edge
     *
     * @param edge
     */
    public void updateCorners(Edge edge) {

        if (edge == this.top) {
            this.left.fillByIndex(0, edge.getUnits()[0]);
            this.right.fillByIndex(0, edge.getUnits()[0]);
        } else if (edge == this.left) {
            this.top.fillByIndex(0, edge.getUnits()[0]);
            this.bottom.fillByIndex(0, edge.getUnits()[0]);

        } else if (edge == this.right) {
            this.top.fillByIndex(4, edge.getUnits()[0]);
            this.bottom.fillByIndex(4, edge.getUnits()[0]);
        } else if (edge == this.bottom) {
            this.left.fillByIndex(4, edge.getUnits()[0]);
            this.right.fillByIndex(4, edge.getUnits()[0]);
        }
    }
}
