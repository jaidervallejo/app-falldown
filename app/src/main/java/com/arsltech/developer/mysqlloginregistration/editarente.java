package com.arsltech.developer.mysqlloginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class editarente extends AppCompatActivity {
  //  TextView tv1;

    EditText tv1;

    EditText tv2;
    EditText tv3;
    TextView tv4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarente);
        String v1;
        String v2;
        String v3;
        String v4;


        // tv1=findViewById(R.id.textView7);
        tv1=findViewById(R.id.editTextTextPersonName);
        tv2=findViewById(R.id.editTextTextPersonName2);
        tv3=findViewById(R.id.editTextTextPersonName3);
        tv4=findViewById(R.id.textView21);





        Bundle informacion = this.getIntent().getExtras();
        v1 = informacion.getString("Error10");
         v2 = informacion.getString("Error11");
      v3 = informacion.getString("Error12");

      v4= informacion.getString("Error13");




        tv1.setText(v1);
        tv2.setText(v2);
        tv3.setText(v3);
        tv4.setText(v4);




    }

    public void Register(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere..");

/*
        if(tv1.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese nombre", Toast.LENGTH_SHORT).show();
        }
        else if(ed_telefono.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese telefono", Toast.LENGTH_SHORT).show();
        }

        else if(ed_identificacion.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese identificacion", Toast.LENGTH_SHORT).show();
        }

        else{
*/
        progressDialog.show();
         /*   v1 = ed_nombre.getText().toString().trim();
            str_telefono= ed_telefono.getText().toString().trim();

            str_identificacion = ed_identificacion.getText().toString().trim();
            str_v1 = v1;
*/


        StringRequest request = new StringRequest(Request.Method.POST, "https://diamantess.000webhostapp.com/conexionapp/actualizar.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();



                Toast.makeText(editarente.this, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(editarente.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("nombre",tv1.getText().toString());
                params.put("telefono", tv3.getText().toString());

                params.put("identificacion", tv2.getText().toString());

                params.put("id",tv4.getText().toString());


                return params;

            }
        };

       // RequestQueue requestQueue = Volley.newRequestQueue(editarente.this);
   //     requestQueue.add(request);
        RequestQueue requestQueue = Volley.newRequestQueue(editarente.this);
        requestQueue.add(request);
/*
        }

    */


    }

    public void Eliminar_ente(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere..");

/*
        if(tv1.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese nombre", Toast.LENGTH_SHORT).show();
        }
        else if(ed_telefono.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese telefono", Toast.LENGTH_SHORT).show();
        }

        else if(ed_identificacion.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese identificacion", Toast.LENGTH_SHORT).show();
        }

        else{
*/
        progressDialog.show();
         /*   v1 = ed_nombre.getText().toString().trim();
            str_telefono= ed_telefono.getText().toString().trim();

            str_identificacion = ed_identificacion.getText().toString().trim();
            str_v1 = v1;
*/


        StringRequest request = new StringRequest(Request.Method.POST, "https://diamantess.000webhostapp.com/conexionapp/Eliminar_ente.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();


                Toast.makeText(editarente.this, response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(editarente.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();



                params.put("id",tv4.getText().toString());


                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(editarente.this);
        requestQueue.add(request);

/*
        }

    */


    }





}
