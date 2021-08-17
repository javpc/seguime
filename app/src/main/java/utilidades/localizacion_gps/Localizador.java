package utilidades.localizacion_gps;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

import utilidades.eventos.MiniEvento;


/**
 * Javier 2021.
 *
 */
public class Localizador implements LocationListener {

    private LocationManager localizadorADM;
    private Context contexto;

    private MiniEvento evento = new MiniEvento(this);

    private boolean proveedor_pasivo = false;

    // constructor
    public Localizador (Context contexto) {
        this.contexto = contexto;





    }




    private void generar_coordenada (Location posicion) {
        /*
        coordenada.setLatitud( posicion.getLatitude());
        coordenada.setLongitud(posicion.getLongitude());
        if (posicion.hasSpeed())
            coordenada.setVelocidad( posicion.getSpeed());

        coordenada.setProveedor(posicion.getProvider());

         */
        evento.agregar_dato("latitud", posicion.getLatitude());
        evento.agregar_dato("longitud", posicion.getLongitude());
        evento.agregar_dato("proveedor", posicion.getProvider());
        if (posicion.hasSpeed())
            evento.agregar_dato("velocidad", posicion.getSpeed());
        if (posicion.hasAltitude())
            evento.agregar_dato("altitud", posicion.getAltitude());
        if (posicion.hasAccuracy())
            evento.agregar_dato("exactitud", posicion.getAccuracy());



    }


    private void actualizar(Location posicion) {
        if (posicion == null)
            return;

        //Coordenada coordenada = new Coordenada();



        // lanza una nueva coordenada


        mensajeLog("nuevas coordenadas - latitud:" + posicion.getLatitude() + ", longitud:" + posicion.getLongitude() );

        if (evento == null)
            return;
            generar_coordenada(posicion);
            evento.lanzar();
            //emitirNuevaCoordenada(coordenada);
    }





    // activa la escucha
    public void activar() {

        // PERMISOS
        if (ActivityCompat.checkSelfPermission(contexto, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(contexto, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;


        // servicio de localizacion
        if (localizadorADM == null)
            localizadorADM = (LocationManager) contexto.getSystemService(contexto.LOCATION_SERVICE);


        if (proveedor_pasivo) {
            // obtiene la ultima posicion conocida
            Location posicion = localizadorADM.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            //actualizar(posicion);
            if (posicion != null) actualizar(posicion);
        }

        // prepara la escucha para emitir coordenadas





        localizadorADM.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 10, 5, this);
        localizadorADM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 10, 5, this);
    }

    // desactiva la escucha
    public void desactivar() {
        localizadorADM.removeUpdates(this);
    }







    private void emitirNuevaCoordenada (Coordenada coordenada) {
        ArrayList<Coordenada> listaCoordenada = new ArrayList<>();
        listaCoordenada.add(coordenada);

        evento.lanzar(listaCoordenada);
    }






    @Override
    public void onLocationChanged(Location location) {
        actualizar(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {    }

    @Override
    public void onProviderEnabled(String s) {    }

    @Override
    public void onProviderDisabled(String s) {    }


    private void mensajeLog(String texto) {
        Log.d("Localizacion", texto);
    }


    public MiniEvento getEvento() {
        return evento;
    }

    public void setEvento(MiniEvento evento) {
        this.evento = evento;
    }


    public void setReceptor(ReceptorCoordenadas receptor) {
        this.evento.agregar_receptor(receptor);
    }

    public void agregarReceptor (ReceptorCoordenadas receptor) {
        this.evento.agregar_receptor(receptor);
    }


}