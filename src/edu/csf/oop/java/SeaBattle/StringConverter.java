package edu.csf.oop.java.SeaBattle;

public class StringConverter {
    private static boolean hide = false;
    public static String fieldToString(SeaBattleField field) {
        String res = "";
        res += StringColor.ANSI_RESET + "  0 1 2 3 4 5 6 7 8 9\n";
        for (int i = 0; i < field.SIZE; i++) {
            res += StringColor.ANSI_RESET + i + " ";
            for (int j = 0; j < field.SIZE; j++) {
                res += fieldTypeConvert(field.getFieldType(j, i)) + " ";
            }
            res += "\n";
        }
        return res;
    }

    private static String fieldTypeConvert(SeaBattleField.FieldType fieldType) {
        return switch (fieldType) {
            case VOID -> StringColor.ANSI_WHITE + "." + StringColor.ANSI_RESET;
            case SHIP -> hide ? (StringColor.ANSI_WHITE + ".") : (StringColor.ANSI_GREEN + "#") + StringColor.ANSI_RESET;
            case ATTACKED_SHIP -> StringColor.ANSI_RED + "X" + StringColor.ANSI_RESET;
            case ATTACKED_VOID -> StringColor.ANSI_YELLOW + "*" + StringColor.ANSI_RESET;
            default -> "";
        };
    }

    public static boolean isHide() {
        return hide;
    }

    public static void setHide(boolean hide) {
        StringConverter.hide = hide;
    }
}
