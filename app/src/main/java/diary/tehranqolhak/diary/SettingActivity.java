package diary.tehranqolhak.diary;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class SettingActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> languages;
//    int o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        bind();
//        generateSpinner();


    }


    @Override
    protected void onResume() {
//        spinner.setSelection(Hawk.get("spinner",o));
        super.onResume();
    }

    private void bind() {
        spinner = findViewById(R.id.spinner);
    }
}
