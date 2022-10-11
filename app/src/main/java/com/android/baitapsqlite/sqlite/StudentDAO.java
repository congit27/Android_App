package com.android.baitapsqlite.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.android.baitapsqlite.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private SQLiteDatabase db;

    public StudentDAO(Context context) {
        DBHelper helper = new DBHelper(context);

        db = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Student> get(String sql, String ...selecArgs) {
        List<Student> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selecArgs);

        while (cursor.moveToNext()) {
            Student emp = new Student();
            emp.setId(cursor.getString(cursor.getColumnIndex("id")));
            emp.setName(cursor.getString(cursor.getColumnIndex("name")));
            emp.setMark(cursor.getFloat(cursor.getColumnIndex("mark")));

            list.add(emp);
        }
            return list;
    }

    public  List<Student> getAll() {
        String sql = "SELECT * FROM student";

        return get(sql);
    }

    public Student getById(String id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        List<Student> list = get(sql, id);

        return list.get(0);
    }

    public long insert(Student emp) {
        ContentValues values = new ContentValues();
        values.put("id", emp.getId());
        values.put("name", emp.getName());
        values.put("mark", emp.getMark());

        return db.insert("student", null, values);
    }

    public long update(Student emp) {
        ContentValues values = new ContentValues();
        values.put("name", emp.getName());
        values.put("mark", emp.getMark());

        return db.update("student", values, "id = ?", new String[]{emp.getId()});
    }

    public int delete(String id) {
        return db.delete("student", "id = ?", new String[]{id});
    }
}
