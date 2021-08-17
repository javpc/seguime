package pc.javier.seguime.control.receptor;

import android.content.Context;

import pc.javier.seguime.adaptador.BD;
import pc.javier.seguime.adaptador.Coordenada;
import utilidades.basico.MensajeRegistro;

/**
    Javier 2019.
    Recibe coordenadas y las almacena en base de datos
 */

public class ReceptorCoordenadasBD extends ReceptorCoordenadas {


    Context contexto;
    public ReceptorCoordenadasBD(Context contexto) {

        this.contexto = contexto;
    }


    @Override
    protected void procesarCoordenada(Coordenada coordenada) {
        MensajeRegistro.msj("RECEPTOR COORDENADA DB - RECIBE COORDENADA");
        BD baseDeDatos = new BD(contexto);
        baseDeDatos.coordenadaInsertar(coordenada);
        baseDeDatos.cerrar();
    }



}
