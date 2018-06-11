package fmi.edu.alwaysontime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.sql.Time;

import fmi.edu.alwaysontime.R;
import fmi.edu.alwaysontime.db.schedule.ScheduleModel;
import fmi.edu.alwaysontime.sevices.schedule.ScheduleService;
import fmi.edu.alwaysontime.util.AppConfigurationOptions;
import fmi.edu.alwaysontime.util.UtilFunctions;

public class SetDateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void addSchedule(View view) {
        ScheduleModel scheduleModel = buildScheduleModel();
        ScheduleService service = new ScheduleService(getApplicationContext());
        long id = service.addSchedule(scheduleModel);
        Intent intent = new Intent(SetDateActivity.this, SetDateActivity.class);
        intent.putExtra(AppConfigurationOptions.SCHEDULE_ACTIVITY_ID, id);
        startActivity(intent);
    }

    private ScheduleModel buildScheduleModel() {
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        return new ScheduleModel(UtilFunctions.toDateString(day, month, year),
                UtilFunctions.toTimeString(hour, minute));
    }
}
