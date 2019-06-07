package diary.tehranqolhak.diary;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Calendar;
import java.util.TimeZone;

import diary.tehranqolhak.diary.Utils.TimeAlertDialog;


public class SettingActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> languages;
    //    int o;
    Switch mSwitch;
    AppCompatTextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        bind();

//        generateSpinner();
        time.setOnClickListener(view -> {
            TimeAlertDialog dialog = new TimeAlertDialog();
            dialog.show(getSupportFragmentManager(), null);
//            TODO pass data back here to set it in hawk
        });
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    setAlarm(true);
                    Toast.makeText(SettingActivity.this, "Reminder turned on", Toast.LENGTH_SHORT).show();
                    time.setTextColor(Color.rgb(255,240,0));
//                    Hawk.set(true)
                }
                else if (!b){
                    setAlarm(false);
                    Toast.makeText(SettingActivity.this, "Reminder turned off", Toast.LENGTH_SHORT).show();
                    time.setTextColor(Color.rgb(220,220,220));
//                    Hawk.set(false);
//                    disable pending alarm :|
                }
            }
        });


    }

    private void setAlarm(boolean turnOn) {
        if (turnOn){
            startAlert();

        }
        else if (!turnOn){


        }

    }

    private void startalarm1(Context context) {

        Calendar time = Calendar.getInstance();
        time.setTimeZone(TimeZone.getTimeZone("GMT"));
        time.set(Calendar.HOUR_OF_DAY, 12);
        time.set(Calendar.MINUTE, 3);

        Intent intent = new Intent(context, reminder.class);
        PendingIntent pendingAlarm = PendingIntent.getBroadcast(getApplicationContext(),
                9797, intent, 0);

        AlarmManager alarms = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingAlarm);
        Toast.makeText(context, "alarm is set for ...", Toast.LENGTH_SHORT).show();
    }

    public void startAlert(){
        int i = 5;
        Intent intent = new Intent(this, reminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000), pendingIntent);
        Toast.makeText(this, "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
//        spinner.setSelection(Hawk.get("spinner",o));
        super.onResume();
    }

    private void bind() {
        mSwitch = findViewById(R.id.mSwitch);
        time = findViewById(R.id.time);
//        spinner = findViewById(R.id.spinner);
    }
}
