package com.arsltech.developer.mysqlloginregistration;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class ServicioAcelerometro extends Service {

    SensorManager sensorManager; //Permite acceder al sensor del dispositivo
    Sensor sensor; //Va a representar el sensor
    SensorEventListener sensorEventListener; //Avisa cuando movamos el dispositivo
    String str_v1,v1;

    int latigo = 0;

    @Override
    public void onCreate() {
        super.onCreate();

    }




    //iniciar el proceso del sensor
    private void start(){

        Toast.makeText(getApplicationContext(), "El servicio ha iniciado", Toast.LENGTH_SHORT).show();


        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    //Finalizar el proceso del sensor
    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }

        //Enviar mensaje
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

    @Override
    public int onStartCommand(final Intent intent, int flag, int idProcess){

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //Se asocia el tipo de sensor requerido a la variable sensor
        if(sensor == null) //Verificar si esta presente el sensor en el dispositivo
            Toast.makeText(this, "No tiene acelerómetro", Toast.LENGTH_LONG).show();

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) { //avisa cuando el sensor detecte cambios en el telefono
                float x = sensorEvent.values[0]; //0 = eje x,  1 = eje y,  2 = eje z
                float y = sensorEvent.values[1]; //0 = eje x,  1 = eje y,  2 = eje z
                float z = sensorEvent.values[2]; //0 = eje x,  1 = eje y,  2 = eje z
                    //Datos que van a llevar los mensajes a enviar en caso de accidente
                String numero = "3168730794"; //Este valor debe venir de los entes a los que se van a enviar los mensajes
                String mensaje = "Es posible que el usuario se haya accidentado, la ubicacion esta en: ";//pendiente a no exceder el maximo de caracteres permitidos
                String linkGmail = "gmail.com (aqui va el link con la ubicacion)";

                if( x <- 35 || y <- 35 || z <- 35 || x > 35 || y > 35 || z > 35){     //Observar movimientos bruscos

                    enviarMensaje(""+numero, ""+mensaje); //Enviar mensaje de alerta al ente
                    enviarMensaje(numero, linkGmail);
//


                    String data= (String) intent.getExtras().get("data");

                    registro(data);
                        //Pasar a la ventana de confirmar el estado
                    Intent intent = new Intent(ServicioAcelerometro.this, ConfirmarAccidente.class);
                    intent.putExtra("numero", numero); //se pasa el #Telefono a la activity de confirmar Accidente
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //Solucion a un error que daba
                    startActivity(intent); //iniciar la actvidad de confirmar estado ConfirmarAccidente
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        start(); //iniciar el servicio de reconocimiento de accidentes
        return START_STICKY;
    }


    @Override
    public void onDestroy(){
        Toast.makeText(getApplicationContext(), "El servicio ha finalizado", Toast.LENGTH_SHORT).show();
        stop();
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void registro(final String data){



            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Por favor espere..");

String url="https://diamantess.000webhostapp.com/conexionapp/registro_historial.php";




                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();




                        Toast.makeText(ServicioAcelerometro.this, response, Toast.LENGTH_SHORT).show();
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ServicioAcelerometro.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("id_registro",data);



                        params.put("descripcion","Es posible que el usuario se haya accidentado");




                        return params;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ServicioAcelerometro.this);
                requestQueue.add(request);




    }


}


