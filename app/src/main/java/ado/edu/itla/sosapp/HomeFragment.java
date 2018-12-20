package ado.edu.itla.sosapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

import ado.edu.itla.sosapp.entity.Solicitud;
import ado.edu.itla.sosapp.repositorio.solicitud.SolicitudRepositorio;
import ado.edu.itla.sosapp.repositorio.solicitud.SolicitudRepositorioImpl;
import ado.edu.itla.sosapp.repositorio.usuario.UsuarioRepositorio;


/**as
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "SosApp.HOMEACTIVITY";

    private SolicitudRepositorio solicitudRepositorio;

    //Floating Button
    private FloatingActionButton btnFabMain;

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_home, container, false);


        TextView tvTitulo = myview.findViewById(R.id.title_txtsolicitudes);
        TextView tvEstado = myview.findViewById(R.id.estado_txtsolicitudes);
        TextView tvUsuario = myview.findViewById(R.id.usuario_txtsolicitudes);
        TextView tvLetreroUsuario = myview.findViewById(R.id.usuario_txtsolicitudes);
        TextView tvFechaHora = myview.findViewById(R.id.fecha_txtsolicitudes);

        Intent intent = getIntent(); // gets the previously created intent
        final int idSolicitud = intent.getIntExtra("idSolicitud", 0);
        final int idUsuario = intent.getIntExtra("idUsuario", 0);


        // Conectando el Boton flotante con el Layout
        btnFabMain = myview.findViewById(R.id.fab_main_plus);
        btnFabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment newCase = new NewSolicitudFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame,newCase); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });

        return myview;

    }


}
