package fmi.edu.alwaysontime.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import fmi.edu.alwaysontime.R;

public class PlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);


        TextView questionValue = (TextView) findViewById(R.id.textView);
        questionValue.setTypeface(null, Typeface.BOLD);

    }

    public void showCalendar(View view) {
        Intent intent = new Intent(PlanActivity.this, SetDateActivity.class);
        startActivity(intent);
    }


}
