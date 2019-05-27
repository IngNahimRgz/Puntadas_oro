package com.example.arrec.navigation_view.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arrec.navigation_view.R;
import com.example.arrec.navigation_view.fragments.Fragment1;
import com.example.arrec.navigation_view.fragments.Fragment2;
import com.example.arrec.navigation_view.fragments.Fragment3;
import com.example.arrec.navigation_view.fragments.Fragment4;
import com.example.arrec.navigation_view.settings.SettingsActivity;

/**
 * Tenemos la clase Principal, a la cual le tenemos que indicar que implemente los metodos
 * OnFragmentInteractionListener, debido a que la aplicacion unicamente tendra un activity,
 * el dise;o de la vista sera hecha por fragmentos
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Fragment1.OnFragmentInteractionListener,
        Fragment2.OnFragmentInteractionListener,
        Fragment3.OnFragmentInteractionListener,
        Fragment4.OnFragmentInteractionListener {

    /**
     * @Variables_Globales txtView, navigationView;
     * el TextView sera la etiqueta que cambiara de texto cuando se presione el boton
     * y el NavigationView es donde mostraremos el menu
     */
    TextView txtEtiqueta;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_main);

        // -----------------------Vinculacion de los elementos de la interfaz------------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);

        TextView userName = (TextView) hView.findViewById(R.id.UserName_navView);
        TextView email = (TextView) hView.findViewById(R.id.Email_NavView);
        //--------------------Fin de la vinculacion ----------------------------------------------------------------------------------------


        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        email.setText(emailFromIntent);


        //userName.setText(preferences.getString("user_name", "Name")); // Mandamos los valores guardados en las preferencias al navigation
        // email.setText(preferences.getString("email", "null"));

        navigationView.setNavigationItemSelectedListener(this); // asiganmos el metodo onClick al navigationView
        navigationView.setCheckedItem(R.id.nav_unidad1); // hacemos que por default el primer elemento este seleccionado

        Fragment fragment = new Fragment1();
        getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor, fragment).commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu); // inflamos el menu, el que se mostrara a la derecha y que contendra las opciones de "Shared Preferences" y la opcion de salir
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class); // abre el menu de configuracion
            startActivity(intent);

            return true;
        }
        if (id == R.id.action_exit) {
            finish(); // termina la aplicacion
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Este metodo se sobreescribe al usar el navigationView,
     *
     * @param item recibe el item seleccionado, y lo usamos para identificar cual item fue seleccionado y una vez identificamos cual fue,
     *             creamos el fragment de la actividad.
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        boolean fragmentTransition = false;

        if (id == R.id.nav_unidad1) {
            // Handle the camera action
            fragment = new Fragment1();
            fragmentTransition = true;

        } else if (id == R.id.nav_unidad2) {
//            fragment = new Fragment2();
//
//            fragmentTransition = true;


        } else if (id == R.id.nav_unidad4) {
//            Intent intentBD = new Intent(getApplicationContext(), UsersListActivity.class);
//            startActivity(intentBD);
        }

        if (fragmentTransition) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor, fragment).commit(); // Una vez seleccionado remplazamos el fragment actual por el nuevo fragment

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * Este metodo onClick se lo asignamos a los botones del primer fragment, con un switch sabemos cual fue el boton presionado y realizamos las siguientes acciones:
     *
     * @param view
     */
    public void onClickButton(View view) {
        Button button = (Button) view;
        txtEtiqueta = (TextView) findViewById(R.id.txtEtiqueta1);

        switch (button.getId()) {
            case R.id.btnLecciones:
                Intent lecciones = new Intent(this, LessonsActivity.class);
                startActivity(lecciones);
                break;


            case R.id.btnIniciarForo:
                Intent foro = new Intent(this, ChatRealTime.class);
                startActivity(foro);
                break;

           /* case R.id.btnMostrarToast:
                Toast.makeText(this, "Aun no disponible :(", Toast.LENGTH_SHORT).show(); //muestra un toast
                break;*/

            /*case R.id.btnMostrarDialogo: // Para mostrar un AlertDialog tenemos que llamar a su constructor, le enviamos el mensaje y un boton OK, este generara un metodo OnClick, pero esta vez no lo utilizaremos
               *//* AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Esto es un Dialogo").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();*//*
                break;*/

           /* case R.id.btnMostrarNotificacion: //Al igual que con el AlertDialog  para mostrar una notificacion tenemos que llamar al Constructor
                *//*NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_perm_device_information_black_24dp)
                        .setContentTitle("Notificacion")
                        .setContentText("Esto es el mensaje de la notificacion ")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(1, mBuilder.build());*//*
                break;*/


        }
    }
}
