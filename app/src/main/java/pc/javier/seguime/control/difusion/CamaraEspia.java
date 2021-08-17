package pc.javier.seguime.control.difusion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Date;

import pc.javier.seguime.adaptador.BD;
import pc.javier.seguime.adaptador.Preferencias;
import pc.javier.seguime.adaptador.Servidor;
import utilidades.basico.MensajeRegistro;

/**
 * Created by Javier on 14/07/2019.
 */

public class CamaraEspia extends BroadcastReceiver {

    @Override
    public void onReceive(Context contexto, Intent intent) {

        Preferencias preferencias = new Preferencias(contexto);

        if (preferencias.getServicioActivo() == false)
            return;

        if (preferencias.getEnviarFotografias() == false)
            return;


        if (intent.hasExtra("imagen") == false)
            return;

        MensajeRegistro.msj (this, "IMAGEN RECIBDA");

        String imagen = intent.getStringExtra("imagen");

        BD bd = new BD(contexto);
        String fecha = String.valueOf( (new Date()).getTime() );
        String codigo = fecha;
        bd.fotoInsertar(fecha, imagen, codigo, "");
        bd.cerrar();

        if (preferencias.getRastreo() == false)
            return;

        if (preferencias.getSesionIniciada() == false)
            return;



        Servidor servidor = new Servidor( contexto);
        servidor.agregarImagen(imagen, fecha, codigo, "");
        servidor.enviar();
        MensajeRegistro.msj (this, "IMAGEN enviada");
    }
}
