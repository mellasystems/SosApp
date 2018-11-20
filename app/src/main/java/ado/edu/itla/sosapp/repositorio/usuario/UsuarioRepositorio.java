package ado.edu.itla.sosapp.repositorio.usuario;

import android.database.Cursor;

import ado.edu.itla.sosapp.entity.Usuario;

public interface UsuarioRepositorio {

    void guardar(Usuario usuario);
    Usuario buscar(String email);
    Cursor checkUser(String email, String password);
    Cursor validateEmail(String email);

}
