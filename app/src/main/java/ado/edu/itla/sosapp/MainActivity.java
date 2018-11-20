package ado.edu.itla.sosapp;

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
        Log.e(TAG, "Esto es un error");

        usuarioRepositorio = new UsuarioRepositorioImpl(this);

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
                    Cursor userCursor = usuarioRepositorio.checkUser
                            (textEmail.getText().toString(),textPassword.getText().toString());
                    if (userCursor.getCount() > 0){
                        Intent acceso = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(acceso);
                    }else{
                        Toast.makeText(getApplicationContext(), "Email y/o Password incorrectos",
                                Toast.LENGTH_SHORT).show();
                    }
                    textEmail.setText("");
                    textPassword.setText("");
                    textEmail.requestFocus();
                }catch (SQLException e){
                    e.printStackTrace();
                }


            }
        });

    }
}
