package com.onrkrdmn.model;

/**
 * Surface is representing the side of cube
 *
 * @author Onur Karaduman
 * @since 09.04.17
 */
public class Surface {
    /**
     * Each surface has one piece
     */
    private Piece piece;

    public Surface(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * String representation of surface
     *
     * @return
     */
    @Override
    public String toString() {
        return this.piece.toString();
    }

    /**
     * Clone the surface
     *
     * @return
     */
    protected Surface clone() {
        Surface surfaceTmp = new Surface(this.piece.clone());
        return surfaceTmp;
    }
}
