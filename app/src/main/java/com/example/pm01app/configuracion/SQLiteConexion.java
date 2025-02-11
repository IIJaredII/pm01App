package com.example.pm01app.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteConexion extends SQLiteOpenHelper {

    public static final String NameDB="PM01DB";
    public static final int version=2;
    public static final SQLiteDatabase.CursorFactory factory = null;

    public SQLiteConexion(@Nullable Context context) {
        super(context, NameDB, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Transacciones.CreateTableClient);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Transacciones.DROPTableClient);
        onCreate(db);
    }
}
