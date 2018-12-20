package ado.edu.itla.sosapp.repositorio.areaafin;

import java.util.List;

import ado.edu.itla.sosapp.entity.AreaAfin;
import ado.edu.itla.sosapp.entity.Usuario;

public interface AreaAfinRepositorio {

    void guardar(AreaAfin areaAfin);
    List<AreaAfin> getAllAreaAfines();

}
