package utilidades.unidades;

/**
 *  Javier 2019.
 */

public class Velocidad {
    private float velocidad = 0;

    public Velocidad (float datoGps) {
        velocidad = datoGps;
    }
    public Velocidad () {

    }


    private float kmh() {
        return (float) (velocidad * 3.6000);
    }
    private static float kmh(float velocidad) {
        return (float) (velocidad * 3.6000);
    }

    public  static String kmh_cadena (float velocidad) {
        return formatoDecimal (kmh(velocidad));
    }

    public String kmhString () {
        String respuesta = formatoDecimal(kmh());
        respuesta = respuesta + " km/h";
        return respuesta;
    }


    private static String formatoDecimal(float valor) {
        return  String.format("%.2f", valor).replace(",", ".");
    }



    public enum Unidad {
        gps,
        kmh,

    }

}
