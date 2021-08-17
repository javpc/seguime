package aplicacion.panico;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import pc.javier.seguime.R;

/**
 * Created by Javier on 25/7/2019.
 */

public class ActividadPanicoOpciones extends AppCompatActivity {



    private ControlPantallaPanico control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opcionespanico);
        control = new ControlPantallaPanico( this);
    }


    public void cancelar (View v) {
        control.cancelar ();
    }


    public void aceptar (View v) {
        control.guardar();
    }


}
