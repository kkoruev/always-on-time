package fmi.edu.alwaysontime.shared;

import fmi.edu.alwaysontime.db.schedule.ScheduleConst;

public final class SharedConstants {
    private SharedConstants() {

    }

    public static final String SCHEDULE__ID = ScheduleConst.ID_COLUMN;
    public static final String SCHEDULE_TITLE = ScheduleConst.TITLE_COLUMN;
    public static final String SCHEDULE_TIME = ScheduleConst.TIME_COLUMN;
    public static final String SCHEDULE_DATE = ScheduleConst.DATE_COLUMN;
}
