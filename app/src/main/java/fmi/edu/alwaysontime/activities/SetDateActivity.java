package fmi.edu.alwaysontime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import fmi.edu.alwaysontime.R;
import fmi.edu.alwaysontime.shared.SharedConstants;
import fmi.edu.alwaysontime.shared.UtilFunctions;

public class SetDateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void addSchedule(View view) {
        Intent intent = new Intent(SetDateActivity.this, DescriptionActivity.class);
        intent.putExtra(SharedConstants.SCHEDULE_DATE, makeDate());
        intent.putExtra(SharedConstants.SCHEDULE_TIME, makeTime());
        startActivity(intent);
    }

    private String makeDate() {
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        return UtilFunctions.toDateString(day, month, year);
    }

    private String makeTime() {
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        return UtilFunctions.toTimeString(hour, minute);
    }
}
