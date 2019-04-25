package diary.tehranqolhak.diary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

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
        Hawk.init(this).build();


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
