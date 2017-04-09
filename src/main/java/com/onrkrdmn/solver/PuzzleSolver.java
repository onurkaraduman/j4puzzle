package com.onrkrdmn.solver;

import com.onrkrdmn.model.Cube;
import com.onrkrdmn.model.Piece;
import com.onrkrdmn.model.Surface;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;

/**
 * The cube puzzle solver
 *
 * @author Onur Karaduman
 * @since 09.04.17
 */
@Log4j
public class PuzzleSolver implements Solver {

    /**
     * Cube solver method
     *
     * @param pieces
     * @return
     */
    public Cube solve(List<Piece> pieces) {
        log.info(String.format("%d pieces are available for solving", pieces.size()));
        Cube cube = new Cube();
        return solve(pieces, cube, pieces.get(0));
    }

    /**
     * Recursive method for solving cube
     *
     * @param pieces
     * @param cube
     * @param piece
     * @return
     */
    private Cube solve(List<Piece> pieces, Cube cube, Piece piece) {
        if (cube.isCompleted()) {
            return cube;
        }

        for (Piece pieceElement : pieces) {
            Cube solve = rotateAndSolve(pieces, cube, pieceElement);
            if (solve != null) {
                return solve;
            }

            // Turn the piece, since we can use two sides of puzzle piece
            pieceElement.turn();
            solve = rotateAndSolve(pieces, cube, pieceElement);
            if (solve != null) {
                return solve;
            }
        }
        return null;
    }

    /**
     * This method tries pieces with all rotated status
     *
     * @param pieces
     * @param cube
     * @param piece
     * @return
     */
    private Cube rotateAndSolve(List<Piece> pieces, Cube cube, Piece piece) {
        Piece pieceTmp = (Piece) piece.clone();
        for (int i = 0; i < 4; i++) {
            log.debug("This is the piece for next try: \n" + pieceTmp.toString());

            // Check if the current piece is matched in cube
            if (cube.check(pieceTmp)) {
                Cube clonedCube = cube.clone();
                Surface surface = new Surface(pieceTmp);
                clonedCube.addSurface(surface);
                List<Piece> piecesTmp = new ArrayList<>(pieces);
                piecesTmp.remove(pieceTmp);
                Cube solution = solve(piecesTmp, clonedCube, pieceTmp);
                if (solution != null) {
                    return solution;
                }
            }

            // If the solution couldnt be found. Then try to rotate the current piece
            pieceTmp.rotateClockwise();
        }
        log.debug("This is the last status of cube: \n" + cube.toString());
        return null;
    }
}
