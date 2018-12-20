package ado.edu.itla.sosapp.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbConexion extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "sosapp.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_AREA = "area_afin";
    private static final String TABLE_SOLICITUD = "solicitud";

    // create usuario sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_name TEXT,"
            + "user_email TEXT," +
            "user_password TEXT)";

    // create Area a Fin sql query
    private String CREATE_AREAAFIN = "CREATE TABLE " + TABLE_AREA + "("
            + "area_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "area_name TEXT,"
            + "area_descripcion TEXT)";

    // create Solicitud
    private String CREATE_SOLICITUD = "CREATE TABLE "+ TABLE_SOLICITUD + "("+ "solicitud_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "solicitud_fecha NUMERIC,"+
            "solicitud_description TEXT,"+
            "solicitud_title TEXT,"+
            "solicitud_state TEXT,"+
            "areaafin INTEGER,"+
            "usuario_solicitante INTEGER,"+
            "usuario_asignado INTEGER)";

    //Constructor
    public DbConexion(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_AREAAFIN);
        db.execSQL(CREATE_SOLICITUD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion == 1){
            db.execSQL("DELETE FROM area_afin WHERE area_id = 1");
        }
    }


}
