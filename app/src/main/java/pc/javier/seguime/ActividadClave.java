package pc.javier.seguime;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import pc.javier.seguime.adaptador.Constante;
import pc.javier.seguime.adaptador.Preferencias;
import pc.javier.seguime.vista.PantallaClave;
import utilidades.basico.EnlaceExterno;


/**
 * Javier 2018.
 */

public class ActividadClave extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clave);
    }

    @Override
    protected void onResume () {
        super.onResume();
        preferencias = new Preferencias(this);
        pantallaClave = new PantallaClave(this);
    }

    private Preferencias preferencias;
    private PantallaClave pantallaClave;


    public void clickRegistrarVersion (View v) {

        if (registroCorrecto()) {
            preferencias.setVersionRegistrada(true);
            this.finish();
        } else {
            pantallaClave.bocadillo(R.string.claveIncorrecta);
        }
    }


    // algoritmo de registro (super secreto)
    private boolean registroCorrecto () {
        String clave = preferencias.getUsuario();
        clave = clave + clave.length();
        String claveIntroducida = pantallaClave.getClave();

        if (claveIntroducida.equals(clave))
            return true;
        return false;
    }


    public void clickIrAlSitio(View view) {
        EnlaceExterno enlaceExterno = new EnlaceExterno(this);
        enlaceExterno.abrirEnlace(Constante.urlRegistro);
    }



    }
