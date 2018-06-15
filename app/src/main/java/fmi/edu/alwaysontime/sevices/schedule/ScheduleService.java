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

    private ScheduleDBHelper dbHelper;

    public ScheduleService(Context context) {
        dbHelper = new ScheduleDBHelper(context, ScheduleConst.VERSION);
    }

    public long addSchedule(ScheduleModel scheduleModel) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ScheduleConst.DATE_COLUMN, scheduleModel.getDate());
        values.put(ScheduleConst.TIME_COLUMN, scheduleModel.getTime());
        values.put(ScheduleConst.TITLE_COLUMN, scheduleModel.getTitle());
        values.put(ScheduleConst.DESCRIPTION_COLUMN, scheduleModel.getDescription());

        return db.insert(ScheduleConst.TABLE_NAME, null, values);
    }

    public long updateScheduleDescriptin(long id, String title, String description) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ScheduleConst.TITLE_COLUMN, title);
        values.put(ScheduleConst.DESCRIPTION_COLUMN, description);
        String whereClause = String.format("%s=?", ScheduleConst.ID_COLUMN);
        String[] selectionArgs = {String.format("%d", id)};
        System.out.println(id);
        int count = db.update(ScheduleConst.TABLE_NAME, values, whereClause, selectionArgs);
        db.execSQL("UPDATE " + ScheduleConst.TABLE_NAME + " SET title='kris' WHERE id=6");
        System.out.println(count);
        return 1l;
    }

    public long deleteSchedue(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = ScheduleConst.ID_COLUMN + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { Long.toString(id) };
        // Issue SQL statement.
        int deletedRows = db.delete(ScheduleConst.TABLE_NAME, selection, selectionArgs);
        db.execSQL("Delete from " + ScheduleConst.TABLE_NAME + " WHERE id=" + id);
        System.out.println(deletedRows);
        return 1l;
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
            String title = cursor.getString(cursor.getColumnIndex(ScheduleConst.TITLE_COLUMN));
            title = title == null ? "" : title;
            String description = cursor.getString(cursor.getColumnIndex(ScheduleConst.DESCRIPTION_COLUMN));
            description = description == null ? "" : description;
            ScheduleModel scheduleModel = new ScheduleModel(date, time);
            scheduleModel.setTitle(title);
            scheduleModel.setDescription(description);
            schedules.add(scheduleModel);
        }
        return schedules;
    }
}
