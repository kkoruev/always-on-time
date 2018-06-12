package fmi.edu.alwaysontime.db.schedule;

public final class ScheduleConst {

    private ScheduleConst() {

    }

    public static final String TABLE_NAME = "Schedule";
    public static final String DATE_COLUMN = "date";
    public static final String TIME_COLUMN = "time";
    public static final String ID_COLUMN = "id";
    public static final String TITLE_COLUMN = "title";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final int VERSION = 2;

    public static final String CREATE_TABLE_QUERY =
            String.format("CREATE TABLE %s ( %s id integer PRIMARY KEY, %s text NOT NULL, " +
                    "%s text NOT NULL, %s text, %s text )",
                    TABLE_NAME, ID_COLUMN, DATE_COLUMN, TIME_COLUMN, TITLE_COLUMN, DESCRIPTION_COLUMN);

    public static String SQL_DROP_TABLE =
        String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
}
