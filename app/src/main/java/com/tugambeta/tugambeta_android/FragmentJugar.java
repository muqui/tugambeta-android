package com.tugambeta.tugambeta_android;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tugambeta.model.Car;
import com.tugambeta.model.Jugador;
import com.tugambeta.model.Partidos;
import com.tugambeta.model.Quiniela;

import org.json.JSONObject;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentJugar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentJugar extends Fragment implements View.OnClickListener {
    int width = 0;
    Button buttonJugar;
    RadioGroup radioGroup;
    RadioButton radioButton;
    LinearLayout linearMain;
    EditText editTextNombreJugador;
    ProgressDialog pd;
    Button botonJugar;
    int localID = 0;
    int visitaID = 0;
    private Context mContext;
    Jugador jugador;
    Quiniela quiniela;
    List<Partidos> partidos = new LinkedList<Partidos>();
    List <Partidos> listaPartidos =  new LinkedList<Partidos>();
    List<RadioGroup> listRadioGroup = new ArrayList<RadioGroup>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentJugar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentJugar.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentJugar newInstance(String param1, String param2) {
        FragmentJugar fragment = new FragmentJugar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            partidos = ( List<Partidos>) getArguments().getSerializable("partidos");



        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jugar, container, false);
        WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        String userName;
        width = size.x - 40;
        Log.i("ancho ", "" + width);
       // pd = new ProgressDialog(mContext);
        linearMain = (LinearLayout) view.findViewById(R.id.linearMain);
        buttonJugar = (Button) view.findViewById(R.id.buttonJugar);
        editTextNombreJugador = (EditText) view.findViewById(R.id.editTextNombreJugador);
        buttonJugar.setOnClickListener(this);
        mContext = view.getContext();

        juegos();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonJugar:
                if("".equals(editTextNombreJugador.getText().toString().trim())){
                    Toast.makeText(getActivity(), "Capture nombre!", Toast.LENGTH_LONG).show();
                }
                else{ //inserta jugador
                    buttonJugar.setEnabled(false);
                    jugador = new Jugador();
                    String nombre = editTextNombreJugador.getText().toString();
                    listaPartidos = new LinkedList<Partidos>();
                    jugador.setJugador(nombre);
                    quiniela = partidos.get(0).getQuiniela();


                    for (int i = 0; i < partidos.size(); i++) {
                        Partidos p = partidos.get(i);

                        //  p.setResultado("G");
                        Log.i("ID quiniela" , ""+quiniela.getIdquiniela());
                        int selectedId = listRadioGroup.get(i).getCheckedRadioButtonId();
                        View radioButton = listRadioGroup.get(i).findViewById(selectedId);
                        int radioId = listRadioGroup.get(i).indexOfChild(radioButton);
                        RadioButton btn = (RadioButton) listRadioGroup.get(i).getChildAt(radioId);
                        String selection = (String) btn.getText();
                        if(selection.equalsIgnoreCase(p.getLocal())){


                            p.setResultado("G");

                        }else
                        if(selection.equalsIgnoreCase(p.getVisitante())){

                            p.setResultado("P");
                        }
                        else{

                            p.setResultado("E");
                        }


                        Log.i("texto" , ""+ selection);
                        listaPartidos.add(p);
                    }

                    new FragmentJugar.HttpRequestTask().execute();
                    break;
                }//Fin inserta jugador

        }
    }

    public void juegos(){
        for (int i = 0; i < partidos.size(); i++) {
            Partidos p = partidos.get(i);
            Log.i("ID partido ", "" + p.getIdpartidos());
            radioGroup = new RadioGroup(mContext);
            radioGroup.setLayoutParams(new RadioGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            listRadioGroup.add(radioGroup);
            radioGroup.setOrientation(LinearLayout.HORIZONTAL);
            radioGroup.setId(i);
            //radioButton Local
            radioButton = new RadioButton(mContext);
            radioButton.setText(p.getLocal());
            if (isAdded() && mContext != null) { //SOLUCION TEMPORAL  not attached to ActivitY
                localID  = getResources().getIdentifier(nombreImagen(p.getLocal()) , "drawable", mContext.getPackageName());
                if(localID > 0){
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, localID, 0);
                }
                else{
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.sin_escudo, 0);
                }
            }



            radioButton.setCompoundDrawablesWithIntrinsicBounds(localID, 0, 0, 0);
            radioButton.setButtonDrawable(R.drawable.radio_button_custom);
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(width / 3, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            radioGroup.addView(radioButton);
            radioButton.setChecked(true);
            //radioButton Empate
            radioButton = new RadioButton(mContext);
            radioButton.setButtonDrawable(R.drawable.radio_button_custom);
            RadioGroup.LayoutParams paramsEmpate = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            paramsEmpate.setMargins(50, 12, 0, 12);
            radioButton.setText("");
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setLayoutParams(paramsEmpate);
            radioGroup.addView(radioButton);
            //radioButton Visita
            radioButton = new RadioButton(mContext);
            radioButton.setText(p.getVisitante());
            if (isAdded() && mContext != null) {  //SOLUCION TEMPORAL  not attached to Activity
                visitaID = getResources().getIdentifier(nombreImagen(p.getVisitante()) , "drawable", mContext.getPackageName());
                if(visitaID > 0){
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, visitaID, 0);
                }
                else{
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.sin_escudo, 0);
                }
                Log.i("idImagen", "" + visitaID);
            }
            radioButton.setButtonDrawable(R.drawable.radio_button_custom);
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(width / 3, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            radioButton.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            radioGroup.addView(radioButton);
            linearMain.addView(radioGroup);

        }


    }

    private String nombreImagen(String nombre){
        String compatible;
        compatible = nombre.replace(" ", "_");
        compatible = compatible.toLowerCase();

        return compatible;
    }

    public class HttpRequestTask extends AsyncTask<Void, Void, Jugador> {
        List<Partidos> pars ;
        private Context mContext;

        HttpStatus statusCode;

        public HttpRequestTask(Context context) { // este constructor permite usar un Toast
            mContext = context;
        }

        public HttpRequestTask() {

        }


        @Override
        protected Jugador doInBackground(Void... params) {


            try {

                String username = "ligamx";
                   String password = "12345678";
                  HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);

                //setting up the request headers
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                requestHeaders.setAuthorization(authHeader);

               // String getUrl = "http://192.168.56.1:8080/car";
              //  String getUrl = "http://192.168.56.1:8080/grupo/jugar/"+ username;
                String getUrl = "http://tugambeta-rest.herokuapp.com/grupo/jugar/"+ username;

                try {
                    ObjectMapper mapper = new ObjectMapper();

                    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    Quiniela q  = new Quiniela();
                    q.setIdquiniela(quiniela.getIdquiniela());

                    pars  = new ArrayList<Partidos>();
                    Log.i("jugados", ""+pars.size());
                    Log.i("jugados List", ""+listaPartidos.size());
                    for(  Partidos pa :listaPartidos){
                       //

                        Partidos p = new Partidos();
                         p.setResultado(pa.getResultado());
                         p.setIdpartidos(pa.getIdpartidos());
                         p.setLocal(pa.getLocal());
                        p.setVisitante(pa.getVisitante());
                        pars.add(p);

                    }
                  //  pars.add(p);
                    Log.i("jugados", ""+pars.size());
                    q.setPartidoses(pars);
                    Jugador j = new Jugador();

                    j.setQuiniela(q);
                    j.setJugador(editTextNombreJugador.getText().toString());
                    j.setAciertos(0);
                    String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(j);

                    Log.i("To jason ", json);
                    HttpEntity<?> requestEntity = new HttpEntity<>(j, requestHeaders);
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    ResponseEntity<?> responseEntity = restTemplate.exchange(getUrl, HttpMethod.POST, requestEntity, Jugador.class);

                } catch (Exception e) {
                    editTextNombreJugador.setText("");
                    Log.i(" Erorr To jason ", ""+  e);
                }







            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }


            return null;
        }

        @Override
        protected void onPostExecute(Jugador jugador) {
            pars  = new ArrayList<Partidos>();
            editTextNombreJugador.setText("");
            buttonJugar.setEnabled(true);
            Log.i(" tama finish ", ""+  pars.size());
            confirmDialog();

        }
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder
                .setMessage("Registro exitoso!")
                .setPositiveButton("OK",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Yes-code
                    }
                })

                .show();
    }

}
