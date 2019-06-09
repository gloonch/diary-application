package diary.tehranqolhak.diary;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
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

import com.orhanobut.hawk.Hawk;

import java.sql.Time;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import diary.tehranqolhak.diary.Utils.TimeAlertDialog;


public class SettingActivity extends AppCompatActivity implements TimeAlertDialog.onTimePickedInterface {

    Spinner spinner;
    ArrayAdapter<CharSequence> languages;
    //    int o;
    Switch mSwitch;
    AppCompatTextView time;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    public static final String STATE = "state";
    String locale = Locale.getDefault().getDisplayLanguage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        bind();
        TimeAlertDialog.onTimePickedInterface mListener = (TimeAlertDialog.onTimePickedInterface) this;
        Hawk.init(this).build();
        time.setText(Hawk.get("hour", 0) + ":" + Hawk.get("min", 0));
        mSwitch.setChecked(Hawk.get(STATE));

//        these lines belong to alarm method
        Intent intent = new Intent(this, reminder.class);
        pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 280192, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (Hawk.get(STATE, false).equals(true)) {
            time.setTextColor(Color.rgb(255, 240, 0));
        }

//        generateSpinner();
        time.setOnClickListener(view -> {
            TimeAlertDialog dialog = new TimeAlertDialog(mListener);
            dialog.show(getSupportFragmentManager(), null);
        });

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    switch (locale) {
                        case "English":
                            Toast.makeText(SettingActivity.this, "Reminder turned on", Toast.LENGTH_SHORT).show();
                            break;
                        case "Deutsch":
                            Toast.makeText(SettingActivity.this, "Erinnerung eingeschaltet", Toast.LENGTH_SHORT).show();
                            break;
                        case "español":
                            Toast.makeText(SettingActivity.this, "Recordatorio activado", Toast.LENGTH_SHORT).show();
                            break;
                        case "français":
                            Toast.makeText(SettingActivity.this, "Rappel activé", Toast.LENGTH_SHORT).show();
                            break;
                        case "italiano":
                            Toast.makeText(SettingActivity.this, "Promemoria attivato", Toast.LENGTH_SHORT).show();
                            break;
                        case "português":
                            Toast.makeText(SettingActivity.this, "Lembrete ativado", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    time.setTextColor(Color.rgb(255, 240, 0));
                    startAlertAtParticularTime(Hawk.get("hour", 0), Hawk.get("min", 0));
                    Hawk.put(STATE, true);
                } else if (!b) {
                    switch (locale) {
                        case "English":
                            Toast.makeText(SettingActivity.this, "Reminder turned off", Toast.LENGTH_SHORT).show();
                            break;
                        case "Deutsch":
                            Toast.makeText(SettingActivity.this, "Erinnerung ausgeschaltet", Toast.LENGTH_SHORT).show();
                            break;
                        case "español":
                            Toast.makeText(SettingActivity.this, "Recordatorio desactivado", Toast.LENGTH_SHORT).show();
                            break;
                        case "français":
                            Toast.makeText(SettingActivity.this, "Rappel désactivé", Toast.LENGTH_SHORT).show();
                            break;
                        case "italiano":
                            Toast.makeText(SettingActivity.this, "Promemoria disattivato", Toast.LENGTH_SHORT).show();
                            break;
                        case "português":
                            Toast.makeText(SettingActivity.this, "Lembrete desativado", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    time.setTextColor(Color.rgb(220, 220, 220));
                    alarmManager.cancel(pendingIntent);
                    Hawk.put(STATE, false);
                }
            }
        });


    }

    public void startAlertAtParticularTime(int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_HOUR, pendingIntent);
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

    @Override
    public void onTimePicked(int hour, int min) {
        Hawk.put("hour", hour);
        Hawk.put("min", min);
        time.setText(hour + ":" + min);
        time.setTextColor(Color.rgb(255, 240, 0));
        Hawk.put(STATE, true);
        mSwitch.setChecked(true);
    }
}
