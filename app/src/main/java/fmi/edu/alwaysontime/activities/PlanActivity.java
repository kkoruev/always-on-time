package fmi.edu.alwaysontime.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import fmi.edu.alwaysontime.R;
import fmi.edu.alwaysontime.db.schedule.ScheduleConst;
import fmi.edu.alwaysontime.db.schedule.ScheduleModel;
import fmi.edu.alwaysontime.sevices.schedule.ScheduleService;

public class PlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        TextView questionValue = (TextView) findViewById(R.id.textView);
        questionValue.setTypeface(null, Typeface.BOLD);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
            System.out.println("HERE_cal");
            Intent intent = new Intent(PlanActivity.this, SetDateActivity.class);
            startActivity(intent);
            return true;
            }
        });
        prepareList();
        refreshList();
    }

    public void refreshList() {
        final ScheduleService scheduleService = new ScheduleService(getApplicationContext());
        ArrayList<String> listItems=new ArrayList<String>();

        List<ScheduleModel> scheduleModelList = scheduleService.retrieveAllSchedules();
        for (ScheduleModel model : scheduleModelList) {
            listItems.add(model.getTitle() + " - " + model.getTime() + " " + model.getDate());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.text_view_file, listItems);
        ListView listView = (ListView) findViewById(R.id.schedules);
        listView.setAdapter(adapter);
    }

    private void prepareList() {
        final ScheduleService scheduleService = new ScheduleService(getApplicationContext());

        ListView listView = (ListView) findViewById(R.id.schedules);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String content = (String) adapterView.getItemAtPosition(i);
                String[] parts = content.split(" - ");
                Intent intent = new Intent(PlanActivity.this, InformationActivity.class);
                intent.putExtra(ScheduleConst.TITLE_COLUMN, parts[0]);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("LONG LONG");
                String content = (String) adapterView.getItemAtPosition(i);
                String[] parts = content.split(" - ");
                scheduleService.deleteSchedule(parts[0]);
                refreshList();
                return true;
            }
        });
    }

//    public void showCalendar(View view) {
//        System.out.println("HERE_cal");
//        Intent intent = new Intent(PlanActivity.this, SetDateActivity.class);
//        startActivity(intent);
//    }

//    public void showPlan(View view) {
//        System.out.println("Here");
//    }
}
