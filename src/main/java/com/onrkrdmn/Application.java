package com.onrkrdmn;

import com.onrkrdmn.converter.Converter;
import com.onrkrdmn.converter.PieceConverter;
import com.onrkrdmn.model.Cube;
import com.onrkrdmn.model.Piece;
import com.onrkrdmn.reader.AnyFileReader;
import com.onrkrdmn.reader.CReader;
import com.onrkrdmn.reader.Source;
import com.onrkrdmn.reader.SourceFactory;
import com.onrkrdmn.reader.buffer.Buffer;
import com.onrkrdmn.reader.buffer.FileBuffer;
import com.onrkrdmn.solver.PuzzleSolver;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Application main class
 *
 * @author Onur Karaduman
 * @since 09.04.17
 */
@Log4j
public class Application {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        System.out.println("********************************************");
        System.out.println("*          cube puzzle solver              *");
        System.out.println("*      developed by Onur Karaduman         *");
        System.out.println("********************************************");

        // Getting sourceUsage and path option from program arguments
        String sourceUsage = null;
        String path = null;
        if (args != null && args.length > 0) {
            sourceUsage = args[0];
            log.info("source>>>>>>>>>>> " + sourceUsage);
        }
        if (args.length > 1) {

            path = args[1];
            log.info("path>>>>>>>>>>> " + path);
        }
        Source source = SourceFactory.getInstance().getSource(sourceUsage, path);

        Buffer buffer = new FileBuffer();

        CReader CReader = new AnyFileReader(buffer, source);

        Converter converter = new PieceConverter(buffer);

        Thread threadReader = new Thread(CReader);


        ExecutorService service = Executors.newSingleThreadExecutor();
        Future submit = service.submit(converter);

        threadReader.start();
        List<Piece> pieces = (List<Piece>) submit.get();


        PuzzleSolver solver = new PuzzleSolver();
        Cube solution = solver.solve(pieces);
        if (solution == null) {
            log.error("!!!!Any solution couldn't be found, please make sure that you give correct pieces");
        } else {
            System.out.println(">>>>>>>>>>Solution found<<<<<<<<<<<<<");
            System.out.println(solution.toString());
        }
        if (!service.isShutdown()) {
            service.shutdown();
        }
    }
}
