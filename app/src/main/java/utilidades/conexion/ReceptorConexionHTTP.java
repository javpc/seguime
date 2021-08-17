package utilidades.conexion;

import utilidades.basico.MensajeRegistro;
import utilidades.eventos.MiniReceptor;

/**
 * Javier 2021
 * Típico formato para recibir Eventos de la conexión.
 */

public abstract class ReceptorConexionHTTP extends MiniReceptor {



    public ReceptorConexionHTTP () {
        setClaseEmisora(ConexionHTTP.class);
    }



    // recetor de estados
    public abstract void recibir (ConexionHTTP.Estado estado);




    // Los receptores no reciben estados, por lo tanto reciben enteros que luego se puede convertir en estado
    @Override
    public void recibir (int dato) {

        MensajeRegistro.msj("Receptor CONEXIÖN ESTADO: " + dato);

        ConexionHTTP.Estado estado = ConexionHTTP.Estado.Error;

        if (dato == ConexionHTTP.Estado.Conectando.ordinal())
            estado = ConexionHTTP.Estado.Conectando;
        else

        if (dato == ConexionHTTP.Estado.Finalizado.ordinal())
            estado = ConexionHTTP.Estado.Finalizado;
        else

        if (dato == ConexionHTTP.Estado.Recibiendo.ordinal())
            estado = ConexionHTTP.Estado.Recibiendo;
        else

        if (dato == ConexionHTTP.Estado.Enviando.ordinal())
            estado = ConexionHTTP.Estado.Enviando;
        else

        if (dato == ConexionHTTP.Estado.Error.ordinal())
            estado = ConexionHTTP.Estado.Error;


        recibir (estado);
    }





}
