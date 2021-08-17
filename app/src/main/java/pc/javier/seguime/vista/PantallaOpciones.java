package pc.javier.seguime.vista;

import android.app.Activity;

import pc.javier.seguime.R;
import pc.javier.seguime.adaptador.Pantalla;

/**
 * Javier 2019.
 */

public class PantallaOpciones extends Pantalla {
    public PantallaOpciones(Activity activity) {
        super(activity);
    }




    // gets
    public boolean getEnviarInfoConexion () {
        return getToggle (R.id.opciones_enviarInfoConexion).isChecked();
    }

    public int getInternet () {
        return entero(getTexto(R.id.opciones_internet));
    }

    public int getActividad () {
        return entero(getTexto(R.id.opciones_actividad));
    }

    public int getInactividad () {
        return entero(getTexto(R.id.opciones_inactividad));
    }

    public String getSms () {
        return getTexto(R.id.opciones_sms).trim();
    }

    public String getTelegram () {
        return getTexto(R.id.opciones_telegram);
    }

    public boolean getIniciarConSistema () {
        return getToggle (R.id.opciones_iniciarConElSistema).isChecked();
    }

    public boolean getRastreo () {
        return getToggle (R.id.opciones_rastreo).isChecked();
    }

    public boolean getActivarConPantalla () {
        return getToggle (R.id.opciones_activarconpantalla).isChecked();
    }


    public boolean getPermitirConfigurarSMS () {
        return getToggle(R.id.opciones_permitirConfigurarSMS).isChecked();
    }

    public boolean getConectarRedesAbiertas () {
        return getToggle(R.id.opciones_conectarseRedesAbiertas).isChecked();
    }

    public boolean getEnviarFotografias () {
        return getToggle(R.id.opciones_enviarFotografias).isChecked();
    }

    public boolean getActivarSMS () {
        return getToggle(R.id.opciones_permitirActivarSMS).isChecked();
    }

    // public int getZonaHoraria () { return entero(getTexto(R.id.opciones_zonahoraria)); }



    // sets -

    public void  setEnviarInfoConexion (boolean valor) {
        setToggle(R.id.opciones_enviarInfoConexion, valor);
    }

    public void  setInternet (int valor) {
        setTextView(R.id.opciones_internet, String.valueOf(valor));
    }

    public void  setActividad (int valor) {
        setTextView(R.id.opciones_actividad, String.valueOf(valor));
    }

    public void setInactividad (int valor) {
        setTextView(R.id.opciones_inactividad, String.valueOf(valor));
    }

    public void setSms (String valor) {
        setTextView(R.id.opciones_sms, valor);
    }

    public void setTelegram (String valor) {
        setTextView(R.id.opciones_telegram, valor);
    }

    public void setIniciarConSistema (boolean valor) {
        setToggle (R.id.opciones_iniciarConElSistema, valor);
    }

    public void setRastreo (boolean valor) {
        setToggle (R.id.opciones_rastreo, valor);
    }

    public void setActivarConPantalla (boolean valor) {
        setToggle (R.id.opciones_activarconpantalla, valor);
    }


    public void setPermitirConfigurarSMS (boolean valor) {
        setToggle(R.id.opciones_permitirConfigurarSMS, valor);
    }

    public void setConectarseRedesAbiertas (boolean valor) {
        setToggle(R.id.opciones_conectarseRedesAbiertas, valor);
    }

    public void setEnviarFotografias (boolean valor) {
        setToggle(R.id.opciones_enviarFotografias, valor);
    }

    public void setActivarSMS (boolean valor) {
        setToggle(R.id.opciones_permitirActivarSMS, valor);
    }

    // public void setZonaHoraria (String valor) { setTextView(R.id.opciones_zonahoraria, valor); }

    public void setLimiteCoordenadas (int valor) {
        setTextView(R.id.opciones_limite_coordenadas, String.valueOf(valor));
    }

    public int getLimiteCoordenadas () {
        return entero(getTexto(R.id.opciones_limite_coordenadas));
    }




    public void cambiarCategoria (int id) {
        animar_ocultar(R.id.opciones_grupo_actividad);
        animar_ocultar(R.id.opciones_grupo_sistema);
        animar_ocultar(R.id.opciones_grupo_rastreo);
        animar_ocultar(R.id.opciones_grupo_aplicaciones);
        animar_toque(id);
        switch (id) {
            case R.id.opciones_categoria_actividad:
                animar_mostrar(R.id.opciones_grupo_actividad);
                //bocadillo(R.string.txt_opciones_ayuda_actividad);
                break;

            case R.id.opciones_categoria_rastreo:
                animar_mostrar(R.id.opciones_grupo_rastreo);
                //bocadillo(R.string.txt_opciones_ayuda_rastreo);
                break;

            case R.id.opciones_categoria_sistema:
                animar_mostrar(R.id.opciones_grupo_sistema);
                //setVisible(R.id.opciones_grupo_sistema);
                //bocadillo(R.string.txt_opciones_ayuda_sistema);
                break;

            case R.id.opciones_categoria_aplicaciones:
                animar_mostrar(R.id.opciones_grupo_aplicaciones);
                //bocadillo(R.string.txt_opciones_ayuda_rastreo);
                break;
        }



    }

}

