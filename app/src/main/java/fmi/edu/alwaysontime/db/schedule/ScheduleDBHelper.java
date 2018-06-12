package fmi.edu.alwaysontime.db.schedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fmi.edu.alwaysontime.util.AppConfigurationOptions;

public class ScheduleDBHelper extends SQLiteOpenHelper {

    public ScheduleDBHelper(Context context, int version) {
        super(context, AppConfigurationOptions.DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ScheduleConst.CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ScheduleConst.SQL_DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
