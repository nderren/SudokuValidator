package com.ubs.sudoku;

import com.ubs.sudoku.model.Board;
import com.ubs.sudoku.model.Row;
import com.ubs.sudoku.process.SudokuValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ApplicationMain {

    public static final Logger log = LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            log.info("No Sudoku file was given, process completing: INVALID");
            return;
        }

        File file = new File(args[0]);

        if (!file.exists()) {
            log.info("Sudoku file was given but is not found, process completing: INVALID");
            return;
        }

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            List<Row> boardDataList = bufferedReader.lines()
                    .map(line -> line.split(","))
                    .map(stringData -> Arrays.stream(stringData)
                            .mapToInt(val -> Integer.parseInt(val.trim()))
                            .toArray()
                    )
                    .map(dataRow -> Row.of(dataRow))
                    .collect(toList());

            Board board = Board.of(boardDataList);

            SudokuValidator sudokuValidator = new SudokuValidator();

            boolean gameResult = sudokuValidator.validateGameBoard(board);

            log.info("Game result is {}", gameResult? "VALID":"INVALID");

        } catch (IOException e) {
            log.error("Game result is INVALID. Error caused calculating:", e);
        } catch (NumberFormatException e) {
            log.error("Game result is INVALID. Error, bad data in the file {}:", file, e);
        }
    }
}
