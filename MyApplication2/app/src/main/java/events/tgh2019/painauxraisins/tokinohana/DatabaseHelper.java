package events.tgh2019.painauxraisins.tokinohana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    // データーベースのバージョン
    public static final int DATABASE_VERSION = 1;
    // データーベース名
    public static final String DATABASE_NAME = "PointDB.db";
    private static final String TABLE_NAME = "POINTS";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY autoincrement, add_date DATE, point INTEGER)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(
                SQL_CREATE_ENTRIES
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // アップデートの判別、古いバージョンは削除して新規作成
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db,
                            int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void saveData(Date addDate, int point) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        values.put("add_date", sf.format(addDate));
        values.put("point", point);

        db.insert(TABLE_NAME, null, values);
    }


    public int calculateTotal() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                new String[]{"add_date", "point"},   // The array of columns to return (pass null to get all)
                null,    // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null,         // don't group the rows
                null,         // don't filter by row groups
                null
        );

        // cursor.moveToFirst();
        int sum = 0;

        while (cursor.moveToNext()) {
            String date = cursor.getString(0);
            int point = cursor.getInt(1);
            Log.d("tag", date + " " + point);

            sum += point;
        }

        return sum;
    }
}