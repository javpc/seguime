package pc.javier.seguime.adaptador;

import utilidades.basico.MensajeRegistro;
import utilidades.conexion.ReceptorConexionHTTP;


public abstract class ReceptorConexion extends ReceptorConexionHTTP  {



    // -----------------------------------------------------

    protected int  entero (String numero) {
        try { return Integer.parseInt(numero); }
        catch (Exception e) { return 0; }
    }

    protected boolean boleano (String valor) {
        try { return Boolean.parseBoolean(valor); }
        catch (Exception e) { return false; }
    }





    public void crear_comandos (String dato) {
        dato = dato.trim();
        MensajeRegistro.msj("receptor COMANDOS: " +dato);


        // al dato lo "subdivide" en comando y parametro
        int indice = dato.indexOf(" ");
        String comando = dato;
        String parametro = "";

        if (indice > 0) {
            comando = dato.substring(0, indice).trim();
            parametro = dato.substring(indice + 1).trim();
        }

        ejecutar_comando (comando, parametro);
    }

    public abstract  void ejecutar_comando(String comando, String parametro) ;



    @Override
    public void recibir (String dato) {
        crear_comandos(dato);
    }






}
