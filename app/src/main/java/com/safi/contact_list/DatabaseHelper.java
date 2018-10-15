package com.safi.contact_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="person.db";
    private static final String DATABASE_TABLE="person";
    private static final String ID="id";
    private static final int VERSION_NUMBER=1;
    private static final String NAME="name";
    private static final String NUMBER="number";
    private static final String CREATE_TABLE="CREATE TABLE "+DATABASE_TABLE+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(20),"+NUMBER+" INTEGER);";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+DATABASE_TABLE;
    private Context context;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER );
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            Toast.makeText(context, "onCreate is Called", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try {
                    db.execSQL(DROP_TABLE);
                    onCreate(db);
                    Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
                }
    }
    public long savedata(String name,String phn){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(NUMBER,phn);
        long rowNumber=sqLiteDatabase.insert(DATABASE_TABLE,null,contentValues);
        return rowNumber;
    }
    public Cursor showalldata(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+DATABASE_TABLE,null);
        return cursor;
    }
}
