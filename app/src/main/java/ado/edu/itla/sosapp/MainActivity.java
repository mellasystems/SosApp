package ado.edu.itla.sosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ado.edu.itla.sosapp.entity.Usuario;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SosApp.MAINACTIVITY";
    TextView btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Entrando a SosApp");
        Log.e(TAG, "Esto es un error");

        btnRegistrar = findViewById(R.id.txt_register);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Usuario usuario= new Usuario();
        usuario.setEmail("juandelospalotes@gmail.com");
        usuario.setNombre("Juan De los Palotes");
        usuario.setUsername("juandelospalotes");

        Usuario usuario1= new Usuario();
        usuario1.setEmail("juanasaltitopa@gmail.com");
        usuario1.setNombre("Juana Saltitopa");
        usuario1.setUsername("juanasaltitopa");

        Usuario usuario2= new Usuario();
        usuario2.setEmail("marialacontenta@gmail.com");
        usuario2.setNombre("Maria Contenta");
        usuario2.setUsername("mariacontenta");

        Usuario usuario3= new Usuario();
        usuario3.setEmail("pedroahivoy@gmail.com");
        usuario3.setNombre("Pedro Martinez");
        usuario3.setUsername("pedromartinez");

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        Log.i(TAG, "Tamano de la lista: " + usuarios.size());

        for(Usuario u : usuarios){
            Log.i(TAG, "Nombre: " + u.getNombre());
        }

    }
}
