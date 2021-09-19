package com.company;

public class Main {

    public static void main(String[] args) {
	    SeaBattleField field = new SeaBattleField();
	    field.setShip(3,4,4,true);
	    System.out.println(StringConverter.fieldToString(field));
    }
}
