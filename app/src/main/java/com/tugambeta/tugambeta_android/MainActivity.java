package com.tugambeta.tugambeta_android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tugambeta.model.Jugador;
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
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLogin;
    List<Partidos> partidos;
    private EditText editTextUser, editTextPassword;
    private TextView textViewRegistro;
    String username;
    String password;
    String getUrl;
    Users user = null;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);
         pd = new ProgressDialog(MainActivity.this);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        editTextUser = (EditText) findViewById(R.id.editTextUsuario);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                super.onStart();
                pd.setMessage("loading");
                pd.show();
                new MainActivity.HttpRequestTask(this).execute();
                break;
        }
    }
    public class HttpRequestTask extends AsyncTask<Void, Void, List<Partidos>> {
        private Context mContext;

        HttpStatus statusCode;

        public HttpRequestTask(Context context) { // este constructor permite usar un Toast
            mContext = context;
        }


        @Override
        protected List<Partidos> doInBackground(Void... params) {

            try {

                String username = "ligamx";
                String password = "12345678";
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                String getUrl = "http://tugambeta-rest.herokuapp.com/grupo/partidos/"+ username;
               // getUrl = "https://tugambeta.herokuapp.com/rest/partidos/" + username;
                ResponseEntity<Partidos[]> entity = restTemplate.exchange(getUrl, HttpMethod.GET, requestEntity, Partidos[].class);
                statusCode = entity.getStatusCode();
                partidos = Arrays.asList(entity.getBody());

                return partidos;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Partidos> partidos) {

            if(partidos != null){
                for (int i = 0; i < partidos.size(); i++) {
                    Partidos p = partidos.get(i);

                   Log.i("coron1", p.getQuiniela().getIdquiniela().toString() + "id partido ");
                }

                game();
            }
            else

                mostrar();



        }
    }

    private void mostrar() {
        Toast.makeText(MainActivity.this, "mensaje: " + getUrl, Toast.LENGTH_LONG).show();
    }
    private void game() {
        FragmentJugar fragmentJugar = new FragmentJugar();
        Intent i = new Intent(this, JugadorActivity.class);
        i.putExtra("partidos", (Serializable) partidos);
        startActivity(i);
    }





}
