package utilidades.localizacion_mapa;

import android.graphics.Color;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

/**
 * Javier 2019.
 *
 * // En Build.gradle:
 *
 * dependencies {
 *     implementation 'org.osmdroid:osmdroid-android:6.0.1'
 * }
 */

public class Mapa {




    private final double inicio_latitud  = -33.2786;
    private final double inicio_longitud = -66.3183;

    protected GeoPoint punto_marca      = new GeoPoint(inicio_latitud, inicio_longitud);
    protected GeoPoint punto_movimiento = new GeoPoint(inicio_latitud, inicio_longitud);

    protected GeoPoint punto_dibujo1 = null;
    protected GeoPoint punto_dibujo2 = null;
    protected Polyline linea = null;

    protected MapView mapa;
    protected MapController mapaControlador;
    protected Marker marca;




    public Mapa (MapView mapa) {
        this.mapa = mapa;
        cargarMapa(mapa);
    }



    private void cargarMapa(MapView mapa) {

        // Evita bloqueo
        Configuration.getInstance().setUserAgentValue("Rutera");

        // si el mapa sigue bloqueado, usar....
        //Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        // se configura el tipo de mapa
        mapa.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);

        mapa.setMultiTouchControls(true);


        // se obtiene un controlador para el mapa creado
        mapaControlador = (MapController) mapa.getController();

        // se mueve el mapa a una coordenada
        mapaControlador.animateTo(punto_marca);
        // se establece un nivel de zoom
        mapaControlador.setZoom(16);
        // se limpia el mapa y se actualiza
        mapa.getOverlays().clear();
        mapa.invalidate();

        marcar (punto_marca.getLatitude(),punto_marca.getLongitude());

    }


    // pone una marca en el mapa
    public void marcar (double latitud, double longitud) {
        // crea la coordenada
        punto_marca = new GeoPoint(latitud, longitud);
        // crea el marcador en el mapa
        marca = new Marker(mapa);
        // ubica el marcador en la coordenada
        marca.setPosition(punto_marca);
        // mueve el mapa a la coordenada
        // marca.setTitle("¡hola! \r\n todavia no hay puntos para mostrar");
        //mapaControlador.animateTo(punto_marca);
        // limpia el mapa
        mapa.getOverlays().clear();
        // inserta el marcador
        mapa.getOverlays().add(marca);
        // refresca el mapa
        mapa.invalidate();
    }


    public void marcar () {
        marcar (inicio_latitud, inicio_longitud);
    }






    // mueve el marcador a la posicion indicada
    public void mover_marca (double latitud, double longitud) {
        // punto_marca = new GeoPoint(latitud, longitud);

        punto_marca.setLatitude(latitud);
        punto_marca.setLongitude(longitud);
        marca.setPosition(punto_marca);
    }


    public void titulo_marca (String texto) {
        marca.setTitle(texto);
    }


    public void mover_mapa (double latitud, double longitud)  {
        punto_movimiento.setLatitude(latitud);
        punto_movimiento.setLongitude(longitud);
        mapaControlador.animateTo(punto_movimiento);
    }

    public void  mover (double latitud, double longitud) {
        mover_marca(latitud, longitud);
        mover_mapa(latitud, longitud);

    }

    public void acercamiento (int nivel) {
        mapaControlador.setZoom(nivel);
    }

    public void dibujar_linea(double latitud, double longitud) {
        dibujar_linea(latitud, longitud, "");
    }

    public void dibujar_linea(double latitud, double longitud, String titulo) {
        dibujar_linea(latitud, longitud, "", Color.BLACK);
    }

    public void dibujar_linea(double latitud, double longitud, String titulo, int color) {

        if (punto_dibujo2 == null || punto_dibujo1 == null) {
            mover_pincel(latitud, longitud);
            mover_mapa(latitud, longitud);
            return;
        }

        dibujar_linea(punto_dibujo2.getLatitude(), punto_dibujo2.getLongitude(), latitud, longitud, titulo, color);

    }


    public void dibujar_linea(double latitud1, double longitud1, double latitud2, double longitud2) {
        dibujar_linea(latitud1, longitud1, latitud2, longitud2, "");
    }


    public void dibujar_linea (double latitud1, double longitud1, double latitud2, double longitud2, String titulo) {
        dibujar_linea(latitud1, longitud1, latitud2, longitud2, titulo, 0);
    }

    public void dibujar_linea (double latitud1, double longitud1, double latitud2, double longitud2, String titulo, int color) {

        punto_dibujo1 = new GeoPoint(latitud1, longitud1);
        punto_dibujo2 = new GeoPoint(latitud2, longitud2);

        linea = new Polyline(mapa);
        linea.getPoints().add (punto_dibujo1);
        linea.getPoints().add (punto_dibujo2);
        linea.setTitle(titulo);
        linea.setColor(color);
        mapa.getOverlays().add(linea);

        mapa.invalidate();

    }


    public void dibujar_linea (Polyline linea) {
        mapa.getOverlays().add(linea);
        mapa.invalidate();
    }

    public void dibujar_linea () {
        if (linea == null)
            return;
        mapa.getOverlays().add(linea);
        mapa.invalidate();
    }

    public Polyline obtener_linea (double latitud1, double longitud1, double latitud2, double longitud2) {
        nueva_linea (latitud1, longitud1, latitud2, longitud2);
        return linea;
    }


    public void nueva_linea (double latitud1, double longitud1, double latitud2, double longitud2) {
        punto_dibujo1 = new GeoPoint(latitud1, longitud1);
        punto_dibujo2 = new GeoPoint(latitud2, longitud2);

        linea = new Polyline(mapa);
        linea.getPoints().add (punto_dibujo1);
        linea.getPoints().add (punto_dibujo2);

    }



    public void dibujar_linea(ArrayList<GeoPoint> lista_puntos) {
        linea = new Polyline(mapa);
        linea.getPoints().addAll(lista_puntos);
        mapa.getOverlays().add(linea);
        mapa.invalidate();
    }



    public void mover_pincel (double latitud, double longitud) {
        punto_dibujo2 = new GeoPoint(latitud, longitud);
        punto_dibujo1 = new GeoPoint(latitud, longitud);
    }

    public void borrar_pincel () {
        punto_dibujo1 = null;
        punto_dibujo2 = null;
    }

    public GeoPoint getPunto_marca() { return punto_marca; }

    public Polyline getLinea () { return linea; }

    public void setPunto_marca (double latitud, double longitud) {
        punto_marca = new GeoPoint(latitud, longitud);
        mover_mapa(latitud, longitud);
        mover_marca(latitud, longitud);
    }

    public void borrar_rutas () {
        mapa.getOverlays().clear();
        borrar_pincel();
        mapa.invalidate();
    }

    public void zoom (double norte, double este, double sur, double oeste) {
        BoundingBox b = new BoundingBox(norte,este, sur, oeste);

        mapa.zoomToBoundingBox(b,true,100);

    }

    public double distancia (GeoPoint hasta) {
        return punto_marca.distanceToAsDouble(hasta);
    }
}
