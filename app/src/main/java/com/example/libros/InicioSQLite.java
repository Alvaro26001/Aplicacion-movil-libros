package com.example.libros;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class InicioSQLite extends SQLiteOpenHelper {

    public InicioSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table libro(claveLibro integer  primary key unique,nombreLibro text, nombreAutor text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists libro");
        db.execSQL("create table libro(claveLibro integer  primary key unique,nombreLibro text, nombreAutor text)");

    }
}
