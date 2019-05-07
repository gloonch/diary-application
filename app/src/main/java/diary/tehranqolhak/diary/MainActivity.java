package diary.tehranqolhak.diary;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import diary.tehranqolhak.diary.DB.DBHandler;
import diary.tehranqolhak.diary.Utils.DiaryModel;

public class MainActivity extends AppCompatActivity {

    TextView dayID, dateID;
    ImageButton nextbtnID;
    Button savebtnID;
    EditText diaryID;
    Boolean backpressed = false;
    String todaysPostDate;
    DBHandler dbHandler;
    String locale = Locale.getDefault().getDisplayLanguage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        bind();
        showDate();

        dbHandler = new DBHandler(this);
        savebtnID.setOnClickListener(v -> {
            if (diaryID.getText().toString().trim().equalsIgnoreCase("")) {
                diaryID.setError(null);
            } else {
                dbHandler.open();
                DiaryModel dm = new DiaryModel();
                dm.setDescription(String.valueOf(diaryID.getText()));
                dm.setDate(todaysPostDate);
                dbHandler.addDiary(dm);
                dbHandler.close();
                diaryID.setText("");
                    switch (locale) {
                        case "English":
                            Toast.makeText(this, "Diary has been saved", Toast.LENGTH_SHORT).show();
                            break;
                        case "Deutsch":
                            Toast.makeText(this, "Tagebuch wurde gerettet", Toast.LENGTH_SHORT).show();
                            break;
                        case "español":
                            Toast.makeText(this, "El diario ha sido guardado", Toast.LENGTH_SHORT).show();
                            break;
                        case "français":
                            Toast.makeText(this, "Journal a été enregistré", Toast.LENGTH_SHORT).show();
                            break;
                        case "italiano":
                            Toast.makeText(this, "Il diario è stato salvato", Toast.LENGTH_SHORT).show();
                            break;
                        case "português":
                            Toast.makeText(this, "O diário foi salvo", Toast.LENGTH_SHORT).show();
                            break;
                    }
            }
        });
        nextbtnID.setOnClickListener(v -> {
            startActivity(new Intent(this, ListActivity.class));
        });
    }

    @Override
    protected void onPause() {
        dbHandler.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        dbHandler.open();
        super.onResume();
    }

    void bind() {
        nextbtnID = findViewById(R.id.nextbtnID);
        dateID = findViewById(R.id.dateID);
        dayID = findViewById(R.id.dayID);
        diaryID = findViewById(R.id.diaryID);
        savebtnID = findViewById(R.id.savebtnID);
    }

    public void showDate() {
        //day of week
        SimpleDateFormat dow = new SimpleDateFormat("EEE");
        Date dayName = new Date();
        String dayOfWeek = dow.format(dayName);
        dayID.setText(dayOfWeek);

        //day and month number
        SimpleDateFormat format = new SimpleDateFormat("dd / MM");
        Date monthAndDay = new Date();
        String monthandday = format.format(monthAndDay);
        dateID.setText(monthandday);

        //full date number
        SimpleDateFormat format1 = new SimpleDateFormat("YYYY / dd / MM");
        Date monthAndDay1 = new Date();
        todaysPostDate = format1.format(monthAndDay1);
    }

    @Override
    public void onBackPressed() {
        if (backpressed) {
            finish();
        } else {

            if ("English".equals(locale))
                Toast.makeText(this, "Press again for exit", Toast.LENGTH_SHORT).show();
            if ("Deutsch".equals(locale))
                Toast.makeText(this, "Zum Verlassen erneut drücken", Toast.LENGTH_SHORT).show();
            if ("español".equals(locale))
                Toast.makeText(this, "Presione de nuevo para salir", Toast.LENGTH_SHORT).show();
            if ("français".equals(locale))
                Toast.makeText(this, "Appuyez à nouveau pour sortir", Toast.LENGTH_SHORT).show();
            if ("italiano".equals(locale))
                Toast.makeText(this, "Premere di nuovo per uscire", Toast.LENGTH_SHORT).show();
            if ("português".equals(locale))
                Toast.makeText(this, "Pressione novamente para sair", Toast.LENGTH_SHORT).show();
            backpressed = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backpressed = false;
                }
            }, 2000);
        }
    }
}