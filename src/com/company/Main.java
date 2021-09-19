package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
	    SeaBattleField field = new SeaBattleField();
		field.setShip(2, 1, 4, true);
		field.setShip(3, 5,3, false);
		field.deleteShip(2,1);
		field.setShip(0, 0, 4, true);
		System.out.println(StringConverter.fieldToString(field));
    }
}
