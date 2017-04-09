package com.onrkrdmn.solver;

import com.onrkrdmn.model.Cube;
import com.onrkrdmn.model.Piece;

import java.util.List;

/**
 * Puzzle solver service with given puzzle pieces
 *
 * @author Onur Karaduman
 * @since 09.04.17
 */
public interface Solver {

    public Cube solve(List<Piece> pieces);
}
