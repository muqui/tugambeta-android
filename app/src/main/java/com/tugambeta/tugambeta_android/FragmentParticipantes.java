package com.tugambeta.tugambeta_android;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tugambeta.model.Partidos;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentParticipantes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentParticipantes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentParticipantes extends Fragment {
    TextView textView;
    LinearLayout linearParticipantes;
    TableLayout tableLayoutParticipantes;
    private Context mContext;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentParticipantes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentParticipantes.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentParticipantes newInstance(String param1, String param2) {
        FragmentParticipantes fragment = new FragmentParticipantes();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment   AQUI
        View view = inflater.inflate(R.layout.fragment_participantes, container, false);
        WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mContext = view.getContext();
        /*hace referencia al linearParticipantes de fragment_participantes.xml*/
        linearParticipantes = (LinearLayout) view.findViewById(R.id.linearParticipantes);
        new FragmentParticipantes.HttpRequestTask(mContext).execute();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public class HttpRequestTask extends AsyncTask<Void, Void, List<List<String>> > {
        private Context mContext;

        HttpStatus statusCode;

        public HttpRequestTask(Context context) { // este constructor permite usar un Toast
            mContext = context;
        }


        @Override
        protected List<List<String>>doInBackground(Void... params) {

            try {

                String username = "ligamx";
                String password = "12345678";
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                String getUrl = "http://tugambeta-rest.herokuapp.com/grupo/participantes/"+ username;
                // getUrl = "https://tugambeta.herokuapp.com/rest/partidos/" + username;

                 List<List<String>> totalParticipantes = new ArrayList<List<String>>();
                //ParameterizedTypeReference
                ResponseEntity<List> entity = restTemplate.exchange(getUrl, HttpMethod.GET, requestEntity,  List.class  );
                statusCode = entity.getStatusCode();
                entity.getBody();
                List<List<String>> participantes = entity.getBody();
                return participantes;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<List<String>>  partidos) {
/*ESTE FRAGMENT ESTA DISENADO PARA UNA VISTA VERTICAL, POR TAL MOTIVO NO SE MOSTRARAR LOS RESULDATOS DE LOS PARTICIPANTES, SOLO SE MOSTRARA NOMBRE, CODIGO, Y ACIERTOS*/
            int nombre  = 0;  /*almacena la posicion de nombre dentro del arreglo*/
            int codigo= 1; /*Almacena la posicion de codigo dentro del arreglo */
            int aciertos = partidos.get(0).size()-1; /* la cantidad de aciertos esta en la poscion fila del arreglo*/
            TableRow row = null;
            tableLayoutParticipantes = new TableLayout(mContext);
            LinearLayout linearLayoutParticipante = new LinearLayout(mContext);
            linearLayoutParticipante.setOrientation(LinearLayout.HORIZONTAL);

            for(int j = 0; j < partidos.size(); j++){
                row = new TableRow(mContext);
                row.setGravity(Gravity.LEFT);
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                for (int i = 0; i <  partidos.get(j).size(); i++) {
                    //add a new row to the TableLayout



                    String n = partidos.get(j).get(i);
                    Log.i("linea345" , "" + n);
                    textView = new TextView(mContext);
                    textView.setText(n + " x ");
                    textView.setBackgroundResource(R.drawable.border);
                    if(i == nombre || i == codigo || i == aciertos  ){
                        //linearLayoutParticipante.addView(textView);
                        row.addView(textView);
                     //  row.setBackgroundResource(R.drawable.border);

                    }


                }
             //   linearParticipantes.addView(linearLayoutParticipante);
                //add a new line to the TableLayout:

                tableLayoutParticipantes.addView(row);
              //  tableLayoutParticipantes.setBackgroundResource(R.drawable.border);
            }


linearParticipantes.addView(tableLayoutParticipantes);
            }
    }

}
