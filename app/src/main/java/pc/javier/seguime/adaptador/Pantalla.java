package pc.javier.seguime.adaptador;

import android.app.Activity;
import android.view.View;

import pc.javier.seguime.R;
import utilidades.vista.Animacion;
import utilidades.vista.EditorVistas;

/**
 * Javier 2019.
 * adaptador de "editor de vistas"
 */

public class Pantalla extends EditorVistas {
    public Pantalla(Activity activity) {
        super(activity);
    }
    public Pantalla(View vista) {
        super(vista);
    }

    public String getTexto (int id) {
        return getEditText(id).getText().toString();
    }

    public int entero (String valor) {
        try {
            return Integer.parseInt(valor.trim());
        }
        catch (Exception e) {
            return 0;
        }
    }





    public int cuadroVerdeRedondeando () {
        return R.drawable.botonredondeado_verde;
    }

    public int cuadroGrisRedondeando () {
        return R.drawable.botonredondeadogris;
    }

    public int cuadroRojoRedondeando () {
        return R.drawable.botonredondeadorojo;
    }




    protected Animacion animacion = new Animacion ();
    private int animacion_entrada =  R.anim.zoom_atras_entrada;
    private int animacion_salida =  R.anim.zoom_atras_salida;
    private int animacion_toque = R.anim.parpadeo;



    public void entrada_salida (int animacion_entrada) {
        entrada_salida(animacion_entrada, animacion_entrada);
    }

    public void entrada_salida (int animacion_entrada, int animacion_salida) {
        this.animacion_entrada = animacion_entrada;
        this.animacion_salida = animacion_salida;

        animacion.animacion_entrada = animacion_entrada;
        animacion.animacion_salida = animacion_salida;
    }



    public void animar (View v) {
        animacion.animar(v);
    }

    public void animar_mostrar (View v) {
        animacion.animar(v, View.VISIBLE, animacion_entrada);
    }

    public void animar_ocultar (View v) {
        animacion.animar(v, View.GONE, animacion_salida);
    }

    public void animar_mostrar (int id) {
        animar_mostrar(getView(id));
    }

    public void animar_ocultar (int id) {
        animar_ocultar(getView(id));
    }

    public void animar_toque (int id) {
        animar_toque(getView(id));
    }

    public void animar_toque (View v) {
        animacion.animar (v, View.INVISIBLE, animacion_toque);
        animacion.animar (v, View.VISIBLE, animacion_toque);
    }
}
