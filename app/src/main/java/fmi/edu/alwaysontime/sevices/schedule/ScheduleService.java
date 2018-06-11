package fmi.edu.alwaysontime.sevices.schedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fmi.edu.alwaysontime.db.schedule.ScheduleConst;
import fmi.edu.alwaysontime.db.schedule.ScheduleDBHelper;
import fmi.edu.alwaysontime.db.schedule.ScheduleModel;

public class ScheduleService {

    ScheduleDBHelper dbHelper;
    public ScheduleService(Context context) {
        dbHelper = new ScheduleDBHelper(context);
    }

    public long addSchedule(ScheduleModel scheduleModel) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ScheduleConst.DATE_COLUMN, scheduleModel.getDate());
        values.put(ScheduleConst.TIME_COLUMN, scheduleModel.getTime());

        return db.insert(ScheduleConst.TABLE_NAME, null, values);
    }

    public List<ScheduleModel> retrieveAllSchedules() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor  = db.query(
                ScheduleConst.TABLE_NAME,
                null,
                null,
                null,
                null,
                 null,
                null
        );
        List schedules = new ArrayList<>();
        while(cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndex(ScheduleConst.DATE_COLUMN));
            String time = cursor.getString(cursor.getColumnIndex(ScheduleConst.TIME_COLUMN));
            schedules.add(new ScheduleModel(date, time));
        }
        return schedules;
    }
}
