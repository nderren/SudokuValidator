package com.ubs.sudoku.process;

import com.ubs.sudoku.model.Board;

import java.util.Arrays;
import java.util.List;

public class SudokuValidator {

    public boolean validateGameByView(List<int[]> dataView) {

        if(dataView == null || dataView.size() != 9) {
            return false;
        }

        long count = dataView.stream()
                .filter(row -> row.length != 9 )
                .count();

        if(count != 0) {
            return false;
        }

        count = dataView.stream()
                .map(row -> Arrays.stream(row).distinct().count())
                .filter(rowCount -> rowCount != 9 )
                .count();

        return count == 0;
    }

    public boolean validateGameBoard(Board board) {
        return validateGameByView(board.getDataAsColumn())
                && validateGameByView(board.getDataAsRow())
                && validateGameByView(board.getDataAsRegion());

    }

}
