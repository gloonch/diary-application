package diary.tehranqolhak.diary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

import diary.tehranqolhak.diary.DB.DBHandler;
import diary.tehranqolhak.diary.DB.DiaryModel;
import diary.tehranqolhak.diary.Utils.ListAdapter;

public class ListActivity extends AppCompatActivity {

    ListView listview;
    DBHandler dbHandler;
    ListAdapter adapter;

    String locale = Locale.getDefault().getDisplayLanguage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listview = findViewById(R.id.list);

        dbHandler = new DBHandler(this);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (locale) {
                    case "English":
                        showAlertDialog("Delete this diary ?", "YES", "CANCEL", adapterView, i);
                        break;
                    case "Deutsch":
                        showAlertDialog("Dieses Tagebuch löschen ?", "JA", "STORNIEREN", adapterView, i);
                        break;
                    case "español":
                        showAlertDialog("Eliminar este diario ?", "SÍ", "CANCELAR", adapterView, i);
                        break;
                    case "français":
                        showAlertDialog("Supprimer ce journal ?", "OUI", "ANNULER", adapterView, i);
                        break;
                    case "italiano":
                        showAlertDialog("Cancellare questo diario ?", "SÌ", "ANNULLA", adapterView, i);
                        break;
                    case "português":
                        showAlertDialog("Apagar este diario ?", "SIM", "CANCELAR", adapterView, i);
                        break;
                }
                return false;
            }
        });
        findViewById(R.id.settingActivity).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingActivity.class));
        });
        generateList();

    }

    public void showAlertDialog(String title, String yes, String cancel, AdapterView<?> adapterView, int i) {
        new AlertDialog.Builder(ListActivity.this).setTitle(title)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        DiaryModel dm = (DiaryModel) adapterView.getItemAtPosition(i);
                        dbHandler.open();
                        dbHandler.deleteItem(dm.getId());
                        dbHandler.close();
                        generateList();
                    }
                }).setNeutralButton(cancel, null).show();
    }

    public void generateList() {
        dbHandler.open();
        List<DiaryModel> diaryModels = dbHandler.getAllDiary();
        adapter = new ListAdapter(this, diaryModels);
        listview.setAdapter(adapter);
        listview.deferNotifyDataSetChanged();
        dbHandler.close();
    }

}
