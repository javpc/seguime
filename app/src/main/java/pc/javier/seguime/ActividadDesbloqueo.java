package pc.javier.seguime;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import pc.javier.seguime.adaptador.Pantalla;
import pc.javier.seguime.adaptador.Preferencias;



public class ActividadDesbloqueo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desbloqueo);
    }




    public void clickDesbloquear (View v) {
        Preferencias preferencias = new Preferencias(this);
        Pantalla pantalla = new Pantalla(this);
        String clave = pantalla.getTexto(R.id.desbloqueo_clave);

        if (clave.equals(preferencias.getClave())) {
            preferencias.setBloqueado(false);
            finish();
        } else {
            pantalla.bocadillo(R.string.claveIncorrecta);
        }

    }


}
