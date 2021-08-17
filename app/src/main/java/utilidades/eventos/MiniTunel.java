package utilidades.eventos;

import java.util.ArrayList;

import utilidades.basico.MensajeRegistro;

public  class MiniTunel {

    public static boolean ACTIVADO = false;

    public static final ArrayList <MiniReceptor> listaReceptores = new ArrayList<MiniReceptor>();
    public static final ArrayList <MiniEvento>   listaEmisores   = new ArrayList<MiniEvento>();





    // suscriptores

    public static void suscribir (MiniReceptor receptor) {
        MensajeRegistro.msj("Tunel", "suscribiendo receptor");
        // impide la suscripción de Receptores ya presentes
        if (listaReceptores.contains(receptor))
            return;

        if (receptor.getClaseEmisora() == null)
            return;
        MensajeRegistro.msj("Tunel", receptor.getClaseEmisora());

        for (MiniEvento e: listaEmisores)
            if (e != null)
                if (e.get_clase_emisora().equals(receptor.getClaseEmisora()))
                    e.agregar_receptor(receptor);

        MensajeRegistro.msj("Tunel", "nuevo receptor");
        listaReceptores.add(receptor);
    }


    public static void suscribir (MiniEvento emisor) {
        MensajeRegistro.msj("Tunel", "suscribiendo emisor");
        if (listaEmisores.contains(emisor))
            return;

        if (emisor.get_clase_emisora() == null)
            return;
        MensajeRegistro.msj("Tunel", emisor.get_clase_emisora());

        for (MiniReceptor r : listaReceptores)
            if (r != null)
                if (emisor.get_clase_emisora().equals(r.getClaseEmisora()))
                    emisor.agregar_receptor(r);

        MensajeRegistro.msj("Tunel", "nuevo emisor");
        listaEmisores.add (emisor);
    }






    public static void borrarTodo () {
        listaReceptores.clear();
    }

    public static void desuscribir (MiniReceptor receptor) {
        listaReceptores.remove(receptor);
    }

    public static void desuscribir (MiniEvento emisor) {
        for (MiniReceptor r : listaReceptores)
            if (emisor.get_clase_emisora().equals(r.getClaseEmisora()))
                emisor.quitar_receptor (r);
        listaEmisores.remove(emisor);
    }

}
