package com.tugambeta.tugambeta_android;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonLogin;
    private EditText editTextUser, editTextPassword;
    private TextView textViewRegistro;
    String username;
    String password;
    String getUrl;
    Users user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);
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
                new MainActivity.HttpRequestTask(this).execute();
                break;
        }
    }
    public class HttpRequestTask extends AsyncTask<Void, Void, Users> {
        private Context mContext;

        HttpStatus statusCode;
        public HttpRequestTask(Context context){ // este constructor permite usar un Toast
            mContext = context;
        }

        @Override
        protected   Users doInBackground(Void... params) {
            try {
                 username = editTextUser.getText().toString();
                 password = editTextPassword.getText().toString();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username,password);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                // getUrl = "http://192.168.1.68:8080/rest/user/"+ username;
                 getUrl = "https://tugambeta.herokuapp.com/rest/user/" + username;

                ResponseEntity<Users> entity = restTemplate.exchange(getUrl, HttpMethod.GET, requestEntity, Users.class);
                statusCode = entity.getStatusCode();
                user = entity.getBody();

                Log.d("esttus " , "resul_string"+ statusCode.toString());
                return user;
            } catch (Exception e) {

                Log.e("alberto2 ", "error: " + e);
            }

            return user;
        }
        @Override
        protected void onPostExecute(   Users user) {

            if(user != null){
                Log.d("minombre" , "usuario roles " + user.getUserRoleses().toString());
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
        Intent i = new Intent(this, JugadorActivity.class);
        i.putExtra("user", user);
        startActivity(i);
    }

}
