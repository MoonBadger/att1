package com.company;

public class SeaBattleField {
    private final int SIZE = 10;
    private boolean[][] fieldsAttacked = new boolean[SIZE][SIZE],
                        fieldsShips = new boolean[SIZE][SIZE];
    private int[] decks = new int[4];

    public enum fieldType {
        VOID, ATTACKED_VOID, SHIP, ATTACKED_SHIP
    }

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
        return true;
    }

    public fieldType getFieldType(int x, int y) {
        return null;
    }

    private boolean isDestroyedShip(int x, int y) {
        return true;
    }

    private void destroyShipFunction(int x, int y) {

    }

    private void deleteShip(int x, int y) {

    }

    private void clear() {

    }
}
