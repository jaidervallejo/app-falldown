package com.arsltech.developer.mysqlloginregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    RequestQueue req;
    TextView tv1;
    TextView tv2;
    TextView tv3;

    EditText ed_email,ed_password;

    String str_email,str_password;
    String url ="";
            private Button btn;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);

        btn=findViewById(R.id.eliminar_ente);

btn.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {

                               inicio( "https://diamantess.000webhostapp.com/conexionapp/login.php?variable1="+ed_email.getText()+"&variable2="+ed_password.getText());


                           }
                       }
);
    }



    private void inicio(String url){



        JsonArrayRequest json = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;



                for (int i = 0; i < response.length(); i++) {

                    try {

                        jsonObject = response.getJSONObject(i);


                        // tv1.setText(jsonObject.getString("username"));
                        //tv2.setText(jsonObject.getString("email"));



                        if(jsonObject.getString("email").equals(ed_email.getText().toString())){


                            if(jsonObject.getString("password").equals(ed_password.getText().toString())){

                                //tv1.setText(jsonObject.getString("username"));
                               //tv2.setText(jsonObject.getString("email"));

                                Intent intent= new Intent(LoginActivity.this,MainActivity.class);

                                intent.putExtra("Error5", jsonObject.getString("name"));
                                intent.putExtra("Error11", jsonObject.getString("identificacion"));
                                intent.putExtra("Error7", jsonObject.getString("telefono"));
                                intent.putExtra("Error8", jsonObject.getString("direccion"));
                                intent.putExtra("Error9", jsonObject.getString("nacimiento"));
                                intent.putExtra("Error10", jsonObject.getString("sexo"));



                                intent.putExtra("Error3", jsonObject.getString("email"));



                                intent.putExtra("Error4", jsonObject.getString("password"));

                                intent.putExtra("Error6", jsonObject.getString("id_registro2"));



                                startActivity(intent) ;
                                finish();

                            }else{
                                Toast.makeText(getApplicationContext(),"USUARIO O CONTRASEÑA INCORRECTO", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(),"USUARIO O CONTRASEÑA INCORRECTA", Toast.LENGTH_LONG).show();
                        }


                        // mPasswordView.setText(jsonObject.getString("Pass"));
                        //    tv1.setText(jsonObject.getString("Usuario"));
                        // tv2.setText(jsonObject.getString("Pass"));


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "/ERROR/ USUARIO O CONTRASEÑA INCORRECTO", Toast.LENGTH_LONG).show();
            }
        });


        req= Volley.newRequestQueue(this);
        req.add(json);




    }



































/*
    public void Login(View view) {

        if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");

            progressDialog.show();

            str_email = ed_email.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("logged in successfully")){

                        ed_email.setText("");
                        ed_password.setText("");
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    }

                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("email",str_email);
                    params.put("password",str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(request);




        }
    }

    */


    public void moveToRegistration(View view) {
        Intent A =new Intent( LoginActivity.this, RegistrationActivity.class);


        startActivity(A);
    }

}