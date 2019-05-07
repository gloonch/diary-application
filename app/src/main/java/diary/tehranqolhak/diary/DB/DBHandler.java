package diary.tehranqolhak.diary.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import diary.tehranqolhak.diary.Utils.DiaryModel;

public class DBHandler {
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    String[] diaryColumns = {DBOpenHelper.DIARY_ID, DBOpenHelper.DIARY_DESCRIPTION, DBOpenHelper.DIARY_DATE};

    public DBHandler(Context context) {
        sqLiteOpenHelper = new DBOpenHelper(context);
//        database.close();
    }

    public void addDiary(DiaryModel diaryModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBOpenHelper.DIARY_DESCRIPTION, diaryModel.getDescription());
        contentValues.put(DBOpenHelper.DIARY_DATE, diaryModel.getDate());
        database.insert(DBOpenHelper.TABLE_DIARY, null, contentValues);
    }

    public void deleteItem(int id) {
        database.delete(DBOpenHelper.TABLE_DIARY, DBOpenHelper.DIARY_ID + " = " + id, null);
    }

    public List<DiaryModel> getAllDiary() {
        List<DiaryModel> diaryModels = new ArrayList<>();
//        diaryColumns[0] + " DESC"
        Cursor cursor = database.query(DBOpenHelper.TABLE_DIARY, diaryColumns, null, null, null, null, diaryColumns[0] + " DESC");
        if (cursor.moveToFirst()) {
            do {
                DiaryModel diaryModel = new DiaryModel();
                diaryModel.setId(cursor.getInt(cursor.getColumnIndex(DBOpenHelper.DIARY_ID)));
                diaryModel.setDescription(cursor.getString(cursor.getColumnIndex(DBOpenHelper.DIARY_DESCRIPTION)));
                diaryModel.setDate(cursor.getString(cursor.getColumnIndex(DBOpenHelper.DIARY_DATE)));
                diaryModels.add(diaryModel);
            } while (cursor.moveToNext());
        }
        database.close();
        return diaryModels;
    }

    public void open() {
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }
}
