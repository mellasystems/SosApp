package ado.edu.itla.sosapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ado.edu.itla.sosapp.entity.Usuario;
import ado.edu.itla.sosapp.repositorio.Session;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorio;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorioImpl;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SosApp.MAINACTIVITY";

    private UsuarioRepositorio usuarioRepositorio;

    TextView btnRegistrar;
    Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Entrando a SosApp");

        Session session = new Session(this);
        String nombreUsuario = session.get("usuario");

        usuarioRepositorio = new UsuarioRepositorioImpl(this);

        final ProgressDialog mDialog = new ProgressDialog(this);

        btnRegistrar = findViewById(R.id.login_register);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(register);
            }
        });

        btnIniciar = (Button)findViewById(R.id.login_login);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText textEmail = findViewById(R.id.login_email);
                final EditText textPassword = findViewById(R.id.login_password);

                try{


                    Usuario user = usuarioRepositorio.buscar(textEmail.getText().toString().trim());

                    boolean validado = usuarioRepositorio.validarEmail(textEmail.getText().toString().trim());

                    if(!validado){
                        textEmail.setError("Formato de email invalido");
                        if (textEmail.getText().toString().trim().isEmpty()){
                            textEmail.setError("Campo email requerido");
                        }
                    }else{
                        if(user != null){
                            if(textPassword.getText().toString().equals(user.getPassword())){
                                Session session = new Session(view.getContext());
                                session.set("usuario", user.getEmail());
                                session.set("id_usuario", String.valueOf(user.getId()));
                                mDialog.setMessage("Entrando...");
                                mDialog.show();
                                Intent acceso = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(acceso);

                            }else{
                                Toast.makeText(getApplicationContext(), "Email y/o Password incorrectos",
                                        Toast.LENGTH_SHORT).show();
                                textPassword.setText("");
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Email no registrado",
                                    Toast.LENGTH_SHORT).show();
                            textPassword.setText("");
                            textEmail.requestFocus();

                        }
                    }


                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
