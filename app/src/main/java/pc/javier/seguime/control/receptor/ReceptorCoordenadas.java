package pc.javier.seguime.control.receptor;

import android.os.Bundle;

import java.util.Date;

import pc.javier.seguime.adaptador.Coordenada;
import utilidades.basico.MensajeRegistro;
import utilidades.eventos.MiniReceptor;
import utilidades.localizacion_gps.Localizador;


/**
 * Javier 2019.
 * Recibe coordenadas por parte de eventos del localizador
 */

public abstract class ReceptorCoordenadas extends MiniReceptor {



    public ReceptorCoordenadas () {
        setClaseEmisora(Localizador.class);
    }


    @Override
    public void recibir(Bundle bundle) {

        MensajeRegistro.msj("Coordenada recibida por receptor");
            Coordenada nuevaCoordenada = new Coordenada();

            nuevaCoordenada.setLatitud(bundle.getDouble("latitud"));
            nuevaCoordenada.setLongitud(bundle.getDouble("longitud"));
            nuevaCoordenada.setProveedor(bundle.getString("proveedor"));
            nuevaCoordenada.setVelocidad(bundle.getFloat("velocidad"));
            nuevaCoordenada.setCodigo(bundle.getString("codigo"));
            nuevaCoordenada.setFechaHora(new Date());

            procesarCoordenada(nuevaCoordenada);


    }


    protected abstract void procesarCoordenada (Coordenada coordenada);





}
