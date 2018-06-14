package fmi.edu.alwaysontime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import fmi.edu.alwaysontime.R;
import fmi.edu.alwaysontime.db.schedule.ScheduleModel;
import fmi.edu.alwaysontime.sevices.schedule.ScheduleService;
import fmi.edu.alwaysontime.shared.AppConfigurationOptions;
import fmi.edu.alwaysontime.shared.SharedConstants;

public class DescriptionActivity extends AppCompatActivity {

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
        scheduleService.addSchedule(produceModel(getIntent(), editTextTitle.getText().toString(),
                editTextDescription.getText().toString()));
        Intent intent = new Intent(DescriptionActivity.this, PlanActivity.class);
        startActivity(intent);
    }

    public ScheduleModel produceModel(Intent intent, String title, String description) {
        ScheduleModel scheduleModel = new ScheduleModel(
                intent.getExtras().getString(SharedConstants.SCHEDULE_DATE),
                intent.getExtras().getString(SharedConstants.SCHEDULE_TIME));
        scheduleModel.setTitle(title);
        scheduleModel.setDescription(description);
        return scheduleModel;
    }

}
