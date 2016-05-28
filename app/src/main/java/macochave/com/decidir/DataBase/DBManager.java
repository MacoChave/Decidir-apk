package macochave.com.decidir.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;
import android.util.Log;

import java.util.ArrayList;

import macochave.com.decidir.Categoria;
import macochave.com.decidir.Opcion;

/**
 * Created by MacoChave on 27/05/2016.
 */
public class DBManager {
    private static final String OPCION = "opcion";
    private static final String CATEGORIA = "categoria";
    private static final String[] OP_VALUES = new String[]{"_id", "nombre", "foto", "id_categoria"};
    private static final String[] CT_VALUES = new String[]{"_id", "nombre"};

    private DBHelper helper;
    private SQLiteDatabase database;

    public DBManager (Context context) {
        helper = new DBHelper(context);
        database = helper.getWritableDatabase();
    }

    private ContentValues valuesOp (Opcion opcion) {
        ContentValues values = new ContentValues();
        values.put(OP_VALUES[1], opcion.getOpcion());
        values.put(OP_VALUES[2], opcion.getFoto());
        values.put(OP_VALUES[3], opcion.getIdCategoria());

        return values;
    }

    private ContentValues valuesCT (Categoria categoria) {
        ContentValues values = new ContentValues();
        values.put(CT_VALUES[1], categoria.getNombre());

        return values;
    }

    public ArrayList<Opcion> selectAllOpcion() {
        ArrayList<Opcion> lista = new ArrayList<>();

        Cursor cursor = database.query(OPCION, OP_VALUES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Opcion opcion = new Opcion();
                opcion.setId(cursor.getInt(0));
                opcion.setOpcion(cursor.getString(1));
                opcion.setFoto(cursor.getString(2));
                opcion.setIdCategoria(cursor.getInt(3));
                lista.add(opcion);
            } while (cursor.moveToNext());
        }

        return lista;
    }

    public ArrayList<Categoria> selectAllCategoria() {
        ArrayList<Categoria> lista = new ArrayList<>();

        Cursor cursor = database.query(CATEGORIA, CT_VALUES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Categoria categoria = new Categoria();
                categoria.setId(cursor.getInt(0));
                categoria.setNombre(cursor.getString(1));
                lista.add(categoria);
            } while (cursor.moveToNext());
        }

        return lista;
    }

    public ArrayList<Opcion> selectOpcionCategoria (int id_categoria) {
        ArrayList<Opcion> list = new ArrayList<>();
        String select = "SELECT * FROM " + OPCION + " WHERE " + OP_VALUES[3] + " = " + id_categoria;

        Cursor cursor = database.rawQuery(select, null);

        if(cursor.moveToFirst()) {
            do {
                Opcion opcion = new Opcion();
                opcion.setId(cursor.getInt(0));
                opcion.setOpcion(cursor.getString(1));
                opcion.setFoto(cursor.getString(2));
                opcion.setIdCategoria(cursor.getInt(3));
                list.add(opcion);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public int selectIdOpcion (String nombre, int id_categoria) {
        int id;
        String select = "SELECT " + OP_VALUES[0] + " FROM " + OPCION + " WHERE " + OP_VALUES[1] + " = '" + nombre + "' & " + OP_VALUES[3] + " = " + id_categoria;

        Cursor cursor = database.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }

    public int selectIdCategoria (String nombre) {
        int id;
        String select = "SELECT " + CT_VALUES[0] + " FROM " + CATEGORIA + " WHERE " + CT_VALUES[1] + " = '" + nombre + "'";
        Log.i("Consulta sql", select);

        Cursor cursor = database.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
            return id;
        } else {
            return 0;
        }
    }

    public long insertOpcion (Opcion opcion) {
        return  database.insert(OPCION, OP_VALUES[2], valuesOp(opcion));
    }

    public long insertCategoria (Categoria categoria) {
        return database.insert(CATEGORIA, null, valuesCT(categoria));
    }
}
