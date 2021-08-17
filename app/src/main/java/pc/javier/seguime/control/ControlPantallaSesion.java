package pc.javier.seguime.control;

import android.app.Activity;
import android.util.Log;
import pc.javier.seguime.R;
import pc.javier.seguime.adaptador.Preferencias;
import pc.javier.seguime.adaptador.Servidor;
import pc.javier.seguime.control.receptor.ReceptorPantallaSesion;
import pc.javier.seguime.vista.PantallaSesion;

/**
 * Javier 2019.
 */

public class ControlPantallaSesion extends Control {

    private PantallaSesion pantalla;
    private ReceptorPantallaSesion receptorPantallaSesion;


    public ControlPantallaSesion(Activity activity) {
        super(activity);
        this.pantalla  = new PantallaSesion(activity);
        receptorPantallaSesion = new ReceptorPantallaSesion(pantalla);
    }





    // carga y muestra las opciones guardadas en la pantalla de configuracion
    public void  cargarOpciones () {
        String servidor = preferencias.obtenerString(Preferencias.TipoPreferencia.servidor);
        String usuario = preferencias.obtenerString(Preferencias.TipoPreferencia.usuario);

        pantalla.setServidor(servidor);
        pantalla.setUsuario(usuario);




    }


    private void guardarOpciones () {
        String servidor = pantalla.getServidor().trim();
        String usuario = pantalla.getUsuario().trim();
        String clave = pantalla.getClave();
        //ssl = Cssl.isChecked();


        preferencias.guardar(Preferencias.TipoPreferencia.servidor, servidor);
        preferencias.guardar(Preferencias.TipoPreferencia.usuario, usuario);
        preferencias.guardar(Preferencias.TipoPreferencia.clave, clave);

        preferencias.setSsl (pantalla.getSsl());

    }




    public void iniciar () {


        String clave = pantalla.getClave();
        String usuario = pantalla.getUsuario().trim();
        String urlservidor = pantalla.getServidor().trim();
        boolean modoRegistro = pantalla.modoRegistro();




        if ( modoRegistro) {
            String claveRepetida = pantalla.getClaveRepetida();
            if (!clave.equals(claveRepetida)) {
                pantalla.bocadillo(R.string.sesion_clavedesigual);
                return;
            }
        }


        if (clave.length() < 4) {
            mostrarMensaje((R.string.sesion_clavecorta));
            return;
        }

        if (usuario.length() < 4) {
            mostrarMensaje((R.string.sesion_usuariocorto));
            return;
        }

        if (urlservidor.length() < 2) {
            mostrarMensaje((R.string.sesion_sinservidor));
            return;
        }


        mostrarMensaje((R.string.espere));


        guardarOpciones();



        conectar(urlservidor, usuario, clave, modoRegistro);


        mensajeLog ( "Enviando inicio de sesion");

    }



    public void cambiarRadio () {
        pantalla.cambiarRadio();
    }



    private void mostrarMensaje (int id) {
        pantalla.bocadillo(id);
    }



    private void conectar (String url, String usuario, String clave, boolean modoRegistro) {
        pantalla.conexionActiva = true;
        // receptor de estados de la conexion (conectado, finalizado, etc) para mostrar en pantalla

        Servidor servidor = new Servidor(url,usuario, clave);
        servidor.setReceptor (receptorPantallaSesion);
        if (modoRegistro)
            servidor.agregarComando(Servidor.Comando.registro);
        else
            servidor.agregarComando(Servidor.Comando.sesion);

        servidor.setSsl(pantalla.getSsl());
        servidor.enviar();
    }

    public void cancelar () {
        pantalla.conexionActiva = false;
        pantalla.finalizarActividad();
    }


    public void actualizarBotones () {
        pantalla.habiltarBotonIniciar(!pantalla.conexionActiva);
    }


    public void resumir () {



    }

    public void destruir () {

    }

    private void mensajeLog (String texto) {
        Log.d("Control pantalla Sesion", texto);
    }
}
