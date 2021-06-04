package com.arsltech.developer.mysqlloginregistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    EditText ed_username,ed_email,ed_password,ed_telefono,ed_direccion,ed_identificacion,ed_nacimiento,ed_sexo;
    String str_name,str_email,str_password,str_telefono,str_direccion,str_identificacion,str_nacimiento,str_sexo;
    String url = "https://diamantess.000webhostapp.com/conexionapp/usuario.php";

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ed_email = findViewById(R.id.ed_email);
        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        ed_telefono = findViewById(R.id.ed_telefono);
        ed_direccion = findViewById(R.id.ed_direccion);
        ed_identificacion = findViewById(R.id.ed_identificacion);
        ed_nacimiento = findViewById(R.id.ed_nacimiento);
        ed_sexo = findViewById(R.id.ed_sexo);


        final long today = MaterialDatePicker.todayInUtcMilliseconds(); //Obtener la fecha de hoy
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Seleccione una fecha"); //Titulo del calendario
        builder.setSelection(today); //Inicializar con la fecha actual
        final MaterialDatePicker materialDatePicker = builder.build();

        //Control de fechas en el campo fecha de nacimiento
        //mostrar calendario cuando se de click en el campo
        ed_nacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });
        //mostrar fecha escogida cuando se de OK en el calendario
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                ed_nacimiento.setText(materialDatePicker.getHeaderText());
            }
        });

    }

    public void moveToLogin(View view) {

        startActivity(new Intent(getApplicationContext(),LoginActivity.class));

    }



/*

    public void insertarRegistro(String url) {








        final Map<String, String> params = new HashMap<String, String>();


        StringRequest string = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "enviado", Toast.LENGTH_LONG).show();

            }

        },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {





            protected Map<String, String> getParams() throws AuthFailureError {




                params.put("name","hola 1");
               params.put("email","");
                params.put("password","");
                params.put("telefono","");
                params.put("direccion","");
                params.put("identificacion","");
                params.put("nacimiento","");
                params.put("sexo","");
                return params;




            }


        };
        RequestQueue req2 = Volley.newRequestQueue(this);
        req2.add(string);



    }











*/



    public void Register(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere..");


        if(ed_username.getText().toString().equals("")){
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else if(ed_telefono.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese telefono", Toast.LENGTH_SHORT).show();
        }
        else if(ed_direccion.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese direccion", Toast.LENGTH_SHORT).show();
        }
        else if(ed_identificacion.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese identificacion", Toast.LENGTH_SHORT).show();
        }
        else if(ed_nacimiento.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese fecha de nacimiento", Toast.LENGTH_SHORT).show();
        }
        else if(ed_sexo.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese su sexo", Toast.LENGTH_SHORT).show();
        }



        else{

            progressDialog.show();
            str_name = ed_username.getText().toString().trim();
            str_email = ed_email.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();
            str_telefono= ed_telefono.getText().toString().trim();
            str_direccion = ed_direccion.getText().toString().trim();
            str_identificacion = ed_identificacion.getText().toString().trim();
            str_nacimiento = ed_nacimiento.getText().toString().trim();
            str_sexo = ed_sexo.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    ed_username.setText("");
                    ed_email.setText("");
                    ed_password.setText("");
                    ed_telefono.setText("");
                    ed_direccion.setText("");
                    ed_identificacion.setText("");
                    ed_nacimiento.setText("");
                    ed_sexo.setText("");
                    Toast.makeText(RegistrationActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(RegistrationActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("name",str_name);
                    params.put("email",str_email);
                    params.put("password",str_password);
                    params.put("telefono",str_telefono);
                    params.put("direccion",str_direccion);
                    params.put("identificacion",str_identificacion);
                    params.put("nacimiento",str_nacimiento);
                    params.put("sexo",str_sexo);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(RegistrationActivity.this);
            requestQueue.add(request);


        }

    }



}
