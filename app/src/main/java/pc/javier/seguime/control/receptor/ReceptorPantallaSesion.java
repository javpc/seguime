package pc.javier.seguime.control.receptor;

import pc.javier.seguime.R;
import pc.javier.seguime.adaptador.ReceptorConexion;
import pc.javier.seguime.vista.PantallaSesion;
import utilidades.basico.MensajeRegistro;
import utilidades.conexion.ConexionHTTP;



/**
 * Created by Javier on 21/9/2019.
 */

public class ReceptorPantallaSesion extends ReceptorConexion {

    PantallaSesion pantallaSesion;

    public ReceptorPantallaSesion(PantallaSesion pantallaSesion) {
        super ();
        this.pantallaSesion = pantallaSesion;
    }



    @Override
    public void recibir (ConexionHTTP.Estado estado) {
        MensajeRegistro.msj( "PANTALLA SESION: ESTADO RECIBIDO" + estado);



        switch (estado) {
            case Conectando:
                pantallaSesion.setEstado(R.string.conectando);
                pantallaSesion.habiltarBotonIniciar(false);
                break;

            case Finalizado:
                pantallaSesion.setEstado(R.string.conexionfinalizada);
                pantallaSesion.habiltarBotonIniciar(true);
                pantallaSesion.conexionActiva = false;
                break;

            case Error:
                pantallaSesion.setEstado(R.string.errorconexion);
                pantallaSesion.habiltarBotonIniciar(true);
                pantallaSesion.conexionActiva = false;
                break;

        }


    }

    @Override
    public void ejecutar_comando(String comando, String parametro) {
        MensajeRegistro.msj( "PANTALLA SESION: COMANDO RECIBIDO" + comando + " | " + parametro);


        switch (comando) {
            case "sesion":
                boolean sesionIniciada = boleano(parametro);
                if (sesionIniciada == true)
                    pantallaSesion.finalizarActividad();

                break;

            case "notificacion":
                pantallaSesion.bocadillo(parametro);
                break;

            case "mensajeestado":
                pantallaSesion.bocadillo(parametro);
                break;

        }

    }
}