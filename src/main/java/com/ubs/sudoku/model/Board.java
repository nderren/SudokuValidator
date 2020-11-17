package com.ubs.sudoku.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Board {
    private List<Row> rows;

    private Board(List<Row> rows) {
        this.rows = rows;
    }

    public List<int []> getDataAsRow() {
        return rows.stream()
                .map(row -> row.getCells())
                .collect(toList());
    }

    public List<int[]> getDataAsColumn() {

        SortedMap<Integer, List<Integer>> columns = new TreeMap();

        for (int i = 0; i < rows.size(); i++) {

            int[] cells = rows.get(i).getCells();

            for(int j = 0; j < cells.length; j++) {

                List<Integer> column = columns.get(j);

                if(column == null) {
                    column = new ArrayList();
                    columns.put(j, column);
                }

                column.add(cells[j]);

            }

        }

        return columns.values().stream()
                .map(column -> column.stream().mapToInt(value -> value.intValue()).toArray())
                .collect(toList());
    }

    public static Board of(List<Row> rows) {
        return new Board(rows);
    }

    public List<int[]> getDataAsRegion() {

        List<List<Integer>> regions = new ArrayList<>();

        List<Integer> region1 = null;
        List<Integer> region2 = null;
        List<Integer> region3 = null;

        for (int i = 0;i < rows.size(); i++) {

            if(i % 3 == 0) {
                region1 = new ArrayList<>();
                region2 = new ArrayList<>();
                region3 = new ArrayList<>();

                regions.add(region1);
                regions.add(region2);
                regions.add(region3);
            }

            int[] cells = rows.get(i).getCells();

            for (int j = 0; j < cells.length; j++) {
                if(j >= 0 && j <= 2) {
                    region1.add(cells[j]);
                } else if(j >= 3 && j <= 5) {
                    region2.add(cells[j]);
                } else if(j >= 6 && j <= 8) {
                    region3.add(cells[j]);
                }
            }
        }

        return regions.stream()
                .map(list -> list.stream().mapToInt(value -> value.intValue()).toArray())
                .collect(Collectors.toList());
    }
}
