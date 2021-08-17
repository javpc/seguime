package utilidades.eventos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

import utilidades.basico.MensajeRegistro;

public class MiniReceptor  {

    // La "clave" del Bundle para obtener el dato y ejecutar el método "recibir"
    // No es necesaria. Si esta clave no está definida se pueden extraer los datos de bundle "manualmente"




    protected String clase_emisora=null;
    protected Handler handler;



    public MiniReceptor () {
        nuevo_handler();
    }


    // usado por MiniEvento
    public void recibir_bundle_evento (Bundle datos) {
        Message mensaje = new Message();
        mensaje.setData (datos);
        recibir_mensaje_evento(mensaje);
    }

    private void recibir_mensaje_evento(Message mensaje) {
        handler.sendMessage(mensaje);
    }



    @SuppressLint("HandlerLeak")
    private void  nuevo_handler () {

        handler = new Handler() {
            @Override
            public void handleMessage(Message mensaje) {
                // extrae datos del msj
                final Bundle bundle = mensaje.getData();
                // recibir (bundle);



                final String clave = MiniEvento.CLAVE_EVENTO;
                

                int tipo_dato = MiniEvento.TIPO_INDEFINIDO;
                if (bundle.containsKey(MiniEvento.CLAVE_TIPO_DATO))
                    tipo_dato= bundle.getInt(MiniEvento.CLAVE_TIPO_DATO);

                MensajeRegistro.msj("[MINI-RECEPTOR] Tipo recibido: " +tipo_dato);



                switch (tipo_dato) {
                    case MiniEvento.TIPO_CADENA:
                        recibir (bundle.getString(clave));
                        break;

                    case MiniEvento.TIPO_ENTERO:
                        recibir(bundle.getInt(clave));
                        break;

                    case MiniEvento.TIPO_DOBLE:
                        recibir(bundle.getDouble(clave));
                        break;

                    case MiniEvento.TIPO_FLOTANTE:
                        recibir(bundle.getFloat(clave));
                        break;

                    case MiniEvento.TIPO_LONG:
                        recibir(bundle.getLong(clave));
                        break;

                    case MiniEvento.TIPO_LISTA:
                        recibir((ArrayList) bundle.getSerializable(clave));
                        break;

                    case MiniEvento.TIPO_PAQUETE:
                        recibir(bundle.getBundle(clave));
                        break;

                    case MiniEvento.TIPO_INDEFINIDO:
                        recibir(bundle);
                        break;
                }

            }
        };
    }

    // recibe los mensajes por parte de los eventos y obtiene el "dato" (bundle)





    // metodos para recibir los datos recibidos (sobreescribir)

    public void recibir (Bundle dato) {  }



    // metodos de procesado rápido
    public void recibir (String valor) { }
    public void recibir (int valor)    { }
    public void recibir (ArrayList valor) { }
    public void recibir (float valor)  { }
    public void recibir (double valor) { }
    public void recibir (long valor)   { }



    // gets - sets



    public Handler getHandler () { return  handler; }


    public void setHandler (Handler handler) { this.handler = handler; }




    public String getClaseEmisora() {
        return clase_emisora;
    }

    public void setClaseEmisora(Class clase_emisora) {
        this.clase_emisora = clase_emisora.getSimpleName();
    }




    public void suscribir () {
        MiniTunel.suscribir(this);
    }

    public void desuscribir () {
        MiniTunel.desuscribir(this);
    }

}
