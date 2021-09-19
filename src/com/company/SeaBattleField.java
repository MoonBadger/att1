package com.company;

import java.util.ArrayList;
import java.util.List;

public class SeaBattleField {
    public final int SIZE = 10, LARGEST_DECK = 4;
    private boolean[][] fieldsAttacked = new boolean[SIZE][SIZE],
                        fieldsShips = new boolean[SIZE][SIZE];
    private int[] decks = new int[LARGEST_DECK];

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

    public static class FieldIsAlreadyAttacked extends Exception {
        public FieldIsAlreadyAttacked() {}
    }

    public SeaBattleField() {
        defaultStartFunction();
    }

    //-----------------------

    public boolean fire(int x, int y) throws FieldIsAlreadyAttacked {
        if(fieldsAttacked[x][y]) throw new FieldIsAlreadyAttacked();
        fieldsAttacked[x][y] = true;
        destroyShipFunction(x, y);
        return fieldsShips[x][y];
    }

    public boolean isWin() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if(getFieldType(x, y) == fieldType.SHIP) return false;
            }
        }
        return true;
    }

    public boolean isCorrectPlacement() {
        for (int i = 0; i < LARGEST_DECK; i++) {
            if(decks[i] != LARGEST_DECK - i) return false;
        }
        return true;
    }

    public boolean setShip(int x, int y, int deck, boolean orientation) {
        if(deck > LARGEST_DECK) return false;
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
        if(decks[deck - 1] == (LARGEST_DECK - deck + 1)) return false;
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
        for(Point p : getShipPointsList(x, y)) {
            fieldsShips[p.x][p.y] = false;
        }
    }

    public void clear() {
        defaultStartFunction();
    }

    //----------

    private void destroyShipFunction(int x, int y) {
        ArrayList<Point> list = getShipPointsList(x, y),
                roundList = new ArrayList<>();
        for(Point p : list)  {
            if(getFieldType(p.x, p.y) == fieldType.SHIP) return;
            roundList.addAll(getRoundPoint(p.x, p.y));
        }
        for(Point p : roundList) {
            if(inBound(p.x, p.y)) {
                fieldsAttacked[p.x][p.y] = true;
            }
        }
    }

    private boolean isEmptyEnvironment(int x, int y) {
        for(Point point : getRoundPoint(x, y)) {
            if(fieldsShips[point.x][point.y]) return false;
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
                    Point point = new Point(x + i, y + j);
                    if(! listContainsPoint(list, point)) list.add(point);
                }
            }
        return list;
    }

    private void defaultStartFunction() {
        fillArray(fieldsAttacked, false);
        fillArray(fieldsShips, false);
        for (int i = 0; i < LARGEST_DECK; i++) {
            decks[i] = 0;
        }
    }
}
