package pc.javier.seguime.control;

import android.view.MenuItem;

import pc.javier.seguime.ActividadAyuda;
import pc.javier.seguime.ActividadClave;
import pc.javier.seguime.ActividadDesbloqueo;
import pc.javier.seguime.ActividadImagenes;
import pc.javier.seguime.ActividadOpciones;
import pc.javier.seguime.ActividadPresentacion;
import pc.javier.seguime.ActividadRegistros;
import pc.javier.seguime.ActividadRegresiva;
import pc.javier.seguime.ActividadSesion;
import pc.javier.seguime.R;
import pc.javier.seguime.adaptador.BD;
import pc.javier.seguime.adaptador.Constante;
import pc.javier.seguime.adaptador.Preferencias;
import aplicacion.panico.ActividadPanicoOpciones;
import aplicacion.regresiva.ReceptorAlarma;
import pc.javier.seguime.control.receptor.ReceptorComandosInternet;
import pc.javier.seguime.control.receptor.ReceptorPantallaPrincipal;
import pc.javier.seguime.vista.PantallaPrincipal;
import utilidades.basico.Dialogo;
import utilidades.basico.EnlaceExterno;
import utilidades.basico.MensajeRegistro;

/**
 * Javier 2019.
 */

public class ControlPantallaPrincipal  extends Control {

    private PantallaPrincipal pantalla;
    private ReceptorPantallaPrincipal receptorPantallaPrincipal;
    private ReceptorAlarma receptorAlarma;

    public ControlPantallaPrincipal(PantallaPrincipal pantalla, Preferencias preferencias) {
        super(pantalla, preferencias);
        this.pantalla = pantalla;
    }





    // menú


    public void click (MenuItem opcion) {

        boolean bloueado = getPreferencias().obtenerBoolean(Preferencias.TipoPreferencia.bloqueado);
        String usuario = getPreferencias().obtenerString(Preferencias.TipoPreferencia.usuario);
        boolean versionregistrada = getPreferencias().obtenerBoolean(Preferencias.TipoPreferencia.versionRegistrada);


        switch (opcion.getItemId()) {

            case R.id.menu_sesion:
                if (consultarAplicacionBloqueada() == true)
                    break;
                if (preferencias.getSesionIniciada())
                    cerrarSesion();
                else
                    iniciarActividad(ActividadSesion.class);
                break;

            case R.id.menu_ayuda:
                iniciarActividad(ActividadAyuda.class);
                break;

            case R.id.menu_opciones:
                if (consultarAplicacionBloqueada() == false)
                    iniciarActividad(ActividadOpciones.class);
                break;

            case R.id.menu_registros:
                if (consultarAplicacionBloqueada() == false)
                    iniciarActividad(ActividadRegistros.class);
                break;

            case R.id.menu_imagenes:
                if (consultarAplicacionBloqueada() == false)
                    iniciarActividad(ActividadImagenes.class);
                break;

            case R.id.menu_cuentaregresiva:
                if (consultarAplicacionBloqueada() == false)
                    iniciarActividad(ActividadRegresiva.class);
                break;

            case R.id.menu_registraraplicacion:
                iniciarActividad(ActividadClave.class);
                break;

            case R.id.menu_panico:
                if (consultarAplicacionBloqueada() == false)
                    iniciarActividad(ActividadPanicoOpciones.class);
                break;

            case R.id.menu_donar:
                donar();
                break;

            case R.id.menu_cerrarsesion:
                cerrarSesion();
                break;

            case R.id.menu_bloquear:
                bloquear();
                break;

            case R.id.menu_eliminarcuenta:
                eliminarCuenta();
                break;
        }
    }












    // botón principal


    // pulsa el botón de iniciar / detener la aplicación
    public void botonIniciar () {
        if (servicioActivo()) {
            detenerServicio();
        } else {
            iniciarServicio();
        }

        mostrarBoton(servicioActivo());
        mostrarIconos();
    }


    // dibuja el boton principal
    private void mostrarBoton () {
        mostrarBoton(servicioActivo());
    }
    private void mostrarBoton (boolean estadoBoton) {
        pantalla.dibujarBoton(estadoBoton);
    }









