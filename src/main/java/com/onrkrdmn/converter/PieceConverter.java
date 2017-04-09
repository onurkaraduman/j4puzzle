package com.onrkrdmn.converter;

import com.onrkrdmn.constant.PuzzleConst;
import com.onrkrdmn.model.Piece;
import com.onrkrdmn.reader.buffer.Buffer;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class is charge of providing
 * converting operation from text to pieces
 *
 * @author Onur Karaduman
 * @since 08.04.17
 */
@Log4j
public class PieceConverter implements Converter {

    /**
     * File buffer
     * The lines of file are stored in that buffer
     */
    private Buffer buffer;

    public PieceConverter(Buffer buffer) {
        this.buffer = buffer;
    }

    /**
     * Read from buffer and
     * convert text of the file to puzzle pieces
     *
     * @return
     * @throws IOException
     */
    private List<Piece> convert() throws IOException, InterruptedException {
        List<Piece> pieces = new ArrayList<>();
        int lineIndex = 0;
        int pieceCounter = 0;
        String line = null;
        StringBuffer sb = new StringBuffer();
        while (buffer.dataAvailable() || !buffer.isDone()) {
            line = this.buffer.take();
            sb.append(line);
            sb.append(System.getProperty("line.separator"));

            if (lineIndex++ == PuzzleConst.PUZZLE_PIECE_SIZE - 1) {
                Piece convert = createPiece(sb.toString(), pieceCounter);
                pieces.add(convert);
                lineIndex = 0;
                sb = new StringBuffer();
                pieceCounter++;
                log.info("Puzzle piece created. ID: " + pieceCounter);
            }
        }
        return pieces;
    }

    /**
     * Create piece from puzzle string pattern
     *
     * @param text       puzzle piece string pattern
     * @param pieceCount piece id
     * @return
     */
    private Piece createPiece(String text, int pieceCount) {
        return Piece.createPiece(text, pieceCount);
    }

    /**
     * {@link java.util.concurrent.Callable}
     *
     * @return
     * @throws Exception
     */
    public Object call() throws Exception {
        return convert();
    }
}
