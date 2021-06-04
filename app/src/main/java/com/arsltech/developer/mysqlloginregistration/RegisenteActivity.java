package com.arsltech.developer.mysqlloginregistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisenteActivity extends AppCompatActivity {
    EditText ed_nombre,ed_telefono,ed_identificacion;
    String str_nombre,str_telefono,str_identificacion,str_v1,v1;

    String url = "https://diamantess.000webhostapp.com/conexionapp/register_ente.php";
TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_ente);


        ed_telefono = findViewById(R.id.ed_telefono);
        ed_nombre = findViewById(R.id.ed_nombre);

        ed_identificacion = findViewById(R.id.ed_identificacion);

        tv1=findViewById(R.id.textView3);

        Bundle informacion = this.getIntent().getExtras();
       v1 = informacion.getString("Error3");

        tv1.setText(v1);

    }
    public void moveToLogin(View view) {

        startActivity(new Intent(getApplicationContext(),LoginActivity.class));

    }

    public void Register(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere..");


        if(ed_nombre.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese nombre", Toast.LENGTH_SHORT).show();
        }
        else if(ed_telefono.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese telefono", Toast.LENGTH_SHORT).show();
        }

        else if(ed_identificacion.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese identificacion", Toast.LENGTH_SHORT).show();
        }

        else{

            progressDialog.show();
            str_nombre = ed_nombre.getText().toString().trim();
            str_telefono= ed_telefono.getText().toString().trim();

            str_identificacion = ed_identificacion.getText().toString().trim();
            str_v1 = v1;




            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    ed_nombre.setText("");
                    ed_telefono.setText("");

                    ed_identificacion.setText("");





                    Toast.makeText(RegisenteActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisenteActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("nombre",str_nombre);
                    params.put("telefono",str_telefono);

                    params.put("identificacion",str_identificacion);

                    params.put("id_registro2",str_v1);




                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(RegisenteActivity.this);
            requestQueue.add(request);


        }







}


}
