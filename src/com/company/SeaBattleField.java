package com.company;

import java.util.ArrayList;
import java.util.List;

public class SeaBattleField {
    public final int SIZE = 10;
    private boolean[][] fieldsAttacked = new boolean[SIZE][SIZE],
                        fieldsShips = new boolean[SIZE][SIZE];
    private int[] decks = new int[4];

    private static class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if(! (obj instanceof Point)) return false;
            Point point = (Point) obj;
            return (this.x == point.x) && (this.y == point.y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public enum fieldType {
        VOID, ATTACKED_VOID, SHIP, ATTACKED_SHIP
    }

    public SeaBattleField() {
        fillArray(fieldsAttacked, false);
        fillArray(fieldsShips, false);
        for (int i = 0; i < 4; i++) {
            decks[i] = 0;
        }
    }

    //-----------------------

    public boolean fire(int x, int y) {
        return true;
    }

    public boolean isWin() {
        return true;
    }

    public boolean isCorrectPlacement() {
        return true;
    }

    public boolean setShip(int x, int y, int deck, boolean orientation) {
        int dx = orientation ? deck : 0;
        int dy = orientation ? 0 : deck;
        if(orientation) {
            if(! inBound(x + dx - 1, y)) return false;
            for (int i = 0; i < dx; i++) {
                if(! isEmptyEnvironment(x + i, y)) return false;
            }
        } else {
            if(! inBound(x, y + dy - 1)) return false;
            for (int i = 0; i < dy; i++) {
                if(! isEmptyEnvironment(x, y + i)) return false;
            }
        }
        if(decks[deck - 1] == (5 - deck)) return false;
        if(orientation) {
            for (int i = 0; i < dx; i++) {
                fieldsShips[x + i][y] = true;
            }
        } else {
            for (int i = 0; i < dy; i++) {
                fieldsShips[x][y + i] = true;
            }
        }
        decks[deck - 1]++;
        return true;
    }

    public fieldType getFieldType(int x, int y) {
        if(fieldsShips[x][y] && ! fieldsAttacked[x][y]) return fieldType.SHIP;
        if(fieldsShips[x][y] && fieldsAttacked[x][y]) return fieldType.ATTACKED_SHIP;
        if(! fieldsShips[x][y] && ! fieldsAttacked[x][y]) return fieldType.VOID;
        if(! fieldsShips[x][y] && fieldsAttacked[x][y]) return fieldType.ATTACKED_VOID;
        return null;
    }

    public void deleteShip(int x, int y) {

    }

    public void clear() {

    }

    //----------

    private void destroyShipFunction(int x, int y) {

    }

    private boolean isEmptyEnvironment(int x, int y) {
        int[] arr = new int[]{1, -1, 0};
        for(int i : arr)
            for(int j : arr) {
                if(i == 0 && j == 0) continue;
                if(inBound(x + i,y + j)) {
                    if (fieldsShips[x + i][y + j]) {
                        return false;
                    }
                }
            }
        return true;
    }

    private boolean inBound(int x, int y) {
        boolean bx = x < SIZE && x >= 0;
        boolean by = y < SIZE && y >= 0;
        return bx && by;
    }

    private void getShipPointsList(int x, int y, ArrayList<Point> list) {
        if(! inBound(x, y)) return;
        if(fieldsShips[x][y] && ! listContainsPoint(list, new Point(x, y))) {
            list.add(new Point(x, y));
            getShipPointsList(x + 1, y, list);
            getShipPointsList(x - 1, y, list);
            getShipPointsList(x, y + 1, list);
            getShipPointsList(x, y - 1, list);
        }
    }

    private ArrayList<Point> getShipPointsList(int x, int y) {
        ArrayList<Point> list = new ArrayList<>();
        getShipPointsList(x, y, list);
        return list;
    }

    private void fillArray(boolean[][] arr, boolean val) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = val;
            }
        }
    }

    private boolean listContainsPoint(List<Point> list, Point point) {
        for (Point p : list) {
            if(p.equals(point)) return true;
        }
        return false;
    }

    private ArrayList<Point> getRoundPoint(int x, int y) {
        ArrayList<Point> list = new ArrayList<>();
        int[] arr = new int[]{1, -1, 0};
        for(int i : arr)
            for(int j : arr) {
                if(i == 0 && j == 0) continue;
                if(inBound(x + i,y + j)) {
                    Point point = new Point(x, y);
                    if(! listContainsPoint(list, point)) list.add(point);
                }
            }
        return list;
    }
}
