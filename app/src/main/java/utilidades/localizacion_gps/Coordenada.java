package utilidades.localizacion_gps;


import java.util.Date;

/**
 * Javier 2019.
 */



public class Coordenada {
    protected int identificador = 0;
    protected double latitud;
    protected double longitud;
    protected String proveedor;
    protected float velocidad = 0;
    protected Date fechaHora = null;
    protected long milisegundos;
    protected double altitud;
    protected String precision;

    public Coordenada (){

    }


    public Coordenada (double latitud, double longitud) {
        setLatitud(latitud);
        setLongitud(longitud);
    }




    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public Date getFechaHora () {
        return fechaHora;
    }

    public  void setFechaHora () {
        this.fechaHora = new Date();
        this.milisegundos = fechaHora.getTime();
    }

    public  void setFechaHora (Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public long getMilisegundos () { return milisegundos; }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public int getIdentificador () { return identificador; }
    public void setIdentificador (int identificador) { this.identificador = identificador; }

    public double getAltitud () { return altitud; }
    public void setAltitud (double altitud) { this.altitud = altitud; }
    public void setMilisegundos (long milisegundos) { this.milisegundos = milisegundos; }
}
