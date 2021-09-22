package com.company;

public class StringConverter {
    private static boolean hide = false;
    public static String fieldToString(SeaBattleField field) {
        String res = "";
        res += "  0 1 2 3 4 5 6 7 8 9\n";
        for (int i = 0; i < field.SIZE; i++) {
            res += i + " ";
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
            case SHIP -> hide ? '.' : '#';
            case ATTACKED_SHIP -> 'X';
            case ATTACKED_VOID -> '*';
        };
    }

    public static boolean isHide() {
        return hide;
    }

    public static void setHide(boolean hide) {
        StringConverter.hide = hide;
    }
}
