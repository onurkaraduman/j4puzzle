package com.onrkrdmn.model;

import com.onrkrdmn.constant.PuzzleConst;
import com.onrkrdmn.helper.StringHelper;

/**
 * @author Onur Karaduman
 * @since 09.04.17
 */
public class Cube {
    /**
     * current surface type {@link SurfaceType}
     */
    private SurfaceType surfaceType;

    /**
     * Left surface of cube
     */
    private Surface left;

    /**
     * Top surface of cube
     */
    private Surface top;

    /**
     * Right surface of cube
     */
    private Surface right;

    /**
     * Front surface of cube
     */
    private Surface front;

    /**
     * Bottom surface of cube
     */
    private Surface bottom;

    /**
     * Back surface of cube
     */
    private Surface back;

    public Cube() {
        this.surfaceType = SurfaceType.LEFT;
    }

    public SurfaceType getSurfaceType() {
        return surfaceType;
    }

    public void setSurfaceType(SurfaceType surfaceType) {
        this.surfaceType = surfaceType;
    }

    public Surface getLeft() {
        return left;
    }

    public void setLeft(Surface left) {
        this.left = left;
    }

    public Surface getTop() {
        return top;
    }

    public void setTop(Surface top) {
        this.top = top;
    }

    public Surface getRight() {
        return right;
    }

    public void setRight(Surface right) {
        this.right = right;
    }

    public Surface getFront() {
        return front;
    }

    public void setFront(Surface front) {
        this.front = front;
    }

    public Surface getBottom() {
        return bottom;
    }

    public void setBottom(Surface bottom) {
        this.bottom = bottom;
    }

    public Surface getBack() {
        return back;
    }

    public void setBack(Surface back) {
        this.back = back;
    }

    /**
     * Clone the cube
     */
    public Cube clone() {
        Cube cubeTmp = new Cube();
        if (this.back != null)
            cubeTmp.setBack(this.back.clone());
        if (this.right != null)
            cubeTmp.setRight(this.right.clone());
        if (this.top != null)
            cubeTmp.setTop(this.top.clone());
        if (this.front != null)
            cubeTmp.setFront(this.front.clone());
        if (this.left != null)
            cubeTmp.setLeft(this.left.clone());
        if (this.bottom != null)
            cubeTmp.setBottom(this.bottom.clone());
        cubeTmp.setSurfaceType(this.surfaceType);
        return cubeTmp;
    }

    /**
     * Since the program finds the solution with that order
     * Left-Top-Right-Front-Bottom-Back the last surface of cube is back
     * <p>
     * If the back of the cube is not null, that means the cube is completed
     *
     * @return
     */
    public boolean isCompleted() {
        return this.back != null;
    }

    /**
     * Check if the puzzle piece is suitable for the next surface
     *
     * @param piece
     * @return
     */
    public boolean check(final Piece piece) {

        boolean retValue = false;

        // Clone the piece in order to do some changes for check
        Piece pieceCopied = piece.clone();

        switch (this.surfaceType) {
            // For first surface no need to check
            case LEFT:
                retValue = true;
                break;

            // Only check with right edge of left surface
            case TOP:
                retValue = this.left.getPiece().getRight().isMatch(pieceCopied.getLeft(), pieceCopied);
                break;

            // Check with right edge of top surface
            case RIGHT:
                retValue = this.top.getPiece().getRight().isMatch(pieceCopied.getLeft(), pieceCopied);
                break;

            // Check with bottom edge of the top surface
            // Check with bottom edge of left surface (you need to reverse edge for
            // it because of 3D)
            // Check with bottom edge of right surface (dont check the last corner)
            case FRONT:
                retValue = this.top.getPiece().getBottom().isMatch(pieceCopied.getTop(), pieceCopied)
                        && this.left.getPiece().getBottom().isMatchReverseWithoutFirstCorner(pieceCopied.getLeft(),
                        pieceCopied)
                        && this.right.getPiece().getBottom().isMatchWithoutLastCorner(pieceCopied.getRight(), pieceCopied);
                break;

            // Check with bottom edge of front surface
            // Check with left edge of left surface (reverse edge before check and
            // dont check the first corner)
            // Check with right edge of right surface (reverse edge before check and
            // dont check the first corner)
            case BOTTOM:
                retValue = this.front.getPiece().getBottom().isMatch(pieceCopied.getTop(), pieceCopied)
                        && this.left.getPiece().getLeft().isMatchReverseWithoutFirstCorner(pieceCopied.getLeft(), pieceCopied)
                        && this.right.getPiece().getRight().isMatchReverseWithoutFirstCorner(pieceCopied.getRight(),
                        pieceCopied);
                break;

            // Check top edge of top surface
            // Check top edge of left surface
            // Check top edge of right surface (reverse the edge before check)
            // Check bottom edge of bottom surface
            case BACK:
                retValue = this.top.getPiece().getTop().isMatch(pieceCopied.getBottom(), pieceCopied)
                        && this.left.getPiece().getTop().isMatch(pieceCopied.getLeft(), pieceCopied)
                        && this.right.getPiece().getTop().isMatchReverse(pieceCopied.getRight(), pieceCopied)
                        && this.bottom.getPiece().getBottom().isMatch(pieceCopied.getTop(), pieceCopied);
                break;
        }

        return retValue;
    }

    /**
     * Add surface according to current side The program goes that flow to find
     * solution Left-Top-Right-Front-Bottom-Back
     *
     * @param surface
     */
    public void addSurface(Surface surface) {
        switch (this.surfaceType) {

            case LEFT:
                this.left = surface;
                break;
            case TOP:
                this.top = surface;
                break;
            case RIGHT:
                this.right = surface;
                break;
            case FRONT:
                this.front = surface;
                break;
            case BOTTOM:
                this.bottom = surface;
                break;
            case BACK:
                this.back = surface;
                break;
        }
        this.surfaceType = SurfaceType.getNext(this.surfaceType);
    }

    /**
     * String representation of cube example structure: It shows how the puzzle
     * pieces integrated as 2D 5 012 3 4
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        if (back != null) {
            String[] backSplitted = back.toString().split("\n");
            buffer.append(StringHelper.createStringWithSpace(backSplitted, PuzzleConst.PUZZLE_PIECE_SIZE));
        }

        String[] leftSplitted = null;
        String[] topSplitted = null;
        String[] rightSplitted = null;
        if (left != null) {
            leftSplitted = left.toString().split("\n");
        }
        if (right != null) {
            rightSplitted = right.toString().split("\n");
        }
        if (top != null) {
            topSplitted = top.toString().split("\n");
        }

        for (int i = 0; i < PuzzleConst.PUZZLE_PIECE_SIZE; i++) {
            if (left != null)
                buffer.append(leftSplitted[i]);
            if (top != null)
                buffer.append(topSplitted[i]);
            if (right != null)
                buffer.append(rightSplitted[i]);
            buffer.append("\n");
        }

        if (front != null) {
            String[] frontSplitted = front.toString().split("\n");
            buffer.append(StringHelper.createStringWithSpace(frontSplitted, PuzzleConst.PUZZLE_PIECE_SIZE));
        }

        if (bottom != null) {
            String[] bottomSplitted = bottom.toString().split("\n");
            buffer.append(StringHelper.createStringWithSpace(bottomSplitted, PuzzleConst.PUZZLE_PIECE_SIZE));
        }

        return buffer.toString();
    }
}
