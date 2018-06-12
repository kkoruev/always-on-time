package fmi.edu.alwaysontime.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fmi.edu.alwaysontime.R;
import fmi.edu.alwaysontime.db.schedule.ScheduleModel;
import fmi.edu.alwaysontime.sevices.schedule.ScheduleService;

public class PlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        TextView questionValue = (TextView) findViewById(R.id.textView);
        questionValue.setTypeface(null, Typeface.BOLD);
        refreshList();
    }

    private void refreshList() {
        ScheduleService scheduleService = new ScheduleService(getApplicationContext());
        ArrayList<String> listItems=new ArrayList<String>();

        List<ScheduleModel> scheduleModelList = scheduleService.retrieveAllSchedules();
        for (ScheduleModel model : scheduleModelList) {
            listItems.add(model.getDate() + "  " + model.getTime() + " " + model.getTitle());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.text_view_file, listItems);
        ListView listView = (ListView) findViewById(R.id.schedules);
        listView.setAdapter(adapter);
    }

    public void showCalendar(View view) {
        Intent intent = new Intent(PlanActivity.this, SetDateActivity.class);
        startActivity(intent);
    }


}
