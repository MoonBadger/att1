package com.company;

import java.util.Scanner;

public class ConsoleGame {
    private Game game = new Game();
    private Scanner in = new Scanner(System.in);
    private boolean stageFire = false;
    private boolean hide = false;

    public void start() {
        while(! game.isCorrectPlacement()) {
            if(! hide) System.out.println("PLAYER 1");
            readCommand();
            if(! hide) System.out.println(StringConverter.fieldToString(game.player1));
        }
        if(!hide) {
            for (int i = 0; i < 30; i++) {
                System.out.println();
            }
        }
        game.next();
        while(! game.isCorrectPlacement()) {
            if(! hide)System.out.println("PLAYER 2");
            readCommand();
            if(! hide) System.out.println(StringConverter.fieldToString(game.player2));
        }
        stageFire = true;
        System.out.println("<< LET'S THE BATTLE BEGIN! >>");
        StringConverter.setHide(true);
        game.next();
        while(! game.isWin()) {
            System.out.println(StringConverter.fieldToString(game.getCurrentToAttack()));
            System.out.println(StringColor.ANSI_RESET + (game.isFirstPlayer() ? "PLAYER 1" : "PLAYER 2"));
            boolean b  = readCommand();
            if(!b) game.next();
        }
        String str = game.getCurrent() == game.player2 ? "PLAYER 2" : "PLAYER 1";
        System.out.println("<<< " + str + " WON!" + " >>>");
    }

    private boolean readCommand() {
        try {
            Scanner s = new Scanner(in.nextLine());
            String com = s.next();
            if (!stageFire && com.equals("set")) {
                game.getCurrent().setShip(
                        s.nextInt(),
                        s.nextInt(),
                        s.nextInt(),
                        s.nextBoolean());
            } else if (!stageFire && com.equals("del")) {
                game.deleteShip(s.nextInt(), s.nextInt());
            } else if (stageFire) {
                return game.fire(Integer.parseInt(com), s.nextInt());
            } else if(com.equals("hide")) {
                hide = true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }
}
