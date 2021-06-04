package com.arsltech.developer.mysqlloginregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch botonActivarServicio;

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CONECTAR LOS ELEMENTOS LOGICOS CON LA VISTA
        botonActivarServicio = (Switch) findViewById(R.id.switch1);

        //PERMISOS DE MENSAJES
        if(ActivityCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                MainActivity.this,Manifest
                        .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]
                    { Manifest.permission.SEND_SMS,},1000);
        }else{
        };


tv1=(TextView) findViewById(R.id.textView2);
        tv2=(TextView) findViewById(R.id.textView6);

        tv3=(TextView) findViewById(R.id.textView9);
        tv4=(TextView) findViewById(R.id.textView12);
        tv5=(TextView) findViewById(R.id.textView14);
        tv6=(TextView) findViewById(R.id.textView15);
        tv7=(TextView) findViewById(R.id.textView18);
        tv8=(TextView) findViewById(R.id.textView22);





        Bundle informacion = this.getIntent().getExtras();
        String v1 = informacion.getString("Error3");
        String v2 = informacion.getString("Error5");
        String v3 = informacion.getString("Error11");
        String v4 = informacion.getString("Error7");
        String v5 = informacion.getString("Error8");
        String v6 = informacion.getString("Error9");
        String v7 = informacion.getString("Error10");
        String v8 = informacion.getString("Error6");

        tv1.setText(v1);
        tv2.setText(v2);
        tv3.setText(v3);
        tv4.setText(v4);
        tv5.setText(v5);
        tv6.setText(v6);
        tv7.setText(v7);
        tv8.setText(v8);



    }

    public void ente(View view) {
        Bundle informacion = this.getIntent().getExtras();

        Intent A =new Intent( MainActivity.this, RegisenteActivity.class);

        A.putExtra("Error3",informacion.getString("Error6"));

        startActivity(A);
    }

    public void edit_ente(View view) {
        Bundle informacion = this.getIntent().getExtras();
        Intent A =new Intent(MainActivity.this, listar_ente.class);
        A.putExtra("Error3",informacion.getString("Error6"));
        startActivity(A);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
    private void enviarMensaje (String numero, String mensaje){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(this, "Mensaje no enviado, datos incorrectos", Toast.LENGTH_LONG).show();
        }
    }

    public void activarDesactServicio(View view) {
        if (botonActivarServicio.isChecked()) {

            Toast.makeText(this, "Iniciando servicio...", Toast.LENGTH_SHORT).show();
          //  startService(new Intent(this, ServicioAcelerometro.class).putExtra("error","hola"));


            Intent ir=new Intent(this, ServicioAcelerometro.class);
            ir.putExtra("data", tv8.getText());
            this.startService(ir);


        }else {
            Toast.makeText(this, "Finalizando servicio...", Toast.LENGTH_SHORT).show();
            stopService(new Intent(this, ServicioAcelerometro.class));
        }


    }
}
