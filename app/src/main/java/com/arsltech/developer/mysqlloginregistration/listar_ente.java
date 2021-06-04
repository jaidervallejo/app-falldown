package com.arsltech.developer.mysqlloginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.arsltech.developer.mysqlloginregistration.logica.Ente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class listar_ente extends AppCompatActivity {

    private ArrayList<Ente> ente;
Button bt1;

    private ArrayAdapter<String> adapter;

    Spinner spinner2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ente);
        String url = "https://diamantess.000webhostapp.com/pruebaLogin/Listar.php";


        ente= new ArrayList<>();
bt1=findViewById(R.id.button);
        spinner2 = (Spinner) findViewById(R.id.spinner);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);


        Bundle informacion = this.getIntent().getExtras();
       String v1 = informacion.getString("Error3");

        String url2="https://diamantess.000webhostapp.com/conexionapp/listar_ente.php?variable1="+v1;
///////////////////////////////////////////////


        JsonArrayRequest json = new JsonArrayRequest(url2, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;


                for (int i = 0; i < response.length(); i++) {

                    try {


                        jsonObject = response.getJSONObject(i);
                        String id_registro2 = jsonObject.getString("id_registro2");
                        String nombre = jsonObject.getString("nombre");
                        String identificacion = jsonObject.getString("identificacion");
                        String telefono = jsonObject.getString("telefono");

                        String id= jsonObject.getString("id");


                            ente.add(new Ente(Integer.parseInt(id),nombre,telefono,identificacion,id_registro2));

                            adapter.add(nombre+" "+identificacion);





                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue req = Volley.newRequestQueue(this);
        req.add(json);

        spinner2.setAdapter(adapter);

        ///////////////////////////////////////7




        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int idaux=spinner2.getSelectedItemPosition();

                 // Toast.makeText(getApplicationContext(),ente.get(idaux).getNombre(), Toast.LENGTH_LONG).show();


                     //  ente.get(idaux).ge

                Intent B =new Intent( listar_ente.this, editarente.class);
                B.putExtra("Error10",ente.get(idaux).getNombre());
                B.putExtra("Error11",ente.get(idaux).getIdentificacion());
                B.putExtra("Error12",ente.get(idaux).getTelefono());
                B.putExtra("Error13",String.valueOf(ente.get(idaux).getId()));




                startActivity(B);

            }
        });

    }

}