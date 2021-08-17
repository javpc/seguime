package pc.javier.seguime.control;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import pc.javier.seguime.adaptador.Preferencias;
import pc.javier.seguime.control.receptor.ReceptorCoordenadasBD;
import pc.javier.seguime.control.receptor.ReceptorCoordenadasContador;
import pc.javier.seguime.control.receptor.ReceptorCoordenadasDifusion;
import pc.javier.seguime.control.receptor.ReceptorCoordenadasInternet;
import pc.javier.seguime.control.receptor.ReceptorCoordenadasSMS;
import utilidades.basico.MensajeRegistro;
import utilidades.localizacion_gps.Localizador;





public class ServicioLocalizador extends Service {

    Intent intent;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        iniciarServicio();
        this.intent = intent;
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        detenerServicio();
        super.onDestroy();
    }








    // ------------------------------------------------------------------------------

    private Localizador localizador;



    private void iniciarServicio () {



        // localizador gps
        if (localizador == null)
            localizador = new Localizador(this);
        evSuscribir();
        localizador.activar();
    }


    private void detenerServicio () {
        if (localizador != null)
            localizador.desactivar();

        evDesuscribir();

    }







    // --- Eventos ----------

    Preferencias preferencias;

    ReceptorCoordenadasBD receptorCoordenadasBD;
    ReceptorCoordenadasInternet receptorCoordenadasInternet ;
    ReceptorCoordenadasSMS receptorCoordenadasSMS ;
    ReceptorCoordenadasDifusion receptorCoordenadasDifusion ;
    private ReceptorCoordenadasContador receptorCoordenadasContador;

    private void evSuscribir () {
        MensajeRegistro.msj ("SERIVCIO: suscribiendo receptores");
        preferencias = new Preferencias(this);

        if (receptorCoordenadasBD == null)
            receptorCoordenadasBD = new ReceptorCoordenadasBD(this);
        localizador.getEvento().agregar_receptor(receptorCoordenadasBD);

        if (receptorCoordenadasInternet == null)
            receptorCoordenadasInternet = new ReceptorCoordenadasInternet(this);
        if (preferencias.getSesionIniciada())
            localizador.getEvento().agregar_receptor(receptorCoordenadasInternet);


        if (receptorCoordenadasSMS == null)
            receptorCoordenadasSMS = new ReceptorCoordenadasSMS(this);
        if (preferencias.getRastreo() == true)
            if (preferencias.getNumeroSms().isEmpty() == false)
                localizador.getEvento().agregar_receptor(receptorCoordenadasSMS);

        if (receptorCoordenadasDifusion == null)
            receptorCoordenadasDifusion = new ReceptorCoordenadasDifusion(this);
        if (preferencias.getConectarRedesAbiertas() == true)
            localizador.getEvento().agregar_receptor(receptorCoordenadasDifusion);


        if (receptorCoordenadasContador == null)
            receptorCoordenadasContador = new ReceptorCoordenadasContador(localizador);

        receptorCoordenadasContador.limite(preferencias.geLimiteCoordenadas());
        receptorCoordenadasContador.reiniciar();
        if (preferencias.getIntervaloActividad() > 0)
            if (preferencias.getIntervaloInactividad() > 0)
                if (preferencias.getRastreo()== false)
                    localizador.getEvento().agregar_receptor(receptorCoordenadasContador);
    }

    private void evDesuscribir () {
        localizador.getEvento().quitar_receptor(receptorCoordenadasBD);
        localizador.getEvento().quitar_receptor(receptorCoordenadasInternet);
        localizador.getEvento().quitar_receptor(receptorCoordenadasSMS);
        localizador.getEvento().quitar_receptor(receptorCoordenadasDifusion);
        localizador.getEvento().quitar_receptor(receptorCoordenadasContador);
    }







}
