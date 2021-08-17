package aplicacion.regresiva;

import android.content.Context;

import pc.javier.seguime.R;
import pc.javier.seguime.vista.PantallaPrincipal;
import utilidades.eventos.MiniReceptor;

/**
 * Javier 2019.
 *  Recibe la señal de un evento cuando se activa una alarma
 *  Solo actualiza la pantalla
 */

public class ReceptorAlarma extends MiniReceptor {

    private Context contexto;
    private PantallaPrincipal pantallaPrincipal;
    public final static String CLAVE_EVENTO = "ALARMA";

    public ReceptorAlarma (Context contexto) {
        this.contexto = contexto;

    }

    @Override
    public void recibir (String dato) {
        activarALARMA();
    }


    private void activarALARMA () {


        if (pantallaPrincipal == null)
            return;

        pantallaPrincipal.iconoRastreo(true);
        pantallaPrincipal.iconoTemporizador(false);

        pantallaPrincipal.bocadillo(R.string.principal_alarma);
    }



}
