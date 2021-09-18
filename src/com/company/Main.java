package com.company;

public class Main {

    public static void main(String[] args) {
	    SeaBattleField field = new SeaBattleField();
	    field.setShip(2,0, 3, true);
	    field.setShip(3, 1, 4, false);
	    field.setShip(5, 7, 2, true);
	    field.setShip(9,9,1,false);
	    System.out.println(StringConverter.fieldToString(field));
    }
}
