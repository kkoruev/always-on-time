package fmi.edu.alwaysontime.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import fmi.edu.alwaysontime.R;
import fmi.edu.alwaysontime.db.schedule.ScheduleModel;
import fmi.edu.alwaysontime.sevices.schedule.ScheduleService;
import fmi.edu.alwaysontime.shared.SharedConstants;

public class DescriptionActivity extends AppCompatActivity {

    private static int requestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void addReminder(View view) {
        EditText editTextTitle = (EditText) findViewById(R.id.schedule_title);
        EditText editTextDescription = (EditText) findViewById(R.id.schedule_description);
        ScheduleService scheduleService = new ScheduleService(getApplicationContext());
        ScheduleModel scheduleModel = produceModel(getIntent(), editTextTitle.getText().toString(),
                editTextDescription.getText().toString());
        long id = scheduleService.addSchedule(scheduleModel);
        Intent intent = new Intent(DescriptionActivity.this, PlanActivity.class);

        Intent currentIntent = new Intent(this, AlarmReceiverActivity.class);
        currentIntent.putExtra(SharedConstants.SCHEDULE_TITLE, scheduleModel.getTitle());
        currentIntent.putExtra(SharedConstants.SCHEDULE_DESCRIPTION, scheduleModel.getDescription());
        Calendar calendar = calendarSchedule(scheduleModel);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                requestCode++, currentIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager am =
                (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                pendingIntent);
        startActivity(intent);
    }

    private Calendar calendarSchedule(ScheduleModel model) {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
        String[] date  = model.getDate().split("\\.");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        String[] time = model.getTime().split(":");
        int hour = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int am_pm = -1;
        if(hour >= 12) {
            am_pm = 1;
        } else{
            am_pm = 0;
        }
        cal.set(Calendar.AM_PM, am_pm);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR_OF_DAY, hour);

        cal.set(Calendar.MINUTE, minutes);
        return cal;
    }

    private ScheduleModel produceModel(Intent intent, String title, String description) {
        ScheduleModel scheduleModel = new ScheduleModel(
                intent.getExtras().getString(SharedConstants.SCHEDULE_DATE),
                intent.getExtras().getString(SharedConstants.SCHEDULE_TIME));
        scheduleModel.setTitle(title);
        scheduleModel.setDescription(description);
        return scheduleModel;
    }

}
