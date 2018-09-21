package vn.edu.poly.projectbookmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import vn.edu.poly.projectbookmanager.model.User;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String TABLE_USER = "User";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_PHONE_NUMBER = "Phone_number";

    public static final String CREAT_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +
            COLUMN_USERNAME + " VARCHAR PRIMARY KEY," +
            COLUMN_PASSWORD + " VARCHAR," +
            COLUMN_NAME + " VARCHAR," +
            COLUMN_PHONE_NUMBER + " VARCHAR)";

    public DatabaseHelper(Context context) {
        super(context, "bookmanager", null, 1);
    }
    public void insertUser(User user){
        //Xin quyen ghi!!!
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //Tao doi tuong de truyen du lieu la ContentValues
        ContentValues contentValues = new ContentValues();
        //Dua gia tri tuong ung User vao ContentValues;
        contentValues.put(COLUMN_USERNAME,user.getUsername());

        contentValues.put(COLUMN_PASSWORD,user.getPassword());

        contentValues.put(COLUMN_NAME,user.getName());

        contentValues.put(COLUMN_PHONE_NUMBER,user.getPhone());

        //Viet cau lenh insert vao DB
        long id = sqLiteDatabase.insert(TABLE_USER, null, contentValues);

        Log.e("insertUser","ID = " + id );

//        sqLiteDatabase.insert(TABLE_USER,null, contentValues);

        sqLiteDatabase.close();
    }
    public User getUser(String username){
        User user = null;

        //Xin quyen ghi!!!
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Tao cau lenh voi query voi cursor

        Cursor cursor = sqLiteDatabase.query(TABLE_USER, new String[]{COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_NAME, COLUMN_PHONE_NUMBER},
                COLUMN_USERNAME + "=?", new String[]{username}, null,null, null);
//                (TABLE_USER, new String[]{COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_NAME, COLUMN_PHONE_NUMBER},
//                        COLUMN_USERNAME + "=?", new String[]{username},
//                        null, null, null);
        //kiem tra xem cursor !=null va co chua gia tri
        if (cursor!=null && cursor.moveToFirst()){
            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));
            user = new User(user_name, password, name, phone);
        }

        return user;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
