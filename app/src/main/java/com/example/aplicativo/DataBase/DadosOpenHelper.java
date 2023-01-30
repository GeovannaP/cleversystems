package com.example.aplicativo.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DadosOpenHelper extends SQLiteOpenHelper {

    public DadosOpenHelper(Context context) {

        super(context, "DADOS", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    db.execSQL(ScriptDLL.getCreateTableCliente());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
