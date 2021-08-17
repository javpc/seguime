package pc.javier.seguime.adaptador;

import pc.javier.seguime.BuildConfig;

/**
 * Javier 2019.
 *  configuraciones y parametros constantes
 */

public final class Constante {
    public final static String version = BuildConfig.VERSION_NAME ;

    public final static String userAgent = "seguime 4";   // esto es usado para la versión del servidor (no identifica la versión del sistema)
    public final static String urlDonacion = "https://gitlab.com/javipc/mas";
    public final static String urlBot = "http://t.me/seguimebot";

    public final static String urlTelegram = "https://t.me/MisProyectos/7";
    public final static String urlSitio = "http://seguime.atwebpages.com/";

    public final static String urlRegistro = urlSitio + "registroversion.php";
    public final static String urlServidor = urlSitio + "servicio.php";
    public final static String urlEliminarCuenta = urlSitio + "eliminarcuenta.php";




    public final static boolean versionConSMS = true;
    public final static boolean versionCompleta = true;

}
