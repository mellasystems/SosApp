package ado.edu.itla.sosapp.repositorio.usuario;

import ado.edu.itla.sosapp.entity.Usuario;

public interface UsuarioRepositorio {

    void guardar(Usuario usuario);
    Usuario buscar(String email);
    boolean validarEmail(String email);

}
