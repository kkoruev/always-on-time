package fmi.edu.alwaysontime.activities;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import fmi.edu.alwaysontime.R;
import fmi.edu.alwaysontime.sevices.schedule.ScheduleService;
import fmi.edu.alwaysontime.shared.SharedConstants;

public class AlarmReceiverActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_receiver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String description = getIntent().getExtras().getString(SharedConstants.SCHEDULE_DESCRIPTION);
        EditText descriptionEditText = (EditText) findViewById(R.id.description_text);
        descriptionEditText.setKeyListener(null);
        descriptionEditText.setText(description);
        playSound(this, getAlarmUri());
    }

    public void stopAlarm(View view) {
        mMediaPlayer.stop();
        String title  = getIntent().getExtras().getString(SharedConstants.SCHEDULE_TITLE);
        ScheduleService scheduleService = new ScheduleService(getApplicationContext());
        scheduleService.deleteSchedule(title);
        Intent intent = new Intent(AlarmReceiverActivity.this, PlanActivity.class);
        startActivity(intent);
    }

    private void playSound(Context context, Uri alert){
        try {
            mMediaPlayer.setDataSource(context, alert);
            final AudioManager audioManager = (AudioManager) context
                    .getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong during ringtone loading");
        }
    }

    private Uri getAlarmUri() {
        Uri alert = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alert == null) {
            alert = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (alert == null) {
                alert = RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
        return alert;
    }

}
