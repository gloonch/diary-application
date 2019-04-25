package diary.tehranqolhak.diary.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "diary.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_DIARY = "diary";
    public static final String DIARY_ID = "id";
    public static final String DIARY_DESCRIPTION = "description";
    public static final String DIARY_DATE = "date";

    private static String QUERY_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_DIARY + "" +
                    " (" + DIARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " " + DIARY_DESCRIPTION + " TEXT, " +
                    " " + DIARY_DATE + " TEXT)";


    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY + " ");
        onCreate(sqLiteDatabase);
    }
}
