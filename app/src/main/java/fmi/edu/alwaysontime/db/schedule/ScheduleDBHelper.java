package fmi.edu.alwaysontime.db.schedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fmi.edu.alwaysontime.util.AppConfigurationOptions;

public class ScheduleDBHelper extends SQLiteOpenHelper {

    public ScheduleDBHelper(Context context) {
        super(context, AppConfigurationOptions.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ScheduleConst.CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
