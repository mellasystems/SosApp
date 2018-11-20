package ado.edu.itla.sosapp.repositorio.usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.sosapp.entity.Usuario;
import ado.edu.itla.sosapp.repositorio.DbConexion;

public class UsuarioRepositorioImpl implements UsuarioRepositorio{

    private DbConexion conexion;

    public UsuarioRepositorioImpl(Context context) {
        this.conexion = new DbConexion(context);
    }

    @Override
    public void guardar(Usuario usuario) {

        //
        ContentValues cv = new ContentValues();
        cv.put("user_email", usuario.getEmail());
        cv.put("user_password", usuario.getPassword());
        cv.put("user_name", usuario.getNombre());

        //TODO: guardar usuario
        SQLiteDatabase db = conexion.getWritableDatabase();
        Long id = db.insert("user",null,cv);
        usuario.setId(id.intValue());
        //db.close();

    }

    @Override
    public Usuario buscar(String email) {
        return null;
    }


    public Cursor checkUser(String email, String password) throws SQLException {

        Cursor mCursor = null;

        mCursor = conexion.getReadableDatabase().query("user", new String[]{"user_id",
                "user_email", "user_password"}, "user_email LIKE '" + email + "' " +
                "AND user_password LIKE '" + password +"'", null, null, null,null);

        return mCursor;
    }

    @Override
    public Cursor validateEmail(String email) {

        Cursor mCursor = null;

        mCursor = conexion.getReadableDatabase().query("user", new String[]{"user_id",
                "user_email"}, "user_email LIKE '" + email + "' ", null, null, null,null);

        return mCursor;
    }

    public List<Usuario> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                "user_id",
                "user_email",
                "user_name",
        };

        // sorting orders
        String sortOrder =
                "user_id" + " ASC";
        List<Usuario> userList = new ArrayList<Usuario>();

        SQLiteDatabase db = conexion.getReadableDatabase();

        // query the user table
        Cursor cursor = db.query("user", //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Usuario user = new Usuario();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("user_id"))));
                user.setNombre(cursor.getString(cursor.getColumnIndex("user_name")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("user_email")));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

}
