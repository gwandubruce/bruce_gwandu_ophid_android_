package zw.co.softwarezimbabwe.ophidclients;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "ophidclients.db";
    public static final String TABLE_NAME = "tbl_client";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean InsertData(Client client){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("firstname", client.getFirstname());
            contentValues.put("lastname", client.getLastname());
            contentValues.put("address", client.getAddress());
            long result = db.insert(TABLE_NAME, null, contentValues);
            db.close();
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }

    public List<Client> DisplayAll(){
        try{
            List<Client> clients = new ArrayList<Client>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            if(cursor.moveToFirst()){
                do{
                    Client client = new Client();
                    client.setId(cursor.getInt(0));
                    client.setFirstname(cursor.getString(1));
                    client.setLastname(cursor.getString(2));
                    client.setAddress(cursor.getString(3));
                    clients.add(client);
                }while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return clients;
        }catch (Exception e){
            return null;
        }

    }

    public boolean Delete(int id){
        try{
            SQLiteDatabase db = getWritableDatabase();
            int row = db.delete(TABLE_NAME, "ID = ?", new String[]{String.valueOf(id)});
            db.close();
            return row > 0;
        }catch (Exception e){
            return false;
        }
    }

    public boolean Update(Client client){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("firstname", client.getFirstname());
            contentValues.put("lastname", client.getLastname());
            contentValues.put("address", client.getAddress());
            int row = db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{String.valueOf(client.getId())});
            db.close();
            return row > 0;
        }catch (Exception e){
            return false;
        }
    }

}