package ado.edu.itla.sosapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ado.edu.itla.sosapp.entity.Usuario;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorio;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorioImpl;

public class RegisterActivity extends AppCompatActivity{

    private UsuarioRepositorio usuarioRepositorio;

    private static final String TAG = "SosApp.REGISTERACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usuarioRepositorio = new UsuarioRepositorioImpl(this);

        final EditText textName = findViewById(R.id.name_reg);
        final EditText textEmail = findViewById(R.id.email_reg);
        final EditText textPassword = findViewById(R.id.password_reg);
        final EditText textConfirmPassword = findViewById(R.id.confpassword_reg);

        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmail.getText().toString().trim();
                String name = textName.getText().toString().trim();
                String password = textPassword.getText().toString().trim();
                String confPassword = textConfirmPassword.getText().toString().trim();

                Usuario user = new Usuario();

                user.setNombre(name);
                user.setEmail(email);
                user.setPassword(password);

                try{

                    if(TextUtils.isEmpty(email)){
                        textEmail.setError("Campo email requerido...");
                        return;
                    }
                    if(TextUtils.isEmpty(name)){
                        textName.setError("Campo nombre requerido...");
                        return;
                    }



//                    user = usuarioRepositorio.buscar(textEmail.getText().toString().trim());
//
//                    if(user != null){

                        usuarioRepositorio.guardar(user);

                        Log.i(TAG, user.toString());

                        Toast.makeText(RegisterActivity.this, "Usuario registrado con exito",
                                Toast.LENGTH_SHORT).show();

                            //volver al login
                                Intent login = new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(login);

                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
        });

        final TextView btnLoginLink = findViewById(R.id.txt__login_register);
        btnLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(login);
            }
        });
    }

}
