package com.ubs.sudoku.model;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    private Board board;
    private int[] cells = {1,2,3,4,5,6,7,8,9};

    @Before
    public void setupEnvironment() {

        List<Row> rows = asList(Row.of(cells), Row.of(cells), Row.of(cells));

        board = Board.of(rows);
    }

    @Test
    public void boardWillReturnRowData() {
        List<int[]> dataAsRow = board.getDataAsRow();

        assertTrue(dataAsRow.size() == 3);

        dataAsRow.forEach(row -> {
            assertTrue(Arrays.equals(row, cells));
        });
    }

    @Test
    public void boardWillReturnColumnData() {
        List<int[]> dataAsColumn = board.getDataAsColumn();

        assertTrue(dataAsColumn.size() == 9);

        for (int i = 0; i < dataAsColumn.size(); i++) {
            final int expectedValue = i+1;
            assertTrue(Arrays.stream(dataAsColumn.get(i)).allMatch(val -> val == expectedValue));
        }
    }

    @Test
    public void boardWillReturnRegionData() {

        int [] expectedRegion1 = {1,2,3,1,2,3,1,2,3};
        int [] expectedRegion2 = {4,5,6,4,5,6,4,5,6};
        int [] expectedRegion3 = {7,8,9,7,8,9,7,8,9};

        List<int[]> dataAsColumn = board.getDataAsRegion();

        assertTrue(dataAsColumn.size() == 3);
        assertTrue(Arrays.equals(dataAsColumn.get(0), expectedRegion1));
        assertTrue(Arrays.equals(dataAsColumn.get(1), expectedRegion2));
        assertTrue(Arrays.equals(dataAsColumn.get(2), expectedRegion3));
    }
}