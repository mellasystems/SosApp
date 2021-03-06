package ado.edu.itla.sosapp.repositorio.solicitud;

import java.util.List;

import ado.edu.itla.sosapp.entity.Solicitud;
import ado.edu.itla.sosapp.entity.Usuario;

public interface SolicitudRepositorio {

    public void guardar(Solicitud solicitud);
    public List<Solicitud> buscarSolicitudesPor(Usuario usuario);
    public List<Solicitud> buscarSolicitudesAfinesA(Usuario usuario);
}
