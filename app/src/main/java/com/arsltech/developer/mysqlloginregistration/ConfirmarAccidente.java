package com.arsltech.developer.mysqlloginregistration;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmarAccidente extends AppCompatActivity {

    String mensajeEstoyBien;
    String mensajeNoEstoyBien;
    Button botonEstoyBien;
    Button botonNoEstoyBien;
    EditText etNumeroTelefono;
    String numeroAlQueEnviarElMensaje;



    private void enviarMensaje (String numero, String mensaje){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(getApplicationContext(), "Se ha notificado al ente.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(this, "No se pudo enviar el mensaje.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_accidente);

            //Mensajes a enviar segun el boton presionado
        mensajeEstoyBien = "Paciente ha confirmado que se encuentra fuera de peligro por el momento.";
        mensajeNoEstoyBien = "El paciente ha confirmado que se ha accidentado! Por favor acuda a su emergencia.";

        botonEstoyBien = (Button) findViewById(R.id.botonEstoyBien);
        botonNoEstoyBien = (Button) findViewById(R.id.botonNoEstoyBien);

        numeroAlQueEnviarElMensaje = getIntent().getStringExtra("numero"); // Obtener el numero enviado desde el servicio ServicioAcelerometro
            //Enviar mensaje inicial, cuando se reconozca accidente
       // etNumeroTelefono.setText(etNumeroTelefono.getText() + numeroAlQueEnviarElMensaje);


            //Metodo para reconocer cuando un boton sea presionado
        botonEstoyBien.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //si da click en que sí esta bien
                enviarMensaje(numeroAlQueEnviarElMensaje, mensajeEstoyBien);
/*
                //Para enviar a la actividad que se requiera, con los datos que se requieran
                Intent i = new Intent(ConfirmarAccidente.this, MainActivity.class);
                i.putExtra("nombreDato", "dato a enviar(String)");
                startActivity(i);*/
            }
        });
        botonNoEstoyBien.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //si da click en NO estoy bien
                enviarMensaje(numeroAlQueEnviarElMensaje, mensajeNoEstoyBien);
      /*              //Para enviar a la actividad que se requiera, con los datos que se requieran
                Intent i = new Intent(ConfirmarAccidente.this, MainActivity.class);
                i.putExtra("nombreDato", "dato a enviar(String)");
                startActivity(i);*/
            }
        });

    }
}