package ado.edu.itla.sosapp.repositorio.solicitud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import ado.edu.itla.sosapp.entity.Solicitud;
import ado.edu.itla.sosapp.entity.Usuario;
import ado.edu.itla.sosapp.repositorio.DbConexion;

public class SolicitudRepositorioImpl implements SolicitudRepositorio {

    private DbConexion conexion;
    public SolicitudRepositorioImpl(Context context) {
        this.conexion = new DbConexion(context);
    }

    @Override
    public void guardar(Solicitud solicitud) {
        //
        ContentValues cv = new ContentValues();

        cv.put("solicitud_fecha", solicitud.getOnCreate().getTime());
        cv.put("solicitud_description", solicitud.getDescription());
        cv.put("solicitud_title", solicitud.getTitle());
        cv.put("solicitud_state",solicitud.getState());
        cv.put("solicitud_areaafin",solicitud.getAreaAfin().getId());
        cv.put("usuario_solicitante",solicitud.getUsuarioSolicitante());
        cv.put("usuario_asignado",solicitud.getUsuarioAsignado());


        //TODO: guardar solicitud
        SQLiteDatabase db = conexion.getWritableDatabase();
        Long id = db.insert("solicitud",null,cv);
        solicitud.setId(id.intValue());
        db.close();
    }

    @Override
    public List<Solicitud> buscarSolicitudesPor(Usuario usuario) {

        return null;
    }

    @Override
    public List<Solicitud> buscarSolicitudesAfinesA(Usuario usuario) {

        return null;
    }

}
