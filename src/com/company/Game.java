package com.company;

public class Game {
    SeaBattleField player1, player2, current;

    public Game() {
        player1 = new SeaBattleField();
        player2 = new SeaBattleField();
        current = player1;
    }

    public SeaBattleField getCurrent() {
        return current;
    }

    public void next() {
        current = current == player1 ? player2 : player1;
    }
}
