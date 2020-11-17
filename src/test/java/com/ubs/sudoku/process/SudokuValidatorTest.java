package com.ubs.sudoku.process;

import com.ubs.sudoku.model.Board;
import com.ubs.sudoku.model.Row;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class SudokuValidatorTest {

    private SudokuValidator sudokuValidator;
    private List<int[]> dataList;


    @Before
    public void setupEnvironment() {

        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        dataList = asList(data, data, data, data, data, data, data, data, data);

        sudokuValidator = new SudokuValidator();
    }

    @Test
    public void gameViewWillBeValidatedTrueWhenCorrect() {

        boolean result = sudokuValidator.validateGameByView(dataList);

        assertTrue(result);
    }

    @Test
    public void invalidGameViewWillBeValidatedFalse() {

        boolean result = sudokuValidator.validateGameByView(null);

        assertFalse(result);
    }

    @Test
    public void gameViewWillBeValidatedFalseWhenNotCorrect() {

        dataList.get(0)[7] = 9;

        boolean result = sudokuValidator.validateGameByView(dataList);

        assertFalse(result);
    }

    @Test
    public void gameViewWillBeValidatedFalseWhenDataStructureIsNotCorrect() {

        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        dataList = asList(data, data, data, data, data, data, data, data, data);

        boolean result = sudokuValidator.validateGameByView(dataList);

        assertFalse(result);
    }

    @Test
    public void boardWillBeValidatedCorrectlyToTrue() {

        int[] data1 = {1, 7, 2, 5, 4, 9, 6, 8, 3};
        int[] data2 = {6, 4, 5, 8, 7, 3, 2, 1, 9};
        int[] data3 = {3, 8, 9, 2, 6, 1, 7, 4, 5};
        int[] data4 = {4, 9, 6, 3, 2, 7, 8, 5, 1};
        int[] data5 = {8, 1, 3, 4, 5, 6, 9, 7, 2};
        int[] data6 = {2, 5, 7, 1, 9, 8, 4, 3, 6};
        int[] data7 = {9, 6, 4, 7, 1, 5, 3, 2, 8};
        int[] data8 = {7, 3, 1, 6, 8, 2, 5, 9, 4};
        int[] data9 = {5, 2, 8, 9, 3, 4, 1, 6, 7};

        List<Row> rows = asList(Row.of(data1), Row.of(data2), Row.of(data3),
                Row.of(data4), Row.of(data5), Row.of(data6),
                Row.of(data7), Row.of(data8), Row.of(data9));

        boolean result = sudokuValidator.validateGameBoard(Board.of(rows));

        assertTrue(result);
    }

    @Test
    public void boardWillBeValidatedCorrectlyToFalse() {

        int[] data1 = {1, 7, 2, 5, 9, 9, 9, 8, 3};
        int[] data2 = {6, 4, 5, 8, 9, 9, 9, 1, 9};
        int[] data3 = {3, 8, 9, 2, 9, 9, 9, 4, 5};
        int[] data4 = {4, 9, 6, 3, 9, 9, 9, 5, 1};
        int[] data5 = {8, 1, 3, 4, 9, 9, 9, 7, 2};
        int[] data6 = {2, 5, 7, 1, 9, 9, 9, 3, 6};
        int[] data7 = {9, 6, 4, 7, 9, 9, 9, 2, 8};
        int[] data8 = {7, 3, 1, 6, 9, 9, 9, 9, 4};
        int[] data9 = {5, 2, 8, 9, 9, 9, 9, 6, 7};

        List<Row> rows = asList(Row.of(data1), Row.of(data2), Row.of(data3),
                Row.of(data4), Row.of(data5), Row.of(data6),
                Row.of(data7), Row.of(data8), Row.of(data9));

        boolean result = sudokuValidator.validateGameBoard(Board.of(rows));

        assertFalse(result);
    }
}