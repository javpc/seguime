package aplicacion.panico;

import android.app.Activity;

import pc.javier.seguime.control.Control;

/**
 * Created by Javier on 26/7/2019.
 */

public class ControlPantallaPanico extends Control {

    PantallaPanico pantalla;

    public ControlPantallaPanico(Activity activity) {
        super(activity);
        pantalla = new PantallaPanico(activity);

        cargarOpciones();
    }


    private void cargarOpciones() {
        pantalla.setPanicoBloquear(preferencias.getPanicoBloquear());

        if (preferencias.getSesionIniciada() == false)
            pantalla.setPanicoBloquearInactivo ();
    }


    private void guardarOpciones() {
        preferencias.setPanicoBloquear(pantalla.getPanicoBloquear());
    }






    public void guardar () {
        guardarOpciones();
        getPantalla().finalizarActividad();
    }


    public void cancelar () {
        getPantalla().finalizarActividad();
    }



}