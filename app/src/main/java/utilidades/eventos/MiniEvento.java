package utilidades.eventos;

import android.os.Bundle;

import java.util.ArrayList;

import utilidades.basico.MensajeRegistro;

/*
    ------------------
    EMISOR DE EVENTOS.
    ------------------

    // Emisor de evento sin clase emisora, más rápido.
    MiniEvento evento = new MiniEvento ();

    // Emisor de evento con clase emisora, usa Tunel de Eventos.
    MiniEvento evento = new MiniEvento (this);

    // Emisión de una cadena de texto.
    evento.lanzar ("hola");

    // Emisión de conjunto de datos.
    evento.agregar_dato ("saludo", "hola");
    evento.agregar_dato ("veces", 2);
    evento.lanzar ();

 */

public class MiniEvento  {

    /*
     Clave para que el receptor obtenga el "valor" (bundle.get (clave))
     De esta forma se ejecuta directamente el método de recibir el dato. bundle.get (objeto)
     Para que el receptor obtenga el "valor" (getSTRING, getINT, getXXX)
     */

    protected final static String CLAVE_EVENTO    = "_";
    protected final static String CLAVE_TIPO_DATO = "-";

    protected final ArrayList<MiniReceptor> receptoresPrivados = new ArrayList<MiniReceptor>();


    protected Bundle datos = null;
    private int tipo = -1;



    // La clase que emite el evento.
    // Solo es usada para asociar eventos con receptores de forma automática.
    protected String clase_emisora = null;




    public MiniEvento () {
        nuevo();
    }

    public MiniEvento (Object objeto_emisor) {
        clase_emisora = objeto_emisor.getClass().getSimpleName();
        if (MiniTunel.ACTIVADO)
            MiniTunel.suscribir(this);
        nuevo();
    }


    public MiniEvento (Class clase_emisora) {
        this.clase_emisora = clase_emisora.getSimpleName();
        if (MiniTunel.ACTIVADO)
            MiniTunel.suscribir(this);
        nuevo();
    }




    private void verificar_clave_y_tipo(String clave, int tipo) {
        if (this.tipo == -1) {
            set_tipo_dato(tipo);
            return;
        }

        if (this.tipo == TIPO_INDEFINIDO)
            return;

        if( datos.size() >= 2) {
            set_tipo_dato(TIPO_INDEFINIDO);
            return;
        }
    }

    // Agrega datos al paquete - Clave personalizada

    public void agregar_dato(String clave, String valor) {
        verificar_clave_y_tipo(clave, TIPO_CADENA);
        datos.putString(clave, valor);
    }

    public void agregar_dato(String clave, double valor) {
        verificar_clave_y_tipo(clave, TIPO_DOBLE );
        datos.putDouble(clave, valor);
    }

    public void agregar_dato(String clave, float valor) {
        verificar_clave_y_tipo(clave, TIPO_FLOTANTE );
        datos.putFloat(clave, valor);
    }

    public void agregar_dato(String clave, int valor) {
        verificar_clave_y_tipo(clave, TIPO_ENTERO );
        datos.putInt (clave, valor);
    }

    public void agregar_dato(String clave, long valor) {
        verificar_clave_y_tipo(clave, TIPO_LONG );
        datos.putLong (clave, valor);
    }

    public void agregar_dato(String clave, ArrayList valor) {
        verificar_clave_y_tipo(clave, TIPO_LISTA );
        datos.putSerializable(clave, valor);
    }

    public void agregar_dato(String clave, Bundle valor) {
        verificar_clave_y_tipo(clave, TIPO_PAQUETE );
        datos.putBundle(clave, valor);
    }



    // Agrega datos al paquete - Clave genérica

    public void agregar_dato(String valor) {
        agregar_dato(get_clave(), valor);
    }

    public void agregar_dato(double valor) {
        agregar_dato(get_clave(), valor);
    }

    public void agregar_dato(float valor) {
        agregar_dato(get_clave(), valor);
    }

    public void agregar_dato(int valor) {
        agregar_dato(get_clave(), valor);
    }

    public void agregar_dato(long valor) {
        agregar_dato(get_clave(), valor);
    }

    public void agregar_dato(ArrayList valor) {
        agregar_dato(get_clave(), valor);
    }

    public void agregar_dato(Bundle valor) {
        agregar_dato(get_clave(), valor);
    }


