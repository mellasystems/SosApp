package ado.edu.itla.sosapp.repositorio.areaafin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.sosapp.ConfigurationFragment;
import ado.edu.itla.sosapp.entity.AreaAfin;
import ado.edu.itla.sosapp.repositorio.DbConexion;

public class AreaAfinRepositorioImpl implements AreaAfinRepositorio {

    private DbConexion conexion;

    public AreaAfinRepositorioImpl(Context context) {
        this.conexion = new DbConexion(context);
    }

    @Override
    public void guardar(AreaAfin areaAfin) {

        //
        ContentValues cv = new ContentValues();

        cv.put("area_name", areaAfin.getNombre());
        cv.put("area_descripcion", areaAfin.getDescripcion());

        //TODO: guardar AreaAfin
        SQLiteDatabase db = conexion.getWritableDatabase();
        Long id = db.insert("area_afin",null,cv);
        areaAfin.setId(id.intValue());
        db.close();
    }

    @Override
    public List<AreaAfin> getAllAreaAfines() {
        // array of columns to fetch
        String[] columns = {
                "area_id",
                "area_name",
                "area_descripcion",
        };

        // sorting orders
        String sortOrder = "area_id ASC";
        List<AreaAfin> areaAfinList = new ArrayList<AreaAfin>();

        SQLiteDatabase db = conexion.getReadableDatabase();

        // query the user table
        Cursor cursor = db.query("area_afin", //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AreaAfin areaAfin = new AreaAfin();
                areaAfin.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("area_id"))));
                areaAfin.setNombre(cursor.getString(cursor.getColumnIndex("area_name")));
                areaAfin.setDescripcion(cursor.getString(cursor.getColumnIndex("area_descripcion")));
                // Adding user record to list
                areaAfinList.add(areaAfin);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return areaAfinList;
    }
}
