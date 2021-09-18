package com.company;

public class StringConverter {
    public static String fieldToString(SeaBattleField field) {
        String res = "";
        for (int i = 0; i < field.SIZE; i++) {
            for (int j = 0; j < field.SIZE; j++) {
                res += fieldTypeToChar(field.getFieldType(j, i)) + " ";
            }
            res += "\n";
        }
        return res;
    }

    private static char fieldTypeToChar(SeaBattleField.fieldType fieldType) {
        return switch (fieldType) {
            case VOID -> '.';
            case SHIP -> '#';
            case ATTACKED_SHIP -> 'X';
            case ATTACKED_VOID -> '*';
        };
    }
}
