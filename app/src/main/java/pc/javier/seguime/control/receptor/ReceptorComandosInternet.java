package pc.javier.seguime.control.receptor;

import android.app.Activity;

import pc.javier.seguime.adaptador.BD;
import pc.javier.seguime.adaptador.Preferencias;
import pc.javier.seguime.adaptador.ReceptorConexion;
import utilidades.basico.MensajeRegistro;
import utilidades.conexion.ConexionHTTP;

/**
 * Javier 2019.
 *  Recibe comandos por parte del Servidor
 */

public class ReceptorComandosInternet extends ReceptorConexion {

    private Preferencias preferencias;
    private Activity actividad;

    public ReceptorComandosInternet(Activity activity) {
        super();
        actividad = activity;
        preferencias = new Preferencias(activity);
    }


    @Override
    public void recibir(ConexionHTTP.Estado estado) {

    }


    @Override
    public void ejecutar_comando (String comando, String parametro) {

        mensajeLog("COMANDO: "+comando + " | paramentro: " + parametro);

        switch (comando) {
            case "sesion":
                sesion(parametro);
                break;
            case "notificacion":
                notificacion (parametro);
                break;
            case "mensaje":
                mensaje (parametro);
                break;

            case "mensajeestado":
                // mensajeEstado (parametro);
                break;

            case "zonahoraria":
                zonaHoraria (parametro);
                break;
        }



        // comandos que requieren sesión iniciada
        if (preferencias.getSesionIniciada() == false)
            return;

        switch (comando) {
            case "marcar":
                marcar(parametro);
                break;
            case "marcarimagen":
                marcarimagen(parametro);
                break;
            case "bloqueo":
                bloqueo(parametro);
                break;
            case "rastreo":
                rastreo (parametro);
                break;
            case "eliminartodo":
                eliminarCoordenadas();
                break;

            case "alarmaservidor":
                alarmaServidor (parametro);
                break;

            case "sms":
                sms (parametro);
                break;

            case "telegram":
                telegram (parametro);
                break;

        }
    }










    // ------------------------------------------- COMANDOS ----

    private void sesion (String estado) {
        boolean sesionIniciada = boleano(estado);
        preferencias.setSesionIniciada(sesionIniciada);
    }

    private void mensaje (String mensaje) {
        preferencias.setMensaje(mensaje);
    }

    private void notificacion (String mensaje) {
        preferencias.setNotificacion(mensaje);
    }



    // -------

    private void marcar (String codigo) {
        BD bd = new BD (actividad);
        bd.coordenadaMarcar(codigo);
        bd.cerrar();
    }

    private void marcarimagen (String codigo) {
        BD bd = new BD (actividad);
        bd.fotoMarcar(codigo);
        bd.cerrar();
    }

    private void eliminarCoordenadas () {
        BD bd = new BD (actividad);
        bd.eliminarTodoYCerrar();
    }

    private void bloqueo (String estado) {
        boolean bloqueo = boleano(estado);
        preferencias.setBloqueado(bloqueo);
    }

    private void rastreo (String estado) {
        boolean rastreo = boleano(estado);
        preferencias.setRastreo(rastreo);
    }

    private void alarmaServidor (String estado) {
        boolean alarma = boleano(estado);
        preferencias.setAlarmaServidor(alarma);
    }


    private void sms (String numero) {
        if (preferencias.getPermitirConfigurarSMS() == false) {
            preferencias.setMensaje("Se intentó modificar SMS");
            return;
        }
        preferencias.setNumeroSms(numero);
        preferencias.setMensaje("SMS Modificado");
    }

    private void telegram (String numero) {
        preferencias.setIdTelegram(numero);
        preferencias.setMensaje("Telegram Modificado");
    }


    private void zonaHoraria (String numero) {
        // preferencias.setZonaHoraria(entero(numero));
    }


    // -----------------------------------------------------







    private void mensajeLog (String texto) { MensajeRegistro.msj("RECEPTOR COMANDOS", texto);}
}
