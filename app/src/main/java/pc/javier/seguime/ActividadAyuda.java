package pc.javier.seguime;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pc.javier.seguime.adaptador.Constante;
import utilidades.basico.EnlaceExterno;


public class ActividadAyuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayuda);
        ((TextView) findViewById(R.id.version)).setText(Constante.version);
        if (Constante.versionConSMS == false) {
            ((Button) findViewById(R.id.ayuda_boton_donar)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.ayuda_donacion)).setVisibility(View.GONE);
        }
    }



    public void donar(View v) {
        EnlaceExterno enlaceExterno = new EnlaceExterno(this);
        enlaceExterno.abrirEnlace(Constante.urlDonacion);
    }

    public void sitio (View v) {
        EnlaceExterno enlaceExterno = new EnlaceExterno(this);
        enlaceExterno.abrirEnlace(Constante.urlSitio);
    }



}
