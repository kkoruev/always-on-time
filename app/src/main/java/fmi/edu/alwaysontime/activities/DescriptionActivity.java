package fmi.edu.alwaysontime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import fmi.edu.alwaysontime.R;
import fmi.edu.alwaysontime.db.schedule.ScheduleModel;
import fmi.edu.alwaysontime.sevices.schedule.ScheduleService;
import fmi.edu.alwaysontime.util.AppConfigurationOptions;

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
        long id = getIntent().getLongExtra(AppConfigurationOptions.SCHEDULE_ACTIVITY_ID,-1);
        scheduleService.updateScheduleDescriptin(id, editTextTitle.getText().toString(),
                editTextDescription.getText().toString());
        Intent intent = new Intent(DescriptionActivity.this, PlanActivity.class);
        startActivity(intent);
    }

}
