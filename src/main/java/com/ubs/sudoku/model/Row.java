package com.ubs.sudoku.model;

public class Row {
    private int [] cells;

    public Row(int[] cells) {
        this.cells = cells;
    }

    public int [] getCells() {
     return cells;
    }

    public static Row of(int[] cells) {
        return new Row(cells);
    }
}
