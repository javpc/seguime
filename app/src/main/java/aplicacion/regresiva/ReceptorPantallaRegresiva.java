package aplicacion.regresiva;

import pc.javier.seguime.vista.PantallaRegresiva;
import utilidades.Alarma;
import utilidades.basico.MensajeRegistro;
import utilidades.eventos.MiniReceptor;


/**
 * Javier 2019.
 *  actualiza la pantalla de la cuenta regresiva, muestra tiempo restante
 */

public class ReceptorPantallaRegresiva extends MiniReceptor {



    private PantallaRegresiva pantalla;
    private Alarma alarma;



    public ReceptorPantallaRegresiva(PantallaRegresiva pantalla) {

        this.pantalla = pantalla;
        alarma= new Alarma();
        setClaseEmisora(ControlPantallaRegresiva.class);


    }


    @Override
    public void recibir (String dato) {
            mostrarContador(Long.parseLong(dato));
    }


    private void mostrarContador (Long dato) {
        alarma.setFin(dato);
        MensajeRegistro.msj(alarma.getFin().toString());
        MensajeRegistro.msj(alarma.getFin().getTime());
        pantalla.contador(alarma.getHoras(), alarma.getMinutos(), alarma.getSegundos());
        // if (alarma.activada())  pantalla.dibujarBoton(false);
        MensajeRegistro.msj("REGRESIVA");
    }



}
