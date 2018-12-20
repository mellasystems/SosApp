package ado.edu.itla.sosapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ado.edu.itla.sosapp.entity.AreaAfin;
import ado.edu.itla.sosapp.entity.Solicitud;
import ado.edu.itla.sosapp.repositorio.DbConexion;
import ado.edu.itla.sosapp.repositorio.Session;
import ado.edu.itla.sosapp.repositorio.areaafin.AreaAfinRepositorio;
import ado.edu.itla.sosapp.repositorio.areaafin.AreaAfinRepositorioImpl;
import ado.edu.itla.sosapp.repositorio.solicitud.SolicitudRepositorio;
import ado.edu.itla.sosapp.repositorio.solicitud.SolicitudRepositorioImpl;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorio;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorioImpl;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewSolicitudFragment extends Fragment {


    private static final String TAG = "SosApp.HOMEACTIVITY";


    private Button btnCancelar;
    private Button btnGuardar;
    private EditText txtTitulo;
    private EditText txtDescripcion;
    private String estado;
    private int fecha;
    private Spinner spAreaAfin;

    private AreaAfinRepositorio areaAfinRepositorio = new AreaAfinRepositorioImpl(getContext());
    private SolicitudRepositorio solicitudRepositorio;
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview  = inflater.inflate(R.layout.fragment_solicitudes, container, false);

        //Llenar el spiner
        spAreaAfin = myview.findViewById(R.id.sp_area_new);

        areaAfinRepositorio = new AreaAfinRepositorioImpl(getContext());

        List<AreaAfin> allAreaAfines = areaAfinRepositorio.getAllAreaAfines();
        spAreaAfin.setAdapter(new ArrayAdapter<AreaAfin>(getContext(), android.R.layout.simple_list_item_1, allAreaAfines));
        //----------------------------------------------------------

        //Usuario
        final Session session = new Session(getContext());
        final int idUsuario = Integer.parseInt(session.get("id_usuario"));

        btnCancelar = myview.findViewById(R.id.btn_cancel_new);
        btnGuardar = myview.findViewById(R.id.btn_guardar_new);
        txtTitulo = myview.findViewById(R.id.titulo_issues);
        txtDescripcion = myview.findViewById(R.id.descripcion_issues);
        estado = "Pendiente";

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                solicitudRepositorio = new SolicitudRepositorioImpl(getContext());
                usuarioRepositorio = new UsuarioRepositorioImpl(getContext());
                areaAfinRepositorio = new AreaAfinRepositorioImpl(getContext());

                String titulo = txtTitulo.getText().toString().trim();
                String descripcion = txtDescripcion.getText().toString().trim();

                Solicitud solicitud = new Solicitud();
                AreaAfin areaAfin = (AreaAfin) spAreaAfin.getSelectedItem();
                solicitud.setAreaAfin(areaAfin);
                solicitud.setTitle(titulo);
                solicitud.setDescription(descripcion);
                solicitud.setOnCreate(new Date());
                solicitud.setState(estado);
                solicitud.setUsuarioSolicitante(idUsuario);

                solicitudRepositorio.guardar(solicitud);
                Log.i(TAG, solicitud.toString());

                Toast.makeText(getContext(), "Solicitud agregada con exito!",
                        Toast.LENGTH_SHORT).show();

                Fragment newCase = new HomeFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame,newCase); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });



        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newCase = new HomeFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame,newCase); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        return myview;

    }

}