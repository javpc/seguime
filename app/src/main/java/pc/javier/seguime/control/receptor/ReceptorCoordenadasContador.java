package pc.javier.seguime.control.receptor;

import pc.javier.seguime.adaptador.Coordenada;
import utilidades.basico.MensajeRegistro;
import utilidades.localizacion_gps.Localizador;

/**
 * Límite de coordenadas.
 * Establece cuantas coordenadas recolecta cada vez que se inicia el localizador.
 * Llegado el límite, apaga el localizador.
 */

public class ReceptorCoordenadasContador extends ReceptorCoordenadas {



    private int contador = 0;
    private  int limite = 1;
    private Localizador localizador;

    public ReceptorCoordenadasContador (Localizador localizador) {
        this.localizador = localizador;
    }

    @Override
    protected void procesarCoordenada(Coordenada coordenada) {
        contador++;
        if (contador >= limite) {
            contador = 0;
            MensajeRegistro.msj(this, "contador " + contador + " llegó al limite " + limite);
            localizador.desactivar();
        }
    }

    public void limite (int valor) {
        limite = valor;
    }

    public void reiniciar () {
        contador = 0;
    }
}
