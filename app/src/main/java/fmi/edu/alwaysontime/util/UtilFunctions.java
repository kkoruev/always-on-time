package fmi.edu.alwaysontime.util;

public class UtilFunctions {
    private UtilFunctions() {

    }

    public static String toDateString(int day, int month, int year) {
        return String.format("%d.%d.%d", day, month, year);
    }

    public static String toTimeString(int hour, int minute) {
        return String.format("%d.%d", hour, minute);
    }
}