    // Paquete

    public Bundle getBundle () {
        return datos;
    }

    public void setBundle (Bundle valor) {
        datos = valor;
    }

    public void borrar () { datos.clear(); }

    public void nuevo () {
        datos = new Bundle();
        if (tipo >= 0)
            set_tipo_dato(tipo);
    }







    // Emisión de eventos


    private void emitir(MiniReceptor receptor) {
        if (receptor != null)
            receptor.recibir_bundle_evento(datos);
    }




    // Lanzamientos

    public void lanzar () {
        // Lanza el evento a una lista de receptores
        for (MiniReceptor receptor : receptoresPrivados)
            emitir(receptor);

        MensajeRegistro.msj("EMISOR DE EVENTO", "emitendo a " + String.valueOf(receptoresPrivados.size()) + " | clave: " + get_clave()  + " receptores - tipo: " + String.valueOf(tipo));
        if (get_clase_emisora() != null)
            MensajeRegistro.msj( "CLASE: " + get_clase_emisora());
        else
            MensajeRegistro.msj ("Sin clase");

        nuevo();
    }






    // Lanzamientos rápidos

    public void lanzar (String clave, String valor) {
        set_tipo_dato(TIPO_CADENA);
        agregar_dato(clave, valor);
        lanzar();
    }

    public void lanzar (String clave, double valor) {
        set_tipo_dato(TIPO_DOBLE);
        agregar_dato(clave, valor);
        lanzar();
    }

    public void lanzar (String clave, float valor) {
        set_tipo_dato(TIPO_FLOTANTE);
        agregar_dato(clave, valor);
        lanzar();
    }

    public void lanzar (String clave, int valor) {
        set_tipo_dato(TIPO_ENTERO);
        agregar_dato(clave, valor);
        lanzar();
    }

    public void lanzar (String clave, ArrayList valor) {
        set_tipo_dato(TIPO_LISTA);
        agregar_dato(clave, valor);
        lanzar();
    }

    public void lanzar (String clave, Bundle valor) {
        set_tipo_dato(TIPO_PAQUETE);
        agregar_dato(clave, valor);
        lanzar();
    }

    public void lanzar (String clave, long valor) {
        set_tipo_dato(TIPO_LONG);
        agregar_dato(clave, valor);
        lanzar();
    }

    // Lanzamientos  más rápidos

    public void lanzar (String valor) {
        lanzar (get_clave(), valor);
    }

    public void lanzar (double valor) {
        lanzar (get_clave(), valor);
    }

    public void lanzar (float valor) {
        lanzar (get_clave(), valor);
    }

    public void lanzar (int valor) {
        lanzar (get_clave(), valor);
    }

    public void lanzar (ArrayList valor) {
        lanzar (get_clave(), valor);
    }

    public void lanzar (Bundle valor) {
        lanzar (get_clave(), valor);
    }

    public void lanzar (long valor) {
        lanzar (get_clave(), valor);
    }



    // Receptores


    public void agregar_receptor (MiniReceptor miniReceptor) {
        if (miniReceptor == null)
            return;
        if (receptoresPrivados.contains(miniReceptor))
            return;

        receptoresPrivados.add(miniReceptor);
    }


    public void quitar_receptor (MiniReceptor miniReceptor) {
        receptoresPrivados.remove(miniReceptor);
    }

    public void quitar_receptores () {
        receptoresPrivados.clear();
    }

    public ArrayList<MiniReceptor> receptores () {
        return receptoresPrivados;
    }



    public void set_tipo_dato(int tipo_dato) {
        tipo = tipo_dato;
        agregar_dato(CLAVE_TIPO_DATO, tipo_dato);
    }

    // clave --------

    public String get_clave() {
        return CLAVE_EVENTO;
    }




    // clase emisora ----- solo lectura.

    public String get_clase_emisora() {
        return clase_emisora;
    }



    public final static int TIPO_CADENA   = 1;
    public final static int TIPO_ENTERO   = 2;
    public final static int TIPO_FLOTANTE = 3;
    public final static int TIPO_DOBLE    = 4;
    public final static int TIPO_LISTA    = 5;
    public final static int TIPO_PAQUETE  = 6;
    public final static int TIPO_LONG     = 7;
    public final static int TIPO_INDEFINIDO = 0;





}


/*

    CLAVE EVENTO
 */