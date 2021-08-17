package pc.javier.seguime.control.receptor;

import android.app.Activity;

import pc.javier.seguime.adaptador.ReceptorConexion;
import pc.javier.seguime.vista.PantallaPrincipal;
import utilidades.conexion.ConexionHTTP;

/**
 * Created by Javier on 22/9/2019.
 */

public class ReceptorPantallaPrincipal extends ReceptorConexion {

    public ReceptorPantallaPrincipal(Activity activity) {
        super();
        if (activity != null)
            pantallaPrincipal = new PantallaPrincipal(activity);
    }

    @Override
    public void recibir(String dato) {

    }

    PantallaPrincipal pantallaPrincipal;

    @Override
    public void recibir(ConexionHTTP.Estado estado) {



        switch (estado) {
            case Conectando:
                pantallaPrincipal.iconoInternet(true);
                break;

            case Finalizado:
            case Error:
                pantallaPrincipal.iconoInternet(false);
                break;
        }
    }

    @Override
    public void ejecutar_comando (String comando, String parametro) {


        switch (comando) {
            case "sesion":
                boolean sesionIniciada = boleano(parametro);
                if (sesionIniciada == false)
                    pantallaPrincipal.finalizarActividad();
                break;

            case "notificacion":
                pantallaPrincipal.bocadillo(parametro);
                break;

            case "mensajeestado":
                pantallaPrincipal.bocadillo(parametro);
                break;

            case "mensaje":
                pantallaPrincipal.mostrarMensajePrincipal(parametro);
                break;

            case "bloqueo":
                boolean bloqueo = boleano(parametro);
                pantallaPrincipal.iconoBloqueado(bloqueo);
                break;

            case "rastreo":
                boolean rastreo = boleano(parametro);
                pantallaPrincipal.iconoRastreo(rastreo);
                break;

            case "alarmaservidor":
                boolean alarma = boleano(parametro);
                pantallaPrincipal.iconoTemporizadorServidor(alarma);
                break;

            case "sms":
                pantallaPrincipal.mostrarMensajePrincipal("Modificación de SMS");
                break;

            case "telegram":
                pantallaPrincipal.mostrarMensajePrincipal("ID Telegram Modificado");
                break;

        }

    }
}
