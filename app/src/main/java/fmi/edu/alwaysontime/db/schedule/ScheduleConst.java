package fmi.edu.alwaysontime.db.schedule;

public final class ScheduleConst {

    private ScheduleConst() {

    }

    public static final String TABLE_NAME = "Schedule";
    public static final String DATE_COLUMN = "date";
    public static final String TIME_COLUMN = "time";
    public static final String ID_COLUMNS = "id";

    public static final String CREATE_TABLE_QUERY =
            String.format("CREATE TABLE %s ( %s id integer PRIMARY KEY, %s text NOT NULL, " +
                    "%s text NOT NULL )", TABLE_NAME, ID_COLUMNS, DATE_COLUMN, TIME_COLUMN);
}
