package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    SeaBattleField field = new SeaBattleField();
	    field.setShip(3,4,4,false);
	    System.out.println(StringConverter.fieldToString(field));
    }
}
