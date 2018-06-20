package fmi.edu.alwaysontime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import fmi.edu.alwaysontime.R;
import fmi.edu.alwaysontime.db.schedule.ScheduleConst;
import fmi.edu.alwaysontime.sevices.schedule.ScheduleService;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        EditText editText = (EditText) findViewById(R.id.title_text);
        String title = getIntent().getStringExtra(ScheduleConst.TITLE_COLUMN);
        editText.setText(title);

        TextView description = (TextView) findViewById(R.id.description_text);
        description.setText(retrieveDescription(title));
        editText.setKeyListener(null);
    }

    private String retrieveDescription(String title) {
        final ScheduleService scheduleService = new ScheduleService(getApplicationContext());
        return scheduleService.getDecription(title);
    }

    public void back(View view) {
        Intent intent = new Intent(InformationActivity.this, PlanActivity.class);
        startActivity(intent);
    }

}
