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

                Usuario user = new Usuario();
                user.setNombre(textName.getText().toString());
                user.setEmail(textEmail.getText().toString());
                user.setPassword(textPassword.getText().toString());

                try{
                    Cursor userCursor = usuarioRepositorio.validateEmail
                            (textEmail.getText().toString());
                    if (userCursor.getCount() > 0){
                        Toast.makeText(getApplicationContext(), "El email ya esta en uso!",
                                Toast.LENGTH_SHORT).show();
                    }else if(textPassword.getText().toString() == textConfirmPassword.getText().toString()){

                        Log.i(TAG, user.toString());
                        usuarioRepositorio.guardar(user);

                        if(user.getId() > 0){
                            Toast.makeText(RegisterActivity.this, "Usuario registrado con exito",
                                    Toast.LENGTH_SHORT).show();
                        }

                        textName.setText("");
                        textEmail.setText("");
                        textPassword.setText("");
                        textConfirmPassword.setText("");
                        textEmail.requestFocus();

                        //volver al login
                        Intent login = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(login);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password no Coinciden",
                                Toast.LENGTH_SHORT).show();
                        textPassword.setText("");
                        textConfirmPassword.setText("");
                        textPassword.requestFocus();
                    }

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