    public void regresar () {
        pantalla = new PantallaPrincipal(activity);

        receptorPantallaPrincipal = new ReceptorPantallaPrincipal(activity);
        receptorPantallaPrincipal.suscribir();

        receptorAlarma = new ReceptorAlarma(activity);
        receptorAlarma.suscribir();

        if (preferencias.getPresentacionInicial() == false) {
            preferencias.setPresentacionInicial(true);
            iniciarActividad(ActividadPresentacion.class);
            return;
        }


        String notificacion = preferencias.getNotificacion();
        if(!notificacion.equals("")){
            pantalla.bocadillo(notificacion);
            preferencias.borrar(Preferencias.TipoPreferencia.notificacion);
        }

        String mensaje = preferencias.getMensaje();
        if(!mensaje.equals(""))
            pantalla.mostrarMensajePrincipal(mensaje);





        if (preferencias.getVersionRegistrada() == true)
            pantalla.versionRegistrada();


        // receptor internet
        ReceptorComandosInternet receptorComandosInternet = new ReceptorComandosInternet(activity);
        receptorComandosInternet.suscribir();


        // ver-
        mostrarBoton();
        mostrarIconos();
        //personalizarMenu();

    }





    public void cerrarMensaje () {
        pantalla.borrarMensajePrincipal();
        preferencias.borrar(Preferencias.TipoPreferencia.mensaje);
    }


    // -------------------------------------------------------------------------- ÍCONOS


    private void mostrarIconos () {
        pantalla.iconoTemporizador(alarmaIniciada());
        pantalla.iconoTemporizadorServidor(preferencias.getAlarmaServidor());
        pantalla.iconoSeguime( aplicacionActiva());
        pantalla.iconoRastreo(preferencias.getRastreo());
        pantalla.iconoBloqueado(preferencias.getBloqueado());
    }














    // --------------------------------------------------------------------------





    private boolean aplicacionBloqueada () {
        return preferencias.obtenerBoolean(Preferencias.TipoPreferencia.bloqueado);
    }

    private boolean consultarAplicacionBloqueada () {
        boolean respuesta = aplicacionBloqueada();
        if (respuesta == true)
            pantalla.bocadillo(R.string.txt_bloqueado);
        return respuesta;
    }


    private boolean aplicacionActiva () {
        return servicioActivo();
    }

    // verifica si el servicio está activo
    public boolean servicioActivo () {
        return servicioActivo(ServicioAplicacion.class);
    }



    private boolean alarmaIniciada () {
        return  preferencias.getAlarma() != 0;
    }





    private void cerrarSesion () {
        if (consultarAplicacionBloqueada()) {
            pantalla.bocadillo(R.string.txt_bloqueado);
            return;
        }
        if (alarmaIniciada()) {
            pantalla.bocadillo(R.string.txt_bloqueado_alarma);
            return;
        }
        Dialogo dialogo = new Dialogo(activity) {
            @Override
            public void respuestaPositiva () { cerrarSesionDefinitivamente (); }
        };
        dialogo.setTitulo (R.string.cerrar_sesion);
        dialogo.setMensaje(R.string.cerrarsesion_mensajeadvertencia);
        dialogo.mostrar();

    }

    private void cerrarSesionDefinitivamente () {
        MensajeRegistro.msj("CERRANDO SESION ***");
        detenerServicio();
        preferencias.eliminarTodo();
        new BD(activity).eliminarTodoYCerrar();
        cerrarAplicacion();
    }



    private void bloquear () {
        if (preferencias.getBloqueado())
            iniciarActividad(ActividadDesbloqueo.class);
        else {
            preferencias.setBloqueado(true);
            cerrarAplicacion();
        }


    }


    private void donar (){
        (new EnlaceExterno(activity)).abrirEnlace(Constante.urlDonacion);
    }

    private void eliminarCuenta (){
        (new EnlaceExterno(activity)).abrirEnlace(Constante.urlEliminarCuenta);
    }






    private void detenerServicio () {
        if (preferencias.getAlarma() != 0) {
            pantalla.bocadillo(R.string.txt_bloqueado_alarma);
            return;
        }

        if (preferencias.getBloqueado()) {
            pantalla.bocadillo(R.string.txt_bloqueado);
            return;
        }

        detenerServicio(ServicioAplicacion.class);
        preferencias.setServicioActivo(false);


    }

    public void iniciarServicio () {
        iniciarServicio(ServicioAplicacion.class);
        preferencias.setServicioActivo(true);
    }




    public void destruir () {
        receptorPantallaPrincipal.desuscribir();
        receptorAlarma.desuscribir();
    }
}
