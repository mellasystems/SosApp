package ado.edu.itla.sosapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private boolean viewIsAtHome;

    //Fragments
    private HomeFragment homeFragment;
    private ConfigurationFragment configurationFragment;
    private NewSolicitudFragment newSolicitudFragment;
    private SolicitudFragment solicitudFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("SosApp");

        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        NavigationView navigationView = findViewById(R.id.naView);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedListener(R.id.dashboard);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) { //if the current view is not the News fragment
            displaySelectedListener(R.id.dashboard); //display the News fragment
        } else {
            moveTaskToBack(true);  //If view is in News fragment, exit application
        }
    }

    public void displaySelectedListener(int itemId){

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (itemId){
            case R.id.dashboard:
                fragment = new HomeFragment();
                title = getString(R.string.app_name) + " / Inicio";
                viewIsAtHome = true;
                break;

            case R.id.solicitudes:
                fragment = new SolicitudFragment();
                title = getString(R.string.app_name) + " / Otras Solicitudes";
                viewIsAtHome = false;
                break;

            case R.id.missolicitudes:
                fragment = new NewSolicitudFragment();
                title = getString(R.string.app_name) + " / Nueva Solicitud";
                viewIsAtHome = false;
                break;

            case R.id.configuracion:
                fragment = new ConfigurationFragment();
                title = getString(R.string.app_name) + " / Configuracion";
                viewIsAtHome = false;
                break;

        }
        //
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        displaySelectedListener(menuItem.getItemId());
        return true;
    }

}
