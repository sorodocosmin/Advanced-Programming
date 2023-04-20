package org.example.homework;

public class Point {
    private int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
