package utilidades.vista;

import android.view.View;
import android.view.animation.AnimationUtils;

/**
 * Javier
 * Marzo 2020.
 */

public  class Animacion {

    public int animacion_entrada = 0; // R.anim.zoom_atras_entrada
    public int animacion_salida = 0; // R.anim.zoom_atras_salida
    public int visibilidad = View.VISIBLE;


    public void animar (View v, boolean visibilidad) {
        if (visibilidad)
            animar(v, (View.VISIBLE));
        else
            animar (v, View.GONE);
    }

    public  void animar (View v) {
        animar(v, this.visibilidad);
    }

    public  void animar (View v, int visibilidad) {

        if (visibilidad == View.VISIBLE)
            animar(v, visibilidad, animacion_entrada);
        else
            animar(v, visibilidad, animacion_salida);

    }





    public  void animar (View v, int visibilidad, int id_animacion) {
        if (v == null)
            return;

        if (v.getVisibility() == visibilidad)
            return;

        v.clearAnimation();

        v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), id_animacion));

        v.setVisibility(visibilidad);

    }







}
