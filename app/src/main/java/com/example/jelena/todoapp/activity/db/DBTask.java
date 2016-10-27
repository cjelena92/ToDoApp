package com.example.jelena.todoapp.activity.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jelena.todoapp.activity.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jelena on 19/10/2016.
 */

public class DBTask extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "db";

    private static final String TABLE_TASKS = "tasks";

    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_STATUS = "status";

    public DBTask(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ( " + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_TITLE + " TEXT, "
                + KEY_DESCRIPTION + " TEXT, " +KEY_STATUS + " INTEGER)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_TASKS);
        onCreate(db);
    }

    public void addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, task.getTitle());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_STATUS, task.getStatus());

        db.insert(TABLE_TASKS, null, values);
        Log.d("Database operations", "One row inserted");
        db.close();

    }

    public void deleteTask(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " =?", new String[]{String.valueOf(id)} );
        db.close();
    }


    public List<Task> getAllTasks() {
        List<Task> list= new ArrayList<>();
        String query = "SELECT * FROM " +TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                Task task = new Task ();
                task.setId(cursor.getInt(0));
                task.setTitle(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setStatus(cursor.getInt(3));
                list.add(task);
            } while (cursor.moveToNext());
        }
            return list;
    }

    public Task getTask(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_TASKS,
                        new String[] { KEY_TITLE,
                                KEY_DESCRIPTION },KEY_ID + "=?",
                        new String[] { String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);

        if (cursor != null)
            cursor.moveToFirst();
        Task task = new Task();
        task.setTitle(cursor.getString(0));
        task.setDescription(cursor.getString(1));

        return task;
    }

    public void updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, task.getTitle());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_STATUS, task.getStatus());
        db.update(TABLE_TASKS, values, KEY_ID +" = ? ",new String[]
                {String.valueOf(task.getId())});
        db.close();
    }


}

