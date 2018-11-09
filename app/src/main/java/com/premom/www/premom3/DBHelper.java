package com.premom.www.premom3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {              super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // String 보다 StringBuffer가 Query 만들기 편하다.
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE diary ( ");
        sb.append(" idx INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" img BLOB, ");
        sb.append(" title TEXT, ");
        sb.append(" date TEXT, ");
        sb.append(" content TEXT ) ");
        db.execSQL(sb.toString());

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addDiary(DiaryItem data) {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement p = db.compileStatement("INSERT INTO diary (img, title, content, date) values(?, ?, ?, ?)");

        p.bindBlob(1, data.getIs_pic());
        p.bindString(2, data.getTitle());
        p.bindString(3, data.getContent());
        p.bindString(4, data.getDate());

        long id = p.executeInsert();
        p.close();
        db.close();

        Log.d("insert", Long.toString(id));

    }

    public void delete() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from diary;");
    }

    public ArrayList<DiaryItem> getAllDiaryData() {
        ArrayList<DiaryItem> datas = new ArrayList<>();

        try {
            StringBuffer sb = new StringBuffer();
            sb.append(" SELECT * FROM diary order by idx desc");
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(sb.toString(), null);

            DiaryItem data = null;
            if(cursor!=null && cursor.getCount() > 0) {
                Log.d("cursor", cursor.getCount() + " ");
                while (cursor.moveToNext()) {
                    data = new DiaryItem();

                    data.setIs_pic(cursor.getBlob(1));
                    data.setTitle(cursor.getString(2));
                    data.setContent(cursor.getString(3));
                    data.setDate(cursor.getString(4));

                    datas.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;

    }
}
