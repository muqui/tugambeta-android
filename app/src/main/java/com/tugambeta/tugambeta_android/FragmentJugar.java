package com.tugambeta.tugambeta_android;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentJugar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentJugar extends Fragment {

    RadioGroup radioGroup;
    RadioButton radioButton;
    LinearLayout linearMain;
    Button botonJugar;
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jugar, container,false);

        linearMain = (LinearLayout)view.findViewById(R.id.linearMain);
        AsyncTask<Void, Void, List<Partidos>> execute = new HttpRequestTask(view.getContext()).execute();
        return view;
       /*
        View gv = inflater.inflate(R.layout.fragment_jugar, null);
        bindGridview();
        return gv;
*/
    }

    public class HttpRequestTask extends AsyncTask<Void, Void, List<Partidos>> {
        private Context mContext;
        List<Partidos> partidos;
        HttpStatus statusCode;
               public HttpRequestTask(Context context){ // este constructor permite usar un Toast
            mContext = context;
        }



        @Override
        protected  List<Partidos> doInBackground(Void... params) {

            try {
                String username = "ligamx";
                String password = "12345678";
                HttpAuthentication authHeader = new HttpBasicAuthentication(username,password);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                // getUrl = "http://192.168.1.68:8080/rest/user/"+ username;
                String getUrl = "https://tugambeta.herokuapp.com/rest/partidos/" + username;
                ResponseEntity<Partidos[]> entity = restTemplate.exchange(getUrl, HttpMethod.GET, requestEntity,  Partidos[].class);
                statusCode = entity.getStatusCode();
                partidos = Arrays.asList(entity.getBody());

                return partidos;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(  List<Partidos> partidos) {

            for(int i = 0; i < partidos.size(); i++){
                Partidos p = partidos.get(i);
                Log.d("Local " , " " + p.getLocal());


                radioGroup = new RadioGroup(mContext);
                listRadioGroup.add(radioGroup);
                radioGroup.setOrientation(LinearLayout.HORIZONTAL);
                radioGroup.setId(i);
                linearMain.addView(radioGroup);

                radioButton = new RadioButton(mContext);
                radioButton.setText(p.getLocal());
                radioGroup.addView(radioButton);
                radioButton.setChecked(true);

                radioButton = new RadioButton(mContext);
                radioButton.setText("");
                radioGroup.addView(radioButton);


                radioButton = new RadioButton(mContext);
                radioButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                if (ViewCompat.getLayoutDirection(radioButton)== ViewCompat.LAYOUT_DIRECTION_LTR){
                    ViewCompat.setLayoutDirection(radioButton,ViewCompat.LAYOUT_DIRECTION_RTL);
                }else{
                    ViewCompat.setLayoutDirection(radioButton,ViewCompat.LAYOUT_DIRECTION_LTR);
                }
                radioButton.setText(p.getVisitante());
                radioGroup.addView(radioButton);


            }




        }
    }

}
