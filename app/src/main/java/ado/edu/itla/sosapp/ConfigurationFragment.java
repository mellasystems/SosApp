package ado.edu.itla.sosapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import ado.edu.itla.sosapp.entity.AreaAfin;
import ado.edu.itla.sosapp.repositorio.areaafin.AreaAfinRepositorio;
import ado.edu.itla.sosapp.repositorio.areaafin.AreaAfinRepositorioImpl;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigurationFragment extends Fragment {

    private static final String TAG = "SosApp.CONFIGURATIONFRAGMENT";

    private FloatingActionButton floatingActionButton;

    private AreaAfinRepositorio areaAfinRepositorio;

    private Spinner spAreaAfin;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_configuration, container, false);

        spAreaAfin = myview.findViewById(R.id.spinner2);

        areaAfinRepositorio = new AreaAfinRepositorioImpl(getContext());

        List<AreaAfin> allAreaAfines = areaAfinRepositorio.getAllAreaAfines();
        spAreaAfin.setAdapter(new ArrayAdapter<AreaAfin>(getContext(), android.R.layout.simple_list_item_1, allAreaAfines));

        floatingActionButton = myview.findViewById(R.id.fab_area_plus);

        //Movernos e insertar al Fragment de Areas Afines
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserAreaAfines();
            }
        });

        return myview;

    }

    public void inserAreaAfines() {

        areaAfinRepositorio = new AreaAfinRepositorioImpl(getContext());

        AlertDialog.Builder mydialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater1 = LayoutInflater.from(getActivity());
        View myviewm = inflater1.inflate(R.layout.layout_insert_areaafines, null);
        mydialog.setView(myviewm);
        final AlertDialog dialog = mydialog.create();

        final EditText txtNombre = myviewm.findViewById(R.id.nombre_area);
        final EditText txtDescripcion = myviewm.findViewById(R.id.descripcion_area);

        Button btnCancelar = myviewm.findViewById(R.id.btn_areaafin_cancel);

        Button btnGuardar = myviewm.findViewById(R.id.btn_areaafin);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View view) {

                String nombre = txtNombre.getText().toString().trim();
                String descripcion = txtDescripcion.getText().toString().trim();

                if (TextUtils.isEmpty(nombre)) {
                    txtNombre.setError("Campo nombre requerido...");
                    return;
                }

                AreaAfin areaAfin = new AreaAfin();

                areaAfin.setNombre(nombre);
                areaAfin.setDescripcion(descripcion);

                areaAfinRepositorio.guardar(areaAfin);
                Log.i(TAG, areaAfin.toString());


                Toast.makeText(getContext(), "Area Afin agregada con exito!",
                        Toast.LENGTH_SHORT).show();

                dialog.dismiss();

            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
