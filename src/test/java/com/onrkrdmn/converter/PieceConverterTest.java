package com.onrkrdmn.converter;

import com.onrkrdmn.model.Piece;
import com.onrkrdmn.reader.buffer.Buffer;
import com.onrkrdmn.reader.buffer.FileBuffer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by onur on 09.04.17.
 */
public class PieceConverterTest {

    @Test
    public void createPiece() throws Exception {

        Buffer buffer = new FileBuffer();

        buffer.put("  o  ");
        buffer.put(" ooo ");
        buffer.put("ooooo");
        buffer.put(" ooo ");
        buffer.put("  o  ");
        buffer.close();

        PieceConverter pieceConverter = new PieceConverter(buffer);
        List<Piece> call = (List<Piece>) pieceConverter.call();
        assertEquals(call.size(), 1);
    }

}