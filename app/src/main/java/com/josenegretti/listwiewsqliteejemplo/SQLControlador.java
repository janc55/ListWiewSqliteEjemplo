package com.josenegretti.listwiewsqliteejemplo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLControlador {

    private DBHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public void insertarDatos(String name) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.MIEMBRO_NOMBRE, name);
        database.insert(DBHelper.TABLE_MEMBER, null, cv);
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                DBHelper.MIEMBRO_ID,
                DBHelper.MIEMBRO_NOMBRE
        };
        Cursor c = database.query(DBHelper.TABLE_MEMBER, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int actualizarDatos(long memberID, String memberName) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBHelper.MIEMBRO_NOMBRE, memberName);
        int i = database.update(DBHelper.TABLE_MEMBER, cvActualizar,
                DBHelper.MIEMBRO_ID + " = " + memberID, null);
        return i;
    }

    public void deleteData(long memberID) {
        database.delete(DBHelper.TABLE_MEMBER, DBHelper.MIEMBRO_ID + "="
                + memberID, null);
    }
}