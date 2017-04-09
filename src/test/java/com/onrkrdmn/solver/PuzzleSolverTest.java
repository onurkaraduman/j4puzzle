package com.onrkrdmn.solver;

import com.onrkrdmn.converter.PieceConverter;
import com.onrkrdmn.helper.PuzzleTestHelper;
import com.onrkrdmn.model.Cube;
import com.onrkrdmn.model.Piece;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by onur on 09.04.17.
 */
public class PuzzleSolverTest {
    @Test
    public void solve() throws Exception {
        PieceConverter pieceConverter = new PieceConverter(PuzzleTestHelper.createFullBuffer());
        List<Piece> pieces = (List<Piece>) pieceConverter.call();

        Solver solver = new PuzzleSolver();
        Cube solve = solver.solve(pieces);
        assertEquals(solve.toString(), PuzzleTestHelper.solution());
    }

}