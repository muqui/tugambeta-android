package com.tugambeta.tugambeta_android;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tugambeta.model.Partidos;
import com.tugambeta.model.Users;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JugadorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentParticipantes.OnFragmentInteractionListener {
    Users user;
    String username;
    String password;
    RadioGroup radioGroup;
    RadioButton radioButton;
    LinearLayout linearMain;
    List<Partidos> partidos;
    List<RadioGroup> listRadioGroup = new ArrayList<RadioGroup>();
    String getUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugador);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        //datos recibidos del MainActivity.
        Bundle bundle = getIntent().getExtras();
        partidos = (List<Partidos>) bundle.getSerializable("partidos");

//        user = (Users) bundle.getSerializable("user");
        Log.i("userx", partidos.get(0).getQuiniela().getNombre());

        //insertar fragment
        Fragment fragment = new FragmentJugar();
        Bundle bundleFragment = new Bundle();
        bundleFragment.putSerializable("partidos", (Serializable)partidos);
        fragment.setArguments(bundleFragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_jugador,fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
        getMenuInflater().inflate(R.menu.jugador, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean fragmentTransaction = false;
        Fragment fragment = null;
        if (id == R.id.nav_jugar) {
            fragment = new FragmentJugar();
            Bundle bundleFragment = new Bundle();
            bundleFragment.putSerializable("partidos", (Serializable)partidos);
            fragment.setArguments(bundleFragment);
            fragmentTransaction = true;
        } else if (id == R.id.nav_participantes) {
            fragment = new FragmentParticipantes();
            fragmentTransaction = true;

        } else if (id == R.id.nav_salir) {
            System.exit(0);
        }
        if(fragmentTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_jugador,fragment).commit();
            item.setCheckable(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

   //fin
}
