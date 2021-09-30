package com.company;

public class Game {
    public SeaBattleField player1, player2;
    private boolean current = true;

    public Game() {
        reset();
    }

    public SeaBattleField getCurrent() {
        return current ? player1 : player2;
    }

    public SeaBattleField getCurrentToAttack() {
        return current ? player2 : player1;
    }

    public boolean fire(int x, int y) throws SeaBattleField.FieldIsAlreadyAttacked {
        return getCurrentToAttack().fire(x, y);
    }

    public boolean isWin() {
        return getCurrentToAttack().isWin();
    }

    public boolean isCorrectPlacement() {
        return getCurrent().isCorrectPlacement();
    }

    public boolean setShip(int x, int y, int deck, boolean orientation) {
        return getCurrent().setShip(x, y, deck, orientation);
    }

    public SeaBattleField.FieldType getFieldType(int x, int y) {
        return getCurrent().getFieldType(x, y);
    }

    public void deleteShip(int x, int y) {
        getCurrent().deleteShip(x, y);
    }

    public void clear() {
        getCurrent().clear();
    }

    public int getDeckNumber(int deck) {
        return getCurrent().getDeckNumber(deck);
    }

    public SeaBattleField next() {
        current = !current;
        return getCurrent();
    }

    public void reset() {
        player1 = new SeaBattleField();
        player2 = new SeaBattleField();
    }

    public boolean isFirstPlayer() {
        return current;
    }
}
